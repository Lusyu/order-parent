package com.eaphone.jiankang.demo;

import com.eaphone.jiankang.demo.core.document.Order;
import com.eaphone.jiankang.demo.core.document.embed.EmbeddedProduct;
import com.eaphone.jiankang.demo.core.service.DemoService;
import com.eaphone.jiankang.demo.core.util.query.OrderPageParam;
import com.eaphone.xxs.test.BaseSpringBootApplicationTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import java.text.SimpleDateFormat;
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

	/*@Override
	protected void mockSomeSpecialBeans() {
		super.mockSomeSpecialBeans();
	}*/

	@Override
	protected String setContextPath() {
		return "demo";
	}

	@Autowired
	private DemoService demoService;

	@Test @Override
	public void initSomeTestData() {
		EmbeddedProduct embeddedProduct = new EmbeddedProduct();
		embeddedProduct.setId("35bbb4562d49c8016ea35bb6");
		embeddedProduct.setName("电冰箱");
		embeddedProduct.setPrice(1000F);
		Order order = new Order();
		order.setOrderId("59bbb4290d49c8016ea18bb6");
		order.setOrderNumber("594784290949780165218436");
		order.setUserId("59bbb4290d49c8016ea18bb4");
		order.setPayPrice(1000F);
		order.setShipTime(new Date());
		order.setReceiptAddress("广州市....");
		order.setStatus(1);
		order.setList(Arrays.asList(embeddedProduct));
		Order save = demoService.save(order);
		pageOrder();
		initUpdateTestDataOrder();
	}

	private void initUpdateTestDataOrder(){
		EmbeddedProduct embeddedProduct = new EmbeddedProduct();
		embeddedProduct.setId("35bbb4562d49c8016ea35bb6");
		embeddedProduct.setName("机械革命");
		embeddedProduct.setPrice(800F);
		Order order = new Order();
		order.setOrderId("59bbb4290d49c8016ea18bb6");
		order.setOrderNumber("594784290949780165218433");
		order.setUserId("59bbb4290d49c8016ea18bb4");
		order.setPayPrice(800F);
		order.setShipTime(new Date());
		order.setReceiptAddress("广州市....");
		order.setStatus(0);
		order.setList(Arrays.asList(embeddedProduct));
		boolean b = demoService.updateByUserIdAndOrderId(order);
	}


	public void pageOrder(){
		OrderPageParam orderPageParam = new OrderPageParam();
		orderPageParam.setCurrentPage(1);
		orderPageParam.setPageSize(2);
		orderPageParam.setMinPayPrice(200F);
		orderPageParam.setMaxPayPrice(2000F);
		try{
			orderPageParam.setMinShipTime(new SimpleDateFormat("yyyy-MM-dd").parse("1971-12-12"));
			orderPageParam.setMaxShipTime(new SimpleDateFormat("yyyy-MM-dd").parse("2100-12-12"));
		}catch (Exception e){

		}
		orderPageParam.setOrderNumber("594784290949780165218436");
		orderPageParam.setStatus(1);
		Map<String, Object> orderPage = demoService.getOrderPage(orderPageParam);
		List<Order> orders= (List<Order>) orderPage.get("orders");
		System.out.println(orders.size()+"             test");
		orders.forEach(System.out::println);
	}
}
