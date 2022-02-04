package com.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.services.ConnectionService;

@RestController
public class ConnectionController {

	@Autowired
	@Qualifier("connectionService")
	private ConnectionService connectionService;

	@RequestMapping(
						value = "/sayhello/",
						method = RequestMethod.POST
					)
	public @ResponseBody String sayHello(
											@RequestParam(defaultValue = "default1") String arg1,
											@RequestParam(defaultValue = "default2") String arg2
										) throws Exception {
		
		return String.join(" ", connectionService.hello(), arg1, arg2);
	}

	// Funcionando
	@RequestMapping(
						value = "/getToken/", 
						method = RequestMethod.POST
					)
	public @ResponseBody String Token(
										@RequestParam(value = "data", required = true) String data
									) throws Exception {
		return connectionService.connection(data, "app/jwt-gp/authenticate/", "");
	}

	// Funcionando
	@RequestMapping(
					value = "/encryptData/", 
					method = RequestMethod.POST
					)
	public @ResponseBody String Encrypt(
											@RequestParam(value = "data", required = true) String data,
											@RequestParam(value = "idDistribuidor", required = true) int idDistribuidor
										) throws Exception {
		return connectionService.connection(
												data, 
												"app/public/encrypt/?idDistribuidor=" + idDistribuidor,
												""
											);
	}

	// Funcionando
	@RequestMapping(
					value = "/sendTx/", 
					method = RequestMethod.POST
					)
	public @ResponseBody String sendTx(
											@RequestParam(value = "data", required = true) String data,
											@RequestParam(value = "rand", required = true) String rand,
											@RequestParam(value = "auth", required = true) String auth
										) throws Exception {

		return connectionService.connection(
												data, 
												"service/sendTx.do?random=" + rand,
												auth
											);

	}

	
	// Funcionando
	@RequestMapping(
					value = "/identifyMe/", 
					method = RequestMethod.POST
					)
	public @ResponseBody String identifyMe(
											@RequestParam(value = "auth", required = true) String auth,
											@RequestParam(value = "rand", required = true) String rand
											) throws Exception {
		return connectionService.connection(
												"", 
												"service/validateMe.do?random=" + rand, 
												auth
											);
	}

	
	// Funcionando
	@RequestMapping(
					value = "/getProductList/", 
					method = RequestMethod.POST
					)
	public @ResponseBody String getProducts(
												@RequestParam(value = "rand", required = true) String rand,
												@RequestParam(value = "auth", required = true) String auth
											) throws Exception {
		return connectionService.connection(
												"", 
												"service/getProductList.do?random=" + rand,
												auth
											);
	}

	
	// Funcionando
	@RequestMapping(
					value = "/sendEcho/", 
					method = RequestMethod.POST
					)
	public @ResponseBody String sendEcho(
											@RequestParam(value = "auth") String auth, 
											@RequestParam(value = "rand") String rand
										) throws Exception {
		return connectionService.connection(
												"", 
												"service/sendEcho.do?random=" + rand, 
												auth
											);
	}

	// Funcionando
	@RequestMapping(
					value = "/getBalance/", 
					method = RequestMethod.POST
					)
	public @ResponseBody String getBalance(
											@RequestParam(value = "auth") String auth, 
											@RequestParam(value = "rand") String rand
										   ) throws Exception {
		return connectionService.connection(
												"", 
												"service/getBalance.do?random=" + rand, 
												auth
											);
	}

	// Funcionando
	@RequestMapping(
					value = "/verifyReference/", 
					method = RequestMethod.POST
					)
	public @ResponseBody String verifyReference(
													@RequestParam(value = "auth") String auth, 
													@RequestParam(value = "data") String data,
													@RequestParam(value = "rand") String rand
												) throws Exception {
		return connectionService.connection(
												data, 
												"service/verifyReference.do?random=" + rand, 
												auth
											);
	}

	// Funcionando
	@RequestMapping(
					value = "/confirmTx/", 
					method = RequestMethod.POST
					)
	public @ResponseBody String confirmTx(
											@RequestParam(value = "auth") String auth, 
											@RequestParam(value = "data") String data,
											@RequestParam(value = "rand") String rand
										) throws Exception {
		return connectionService.connection(
												data, 
												"service/confirmTx.do?random=" + rand, 
												auth
											);
	}
}