package com.eaphone.jiankang.demo;

import com.eaphone.jiankang.demo.core.document.Demo;
import com.eaphone.jiankang.demo.core.document.embed.EmbeddedDemo;
import com.eaphone.jiankang.demo.core.service.DemoService;
import com.eaphone.xxs.test.BaseSpringBootApplicationTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Demo PAI 集成测试用例
 */
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureStubRunner( ids ={"com.eaphone.jiankang:passport-webapp:+:stubs"}
		, repositoryRoot = "http://svn.xinxiangsui.net/nexus/content/groups/eaphone-g08/"
		,stubsMode = StubRunnerProperties.StubsMode.REMOTE)
public abstract class DemoApiApplicationTests extends BaseSpringBootApplicationTest {


	@Override
	protected String setContextPath() {
		return "demo";
	}

	@Autowired
	private DemoService demoService;

	@Test @Override
	public void initSomeTestData() {
		EmbeddedDemo embeddedDemo = new EmbeddedDemo();
		embeddedDemo.setId("35bbb4562d49c8016ea35bb6");
		embeddedDemo.setName("机械革命X10Ti");
		embeddedDemo.setPrice(1000F);


		List<EmbeddedDemo> children= new ArrayList<>();
		children.add(embeddedDemo);


		Demo demo = new Demo();
		demo.setId("5ff3e1ab3017c813400bf90e");
		demo.setName("jack");
		demo.setPrice(1000f);
		demo.setCreateTime(new Date());
		demo.setChildren(children);

		Demo demoTwo = new Demo();
		demoTwo.setId("5ff3e1ac3017c813400bf90f");
		demoTwo.setName("lucy");
		demoTwo.setPrice(1000f);
		demo.setCreateTime(new Date());
		demo.setChildren(children);

		demoService.save(demo);
		demoService.save(demoTwo);
		demoService.findAll().forEach(System.out::println);

		findAll();
	}


	public void findAll(){
		demoService.findAll().forEach(System.out::println);
	}
}
