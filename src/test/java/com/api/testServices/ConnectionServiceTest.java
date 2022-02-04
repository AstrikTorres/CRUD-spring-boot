package com.api.testServices;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.api.services.ConnectionService;

public class ConnectionServiceTest {

	@Test
	public void testConnectionServiceHello() throws Exception {
		ConnectionService connectionService = new ConnectionService();
		String resultExpected = "HELLO!";
		String result = connectionService.hello();
		assertEquals(resultExpected, result);
	}
	
	@Test
	public void testConnectionServiceApiToken() throws Exception {
		// given
		ConnectionService connectionService = new ConnectionService();
		String resultExpected = "{\"token\":\"qmzAAEYFmqQbIT/Ktxme8Akih4Md6cXf9hTHKjfO626aEN8GrvSLaA9CJP3Q1UI5bL6/tDk+nsTwmGoUwm3VR+LPbGaB13DSSR2d+wUdceSDevuAR+P26GaEBEwCJxf4z5x7CxXJ+LA/ihMVRwLKBw==\",\"success\":true}";
		// when
		String result = connectionService.connection("idDistribuidor=83&codigoDispositivo=GPS83-TPV-17&password=12345678", "app/jwt-gp/authenticate/", "");
		// then
		assertEquals(resultExpected, result);
	}
	
	@Test
	public void testConnectionServiceApiEncrypt() throws Exception {
		// given
		ConnectionService connectionService = new ConnectionService();
		String resultExpected = "7JT3n4xCabZqwtz+o37F3Y8Q7tc2boXZL7U6jJRGPSTDdfTwpm4hCskQ2+x92B1zK4eH1fnXA5nyitO/hAnIt3i0D4mjGaOE6T8RfCSVQ9RSvkqn0oIP8mOKkODkTJXtYCIf3OTtTKTklDlAz68fL6OqHReucDP/nSDhi6YXAPl8itlviEG4VBvGEyZzHj1t";
		// when
		String result = connectionService.connection("data={\"idServicio\":38, \"idProducto\":5267 , \"referencia\": \"09901172032\", \"telefono\":\"1111111111\", \"horaLocal\":\"20200401223601\", \"upc\": \"6566se\"}", "app/public/encrypt/?idDistribuidor=83", "");
		// then
		assertEquals(resultExpected, result);
	}
	
}
