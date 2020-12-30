package com.eaphone.jiankang.demo;

import com.eaphone.jiankang.demo.core.document.Demo;
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
		Demo demo = new Demo();
		demo.setOrderId("59bbb4290d49c8016ea18bb6");
		demo.setUserId("59bbb4290d49c8016ea18bb4");
		demo.setPayPrice(1000F);
		demo.setShipTime(new Date());
		demo.setReceiptAddress("广州市....");
		demo.setStatus(1);
		demo.setList(Arrays.asList(embeddedProduct));
		Demo save = demoService.save(demo);
		pageOrder();
		initUpdateTestDataOrder();
	}

	private void initUpdateTestDataOrder(){
		EmbeddedProduct embeddedProduct = new EmbeddedProduct();
		embeddedProduct.setId("35bbb4562d49c8016ea35bb6");
		embeddedProduct.setName("机械革命");
		embeddedProduct.setPrice(800F);
		Demo demo = new Demo();
		demo.setOrderId("59bbb4290d49c8016ea18bb6");
		demo.setUserId("59bbb4290d49c8016ea18bb4");
		demo.setPayPrice(800F);
		demo.setShipTime(new Date());
		demo.setReceiptAddress("广州市....");
		demo.setStatus(0);
		demo.setList(Arrays.asList(embeddedProduct));
		boolean b = demoService.updateByUserIdAndOrderId(demo);
	}


	public void pageOrder(){
		OrderPageParam orderPageParam = new OrderPageParam();
		orderPageParam.setCurrentPage(1);
		orderPageParam.setPageSize(2);
		orderPageParam.setMinPayPrice(200F);
		orderPageParam.setMaxPayPrice(2000F);
		orderPageParam.setOrderNumber("59bbb4290d49c8016ea18bb6");
		orderPageParam.setStatus(1);
		Map<String, Object> orderPage = demoService.getOrderPage(orderPageParam);
		List<Demo> demos = (List<Demo>) orderPage.get("orders");
		System.out.println(demos.size()+"             test");
		demos.forEach(System.out::println);
	}
}
