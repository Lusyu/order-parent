package com.eaphone.jiankang.demo.client.test;

import com.eaphone.jiankang.demo.client.DemoClient;
import com.eaphone.jiankang.demo.client.dto.DemoDto;
import com.eaphone.jiankang.demo.client.vo.DemoDetailsVo;
import com.eaphone.jiankang.demo.client.vo.DemoVo;
import com.eaphone.xxs.test.BaseSpringBootApplicationTest;
import lombok.NonNull;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import java.util.List;

@SpringBootTest(classes = DemoClientTestApplication.class)
@AutoConfigureStubRunner(ids = "com.eaphone.jiankang.demo:demo-api:+:stubs"
        , stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class DemoClientTestApplicationTests extends BaseSpringBootApplicationTest {

    @Autowired
    private DemoClient demoClient;

    /**
     * 新增demo
     */
    @Test
    public void saveDemo() {
        DemoDto demoDto = new DemoDto();
        demoDto.setName("lucy");
        demoDto.setPrice(1000);
        Boolean bool = demoClient.saveDemo(demoDto);
        System.out.println(bool + "   findUserDemos++++++++++++++++");
        Assert.assertNotNull(bool);

    }

    /**
     * 删除demo
     */
    @Test
    public void deleteDemoById() {
        Boolean bool = demoClient.deleteDemoById("5ff3e1ab3017c813400bf90e");
        System.out.println(bool + "   deleteDemoById++++++++++++++");
        Assert.assertNotNull(bool);
    }

    /**
     * 更新demo
     */
    @Test
    public void updateDemoById() {
        Boolean bool = demoClient.updateDemoById("5ff3e1ab3017c813400bf90e", "jack", 1000f);
        System.out.println(bool + "   updateDemo+++++++++++++++++");
        Assert.assertNotNull(bool);
    }

    /**
     * 根据id获取demo详情
     * @return
     */
    @Test
    public void findDemoById() {
        DemoDetailsVo demoDetails = demoClient.findDemoById("5ff3e1ab3017c813400bf90e");
        System.out.println(demoDetails + "findDemoById   +++++++++++++++++++");
        Assert.assertNotNull(demoDetails);
    }

    @Test
    public void findDemos(){
        List<DemoVo> demos = demoClient.findDemos();
        System.out.println(demos +"findDemos   +++++++++++++++");
    }
}
