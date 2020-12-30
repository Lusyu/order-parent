package com.eaphone.jiankang.demo.client.test;


import com.eaphone.jiankang.demo.client.DemoClient;
import com.eaphone.jiankang.demo.client.dto.OrderResult;
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
	 * 获取指定用户的所有订单
	 */
	@Test
	public void findAllByUserId(){
		List<OrderResult> list = demoClient.findAllByUserId("35bbb4562d49c8016ea35bb6");
		System.out.println(list.size()+"   findAllByUserId++++++++++++++++");
		list.forEach(System.out::println);
		Assert.assertNotNull(list);

	}

	/**
	 * 获取指定订单状态
	 */
	@Test
	public void findByOrderStatus(){
		Integer status = demoClient.findByOrderStatus("594784290949780165218436");
		System.out.println(status+"	     findByOrderStatus+++++++++++++++++");
		Assert.assertNotNull(status);
	}
}
