package com.eaphone.jiankang.demo.core.service;

import com.eaphone.jiankang.demo.core.document.Demo;
import com.eaphone.jiankang.demo.core.document.embed.EmbeddedDemo;
import com.eaphone.jiankang.demo.core.dto.DemoChildrenDto;
import com.eaphone.jiankang.demo.core.redis.UserDemoKeys;
import com.eaphone.jiankang.demo.core.repo.DemoRepo;
import com.eaphone.jiankang.demo.core.vo.DemoDetailsVo;
import com.eaphone.jiankang.demo.core.vo.DemoVo;
import com.eaphone.smarthealth.redis.entity.BaseRedisModel;
import com.eaphone.smarthealth.redis.service.RedisService;
import com.eaphone.smarthealth.service.BaseMongoService;
import com.mongodb.BasicDBObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Demo业务接口实现类
 */
@Service
@Slf4j
public class DemoService extends BaseMongoService<Demo> {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private DemoRepo demoRepo;
    @Autowired
    private RedisService redisService;

    /**
     * 根据demo的id删除指定demo
     *
     * @param id
     * @return
     */
    public boolean deleteDemoById(String id) {
        demoRepo.deleteById(id);
        redisService.delete(UserDemoKeys.USER_DEMO_KEYS, id);
        return true;
    }

    /**
     * 更新指定demo的名称
     *
     * @param id
     * @param name 名称
     * @return
     */
    public boolean updateDemoNameById(String id, String name, Float price) {
        if (demoRepo.existsById(id)) {
            return mongoTemplate.updateFirst(Query.query(Criteria.where(Demo.ID).is(id))
                    , Update.update(Demo.NAME, name).set(Demo.PRICE, price), Demo.class)
                    .getModifiedCount() > 0;
        }
        return false;
    }

    /**
     * 获取指定demo
     *
     * @param id
     * @return demo
     */
    public DemoDetailsVo findDemoById(String id) {
        //查询缓存
        BaseRedisModel<DemoDetailsVo> redisModel =
                redisService.get(UserDemoKeys.USER_DEMO_KEYS, id, DemoDetailsVo.class);
        //不判断data数据 避免缓存穿透
        if (redisModel == null) {
            //查看数据库 缓存该demo
            Demo demo = demoRepo.findById(id).orElseGet(null);
            DemoDetailsVo demoDetailsVo = demo == null ? null : Demo.buildDetail(demo);
            //默认过期3分钟  单位S  持久化需设置负整数
            redisService.set(UserDemoKeys.USER_DEMO_KEYS, id, new BaseRedisModel(demoDetailsVo));
            return demoDetailsVo;
        }
        return redisModel.getData();
    }

    /**
     * 更新嵌套字段
     *
     * @param demoChildren 新值
     * @return
     */
    public boolean updateEmbedDemo(String id, DemoChildrenDto demoChildren) {
        if (demoRepo.existsById(id)) {
            return false;
        }
        return mongoTemplate.updateFirst(
                Query.query(Criteria.where(Demo.ID).is(id)
                        .and(Demo.EMBED_ID).is(demoChildren.getEmbedId()))
                , Update.update("children.$.name", demoChildren.getName())
                        .set("children.$.price", demoChildren.getPrice())
                , Demo.class).getModifiedCount() > 0;
    }

    /**
     * 对指定demo新增嵌套数据
     *
     * @param id
     * @param demoChildrenList 新增的值集
     */
    public void updateSaveEmbedDemo(String id, List<DemoChildrenDto> demoChildrenList) {
        if (!CollectionUtils.isEmpty(demoChildrenList) && demoRepo.existsById(id)) {
            demoChildrenList.stream()
                    .filter(demoChildrenDto -> demoChildrenDto.getEmbedId() != null)
                    .map(EmbeddedDemo::build)
                    .forEach(embeddedDemo -> {
                        mongoTemplate.upsert(
                                Query.query(Criteria.where(Demo.ID).is(id))
                                , new Update().addToSet(Demo.CHILDREN, embeddedDemo)
                                , Demo.class);
                    });
        }
    }

    /**
     * 对指定demo删除嵌套数据
     *
     * @param id
     * @param demoChildrenIds 嵌套的id集
     */
    public void updateDeleteEmbedDemo(String id, List<String> demoChildrenIds) {
        if (!CollectionUtils.isEmpty(demoChildrenIds) && demoRepo.existsById(id)) {
            demoChildrenIds.stream()
                    .filter(demoChildrenId -> demoChildrenId != null)
                    .map(demoChildrenId -> {
                        BasicDBObject basicDBObject = new BasicDBObject();
                        basicDBObject.put("id", demoChildrenId);
                        return basicDBObject;
                    })
                    .forEach(basicDBObject -> {
                        mongoTemplate.updateFirst(
                                Query.query(Criteria.where(Demo.ID).is(id))
                                , new Update().pull(Demo.CHILDREN, basicDBObject)
                                , Demo.class);
                    });
        }
    }

    /**
     * 不分页
     *
     * @return
     */
    public List<DemoVo> list() {
        return demoRepo.findAll().stream().map(Demo::build).collect(Collectors.toList());
    }

    /**
     * 分页
     *
     * @param pageable 分页参数
     * @return
     */
    public Page<Demo> page(Pageable pageable) {
        Page<Demo> page = findPage(new Query(), pageable);
        page.getContent().forEach(demo -> {
            demo.setCreateBy(null);
            demo.setUpdateTime(null);
            demo.setIsDelete(null);
            demo.setChildren(null);
            demo.setUpdateBy(null);
        });
        return page;
    }

}
