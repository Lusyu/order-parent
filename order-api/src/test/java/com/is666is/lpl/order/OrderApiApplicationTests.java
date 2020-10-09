package com.is666is.lpl.order;

import com.eaphone.xxs.test.BaseSpringBootApplicationTest;
import com.is666is.lpl.order.core.document.Order;
import com.is666is.lpl.order.core.document.Product;
import com.is666is.lpl.order.core.service.OrderService;
import com.is666is.lpl.order.core.util.query.OrderPageParam;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Order PAI 集成测试用例
 */

@SpringBootTest(classes =OrderApiApplication.class)
@AutoConfigureStubRunner(ids = {"com.eaphone.jiankang:passport-webapp:+:stubs"
		,"com.eaphone.jiankang:jiankang-api:+:stubs"
		,"com.eaphone.jiankang.device.g08:g08-api:+:stubs"}
		,repositoryRoot = "http://svn.xinxiangsui.net/nexus/content/groups/eaphone-g08/"
		,stubsMode = StubRunnerProperties.StubsMode.REMOTE)
public abstract class OrderApiApplicationTests extends BaseSpringBootApplicationTest {

	/*@Override
	protected void mockSomeSpecialBeans() {
		super.mockSomeSpecialBeans();
	}*/

	@Override
	protected String setContextPath() {
		return "order";
	}

	@Autowired
	private OrderService orderService;

	@Test @Override
	public void initSomeTestData() {
		Product product = new Product();
		product.setId("35bbb4562d49c8016ea35bb6");
		product.setName("电冰箱");
		product.setPrice(1000F);
		Order order = new Order();
		order.setOrderId("59bbb4290d49c8016ea18bb6");
		order.setOrderNumber("594784290949780165218436");
		order.setUserId("59bbb4290d49c8016ea18bb4");
		order.setPayPrice(1000F);
		order.setShipTime(new Date());
		order.setReceiptAddress("广州市....");
		order.setStatus(1);
		order.setList(Arrays.asList(product));
		Order save = orderService.save(order);
		initUpdateTestDataOrder();
		pageOrder();
	}

	private void initUpdateTestDataOrder(){
		Product product = new Product();
		product.setId("35bbb4562d49c8016ea35bb6");
		product.setName("机械革命");
		product.setPrice(800F);
		Order order = new Order();
		order.setOrderId("59bbb4290d49c8016ea18bb6");
		order.setOrderNumber("594784290949780165218433");
		order.setUserId("59bbb4290d49c8016ea18bb4");
		order.setPayPrice(800F);
		order.setShipTime(new Date());
		order.setReceiptAddress("广州市....");
		order.setStatus(0);
		order.setList(Arrays.asList(product));
		boolean b = orderService.updateByUserIdAndOrderId(order);
	}


	public void pageOrder(){
		OrderPageParam orderPageParam = new OrderPageParam();
		orderPageParam.setCurrentPage(1);
		orderPageParam.setPageSize(4);
		orderPageParam.setMinPayPrice(100F);
		orderPageParam.setMaxPayPrice(1000F);
		try{
			orderPageParam.setMinShipTime(new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-04"));
			orderPageParam.setMaxShipTime(new SimpleDateFormat("yyyy-MM-dd").parse("2020-10-11"));
		}catch (Exception e){

		}
		orderPageParam.setOrderNumber("594784290949780165218436");
		orderPageParam.setStatus(1);
		Map<String, Object> orderPage = orderService.getOrderPage(orderPageParam);
/*		List<Order>  orderList= (List<Order>) orderPage.get("orders");
		orderList.forEach(System.out::println);*/
	}
}
