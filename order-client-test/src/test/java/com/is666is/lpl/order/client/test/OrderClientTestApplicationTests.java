package com.is666is.lpl.order.client.test;

import com.eaphone.xxs.test.BaseSpringBootApplicationTest;
import com.is666is.lpl.order.client.OrderClient;

import com.is666is.lpl.order.client.dto.OrderResult;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import java.util.List;

@SpringBootTest(classes = OrderClientTestApplication.class)
@AutoConfigureStubRunner(ids = "com.is666is.lpl.order:order-api:+:stubs"
						,stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class OrderClientTestApplicationTests extends BaseSpringBootApplicationTest {

	@Autowired
	private OrderClient orderClient;

	/**
	 * 获取指定用户的所有订单
	 */
	@Test
	public void findAllByUserId(){
		List<OrderResult> list = orderClient.findAllByUserId("59bbb4290d49c8016ea18bb6");
		System.out.println(list.size()+"   findAllByUserId++++++++++++++++");
		Assert.assertNotNull(list);

	}

	/**
	 * 获取指定订单状态
	 */
	@Test
	public void findByOrderStatus(){
		Integer status = orderClient.findByOrderStatus("594784290949780165218436");
		System.out.println(status+"	     findByOrderStatus+++++++++++++++++");
		Assert.assertNotNull(status);
	}
}
