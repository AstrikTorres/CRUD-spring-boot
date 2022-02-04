package com.api.testControllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.net.URLEncoder;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.api.services.ConnectionService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// @ExtendWith(SpringExtension.class)
@SpringBootTest(/*classes = AppConfig.class*/)
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class ConnectionControllerTest {
	@Mock
	public ConnectionService connectionService;
	
	@Autowired
	public MockMvc mockMvc;

	@BeforeEach
	public void testFirst() {
	//	System.out.println("---------------------");
	}
	
	static ObjectMapper mapper = new ObjectMapper();
	
	static String token = null;
	static String cypheredMsg = null;
	static String resultTx = null;
	static String bearer = null;
	static String identifyMe = null;
	static String productList = null;
	static String echo = null;
	static String balance = null;
	static String verifyReference = null;
	static String confirmTx = null;

	@Test
	@Order(0)
	public void test_sayHello() throws Exception {
		// given
		String resultExpected = "HELLO! Astrik Torres";
		// when
		MvcResult result = mockMvc.perform(
											post("/sayhello/")
												.param("arg1", "Astrik")
												.param("arg2", "Torres")
										).andReturn();
		String msg = result.getResponse().getContentAsString();
		// then
		//		System.out.println(resultExpected);
		System.out.println(msg);
		assertEquals(resultExpected, msg);
	}
	
	@Test
	@Order(1)
	public void test_getToken() throws Exception {
		String idDistribuidor = "83";
		String codigoDispositivo = "GPS83-TPV-17";
		String password = "12345678";
		
		String data = String.join("&", "idDistribuidor=" + idDistribuidor,
										"codigoDispositivo=" + codigoDispositivo,
										"password=" + password
									);
		MvcResult result = mockMvc.perform(
											post("/getToken/")
												.param("data", data)
										).andReturn();
		
		token = result.getResponse().getContentAsString();
		System.out.println(token);
		
		JsonNode node = mapper.readTree(token);
		bearer = (node.get("token")).textValue();
		System.out.println(bearer);
	}
	
	@Test
	@Order(2)
	public void test_encryptData() throws Exception {
		int idServicio = 687;
		int idProducto = 6752;
		String referencia = "IMDM265439384";
		String telefono = "1111111111";
		String horaLocal = "20200401224731";
		String upc = "65686se";
		
		JsonData jsonData = new JsonData(idServicio, idProducto, referencia, telefono, horaLocal, upc);
		String data = mapper.writeValueAsString(jsonData);
		
		String idDistribuidor = "83";
		
		MvcResult result = mockMvc.perform(
											post("/encryptData/")
												.param("data", "data=" + data)
												.param("idDistribuidor", idDistribuidor)
											).andReturn();
		
		cypheredMsg = result.getResponse().getContentAsString();
		System.out.println(cypheredMsg);
	}
	
	@Test
	@Order(3)
	public void test_sendTx() throws Exception {
		
		MvcResult result = mockMvc.perform(
											post("/sendTx/")
												.param("data", "signed=" + URLEncoder.encode(cypheredMsg, "UTF8"))
												.param("rand", String.valueOf(new Date().getTime()))
												.param("auth", "Bearer " + bearer)
											).andReturn();
		resultTx = result.getResponse().getContentAsString();
		System.out.println(resultTx);
	}
	
	@Test
	@Order(4)
	public void test_identifyMe() throws Exception {
		MvcResult result = mockMvc.perform(
											post("/identifyMe/")
												.param("rand", String.valueOf(new Date().getTime()))
												.param("auth", "Bearer " + bearer)
											).andReturn();
		identifyMe = result.getResponse().getContentAsString();
		System.out.println(identifyMe);
	}
	
	@Test
	@Order(5)
	public void test_getProductList() throws Exception {
		MvcResult result = mockMvc.perform(
											post("/getProductList/")
												.param("rand", String.valueOf(new Date().getTime()))
												.param("auth", "Bearer " + bearer)
											).andReturn();
		productList = result.getResponse().getContentAsString();
		System.out.println(productList);
	}
	
	@Test
	@Order(6)
	public void test_sendEcho() throws Exception {
		MvcResult result = mockMvc.perform(
											post("/sendEcho/")
												.param("rand", String.valueOf(new Date().getTime()))
												.param("auth", "Bearer " + bearer)
											).andReturn();
		echo = result.getResponse().getContentAsString();
		System.out.println(echo);
	}
	
	@Test
	@Order(7)
	public void test_getBalance() throws Exception {
		MvcResult result = mockMvc.perform(
											post("/getBalance/")
												.param("rand", String.valueOf(new Date().getTime()))
												.param("auth", "Bearer " + bearer)
											).andReturn();
		balance = result.getResponse().getContentAsString();
		System.out.println(balance);
	}
	
	@Test
	@Order(8)
	public void test_verifyReference() throws Exception {
		MvcResult result = mockMvc.perform(
											post("/verifyReference/")
												.param("data", "signed=" + URLEncoder.encode(cypheredMsg, "UTF8"))	
												.param("rand", String.valueOf(new Date().getTime()))
												.param("auth", "Bearer " + bearer)
											).andReturn();
		verifyReference = result.getResponse().getContentAsString();
		System.out.println(verifyReference);
	}
	
	@Test
	@Order(9)
	public void test_confirmTx() throws Exception {
		MvcResult result = mockMvc.perform(
											post("/confirmTx/")
												.param("data", "signed=" + URLEncoder.encode(cypheredMsg, "UTF8"))	
												.param("rand", String.valueOf(new Date().getTime()))
												.param("auth", "Bearer " + bearer)
											).andReturn();
		confirmTx = result.getResponse().getContentAsString();
		System.out.println(confirmTx);
	}
	
	@AfterAll
	public static void test_afterAll() throws Exception {
		System.out.println("\n\nEnd");
		System.out.println(token);
		System.out.println(cypheredMsg);	
		System.out.println(resultTx);
		System.out.println(bearer);
		System.out.println(identifyMe);
		System.out.println(productList);
		System.out.println(echo);
		System.out.println(balance);
		System.out.println(verifyReference);
		System.out.println(confirmTx);
	}
	
}
