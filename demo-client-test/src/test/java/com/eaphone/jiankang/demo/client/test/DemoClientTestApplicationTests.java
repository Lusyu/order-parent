package com.eaphone.jiankang.demo.client.test;

import com.eaphone.jiankang.demo.client.DemoClient;
import com.eaphone.jiankang.demo.client.dto.DemoResult;
import com.eaphone.xxs.test.BaseSpringBootApplicationTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import java.util.List;

@SpringBootTest(classes = DemoClientTestApplication.class)
@AutoConfigureStubRunner(ids = "com.eaphone.jiankang.demo:demo-api:+:stubs"
						,stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class DemoClientTestApplicationTests extends BaseSpringBootApplicationTest {

	@Autowired
	private DemoClient demoClient;

	/**
	 * 获取指定用户的所有demo
	 */
	@Test
	public void findUserDemos(){
		List<DemoResult> list = demoClient.findUserDemos("59bbb4290d49c8016ea18bb4");
		System.out.println(list.size()+"   findUserDemos++++++++++++++++");
		list.forEach(System.out::println);
		Assert.assertNotNull(list);

	}

	/**
	 * 获取指定demo状态
	 */
	@Test
	public void findDemoStatus(){
		Integer status = demoClient.findDemoStatus("59bbb4290d49c8016ea18bb6");
		System.out.println(status+"	     findDemoStatus+++++++++++++++++");
		Assert.assertNotNull(status);
	}
}
