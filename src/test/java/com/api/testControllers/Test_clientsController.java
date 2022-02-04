package com.api.testControllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.api.models.ClientsModel;
import com.api.services.ClientsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@SpringBootTest()
//@WebMvcTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class Test_clientsController {
	@Autowired
	public ClientsService clientsService;
	
	@Autowired
	public MockMvc mockMvc;
	
	@BeforeAll
	public static void setUp() {
		System.out.println("---------------------");
	}
	static private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	@Order(0)
	public void test_clientsHello() throws Exception {
		String resultExpected = "HELLO!";
		String endpoint = String.join("/", "/clients", "hello/");
		// when
		MvcResult result = mockMvc.perform(
											get(endpoint)
										).andReturn();
		String msg = result.getResponse().getContentAsString();
		// then
		//		System.out.println(resultExpected);
		System.out.println(msg);
		assertEquals(resultExpected, msg);
	}
	
	@Test
	@Order(1)
	public void test_createClient() throws Exception {
		long ms = System.currentTimeMillis();
		String name = "Prueba por bytes";
		Date created = new Date(ms);
		
		ClientsModel client = new ClientsModel(name, created);
		
		String data = mapper.writeValueAsString(client);
		byte[] postDataBytes = data.toString().getBytes("UTF-8");

		// when
		MvcResult result = mockMvc.perform(
											post("/clients/")
												.contentType(MediaType.APPLICATION_JSON)
												.content(postDataBytes)
										).andReturn();
		String msg = result.getResponse().getContentAsString();
		// then
		//		System.out.println(resultExpected);
		System.out.println(msg);
		// assertEquals(resultExpected, msg);
	}
	
	@Test
	@Order(2)
	public void test_getClients() throws Exception {
		// String resultExpected = "HELLO!";
		// when
		MvcResult result = mockMvc.perform(
											get("/clients/")
										).andReturn();
		String msg = result.getResponse().getContentAsString();
		// then
		//		System.out.println(resultExpected);
		System.out.println(msg);
		// assertEquals(resultExpected, msg);
	}
	
	@Test
	@Order(3)
	public void test_updateClients() throws Exception {
		long ms = System.currentTimeMillis();
		int id = 35;
		String name = "update por bytes";
		Date modified = new Date(ms);
		
		ClientsModel client = new ClientsModel(id, name, modified);
		
		String data = mapper.writeValueAsString(client);
		byte[] postDataBytes = data.toString().getBytes("UTF-8");

		// String resultExpected = "HELLO!";
		// when
		MvcResult result = mockMvc.perform(
											put("/clients/")
												.contentType(MediaType.APPLICATION_JSON)
												.content(postDataBytes)
										).andReturn();
		String msg = result.getResponse().getContentAsString();
		// then
		//		System.out.println(resultExpected);
		System.out.println(msg);
		// assertEquals(resultExpected, msg);
	}
	
	@Test
	@Order(4)
	public void test_deleteClients() throws Exception {
		int id = 30;
		// String resultExpected = "HELLO!";
		// when
		MvcResult result = mockMvc.perform(
											delete("/clients/" + id + "/")
										).andReturn();
		String msg = result.getResponse().getContentAsString();
		// then
		//		System.out.println(resultExpected);
		System.out.println(msg);
		// assertEquals(resultExpected, msg);
	}
}
