package com.eaphone.jiankang.demo.core.document;
import com.eaphone.jiankang.demo.core.document.embed.EmbeddedDemo;
import com.eaphone.jiankang.demo.core.vo.DemoDetailsVo;
import com.eaphone.jiankang.demo.core.vo.DemoVo;
import com.eaphone.smarthealth.model.BaseDocument;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.List;

/**
 * demo实体
 */
@Data
@Document(collection = "demo")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Demo extends BaseDocument implements Serializable {

    public static final String NAME = "name";
    public static final String CHILDREN="children";
    public static final String EMBED_ID="children.id";
    public static final String PRICE="price";

    private String name;//名称

    private Float price;//价格

    private List<EmbeddedDemo> children;//嵌套demo

    /**
     * 不分页  语法糖
     * @param demo
     * @return
     */
    public static DemoVo build(Demo demo){
        DemoVo demoVo = new DemoVo();
        BeanUtils.copyProperties(demo,demoVo);
        return demoVo;
    }

    /**
     * 详情    语法糖
     * @param demo
     * @return
     */
    public static DemoDetailsVo buildDetail(Demo demo){
        DemoDetailsVo demoDetailsVo = new DemoDetailsVo();
        BeanUtils.copyProperties(demo,demoDetailsVo);
        return demoDetailsVo;
    }

}
