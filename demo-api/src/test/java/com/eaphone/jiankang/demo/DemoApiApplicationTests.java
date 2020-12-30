package com.eaphone.jiankang.demo;

import com.eaphone.jiankang.demo.core.document.Demo;
import com.eaphone.jiankang.demo.core.document.embed.EmbeddedDemo;
import com.eaphone.jiankang.demo.core.service.DemoService;
import com.eaphone.jiankang.demo.core.util.query.DemoPageParam;
import com.eaphone.xxs.test.BaseSpringBootApplicationTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Order PAI 集成测试用例
 */

@SpringBootTest(classes = DemoApiApplication.class)
@AutoConfigureStubRunner( ids ={"com.eaphone.jiankang:passport-webapp:+:stubs"
		/*,"com.eaphone.jiankang:passport-client:+:stubs"
		,"com.eaphone.jiankang.device.g08:g08-api:+:stubs"*/}
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
		embeddedDemo.setName("电冰箱");
		embeddedDemo.setPrice(1000F);
		Demo demo = new Demo();
		demo.setDemoId("59bbb4290d49c8016ea18bb6");
		demo.setUserId("59bbb4290d49c8016ea18bb4");
		demo.setPayPrice(1000F);
		demo.setShipTime(new Date());
		demo.setReceiptAddress("广州市....");
		demo.setStatus(1);
		demo.setList(Arrays.asList(embeddedDemo));
		Demo save = demoService.save(demo);

		getDemoPage();
		updateDemoAddressTest();
	}

	private void updateDemoAddressTest(){
		boolean b = demoService.updateDemoAddress("59bbb4290d49c8016ea18bb6","北京市...");
		System.out.println("");
	}


	public void getDemoPage(){
		DemoPageParam demoPageParam = new DemoPageParam();
		demoPageParam.setCurrentPage(1);
		demoPageParam.setPageSize(2);
		demoPageParam.setMinPayPrice(200F);
		demoPageParam.setMaxPayPrice(2000F);
		demoPageParam.setDemoId("59bbb4290d49c8016ea18bb6");
		demoPageParam.setStatus(1);
		Map<String, Object> orderPage = demoService.getDemoPage(demoPageParam);
		List<Demo> demos = (List<Demo>) orderPage.get("demos");
		System.out.println(demos.size()+"  getOrderPageTest");
		demos.forEach(System.out::println);
	}
}
