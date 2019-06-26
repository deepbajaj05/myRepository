package com.retail.store;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.retail.store.service.DiscountServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RetailStoreApplicationTests {

	@Autowired
	DiscountServiceImpl discountService;
	
	private MockMvc mockMvc;	
	
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() throws Exception {
	    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void test_getDiscount_withOutGrocery() throws Exception {
				
		String json = "{\n" + 
				"	\"billDetails\": {\n" + 
				"		\"itemName\": \"wheat\",\n" + 
				"		\"itemCategory\": \"FRUITS\",\n" + 
				"		\"amount\": \"300\"\n" + 
				"	},\n" + 
				"	\"isEmployee\": \"true\",\n" + 
				"	\"isAffilate\": \"true\",\n" + 
				"	\"customerDuration\": \"3\"\n" + 
				"}";
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/getDiscount")
				.accept(MediaType.APPLICATION_JSON).content(json)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		String expectedResult="{\n" + 
				"    \"Status\": \"success\",\n" + 
				"    \"netPaybaleAmount\": 195\n" + 
				"}";
		

				
		JSONAssert.assertEquals(expectedResult, result.getResponse()
						.getContentAsString(), false);
	}
	
	@Test
	public void test_getDiscount_withGrocery() throws Exception {
				
		String json = "{\n" + 
				"	\"billDetails\": {\n" + 
				"		\"itemName\": \"wheat\",\n" + 
				"		\"itemCategory\": \"GROCERY\",\n" + 
				"		\"amount\": \"300\"\n" + 
				"	},\n" + 
				"	\"isEmployee\": \"true\",\n" + 
				"	\"isAffilate\": \"true\",\n" + 
				"	\"customerDuration\": \"3\"\n" + 
				"}";
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/getDiscount")
				.accept(MediaType.APPLICATION_JSON).content(json)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		String expectedResult="{\n" + 
				"    \"Status\": \"success\",\n" + 
				"    \"netPaybaleAmount\": 195\n" + 
				"}";
		

				
		JSONAssert.assertEquals(expectedResult, result.getResponse()
						.getContentAsString(), false);
	}
}
