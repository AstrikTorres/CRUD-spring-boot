package com.api.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.models.ClientsModel;
import com.api.services.ClientsService;

@RestController
@RequestMapping("/clients/")
public class ClientsController {
	
	@Autowired
	@Qualifier("clientsService")
	private ClientsService clientsService;

	@GetMapping(path = "/hello/")
	public @ResponseBody String DBhello() throws Exception {
		return clientsService.hello();
	}
	
	@GetMapping
	public @ResponseBody ArrayList<ClientsModel> getClients(){
//		System.out.println("-->");
		return clientsService.getClients();
	}
	
	@PostMapping
	public @ResponseBody ClientsModel createClient(
											@RequestBody(required = false) ClientsModel client
										) throws Exception {
		return clientsService.createClient(client);
	}
	
	@PutMapping
	public @ResponseBody ClientsModel updateClient(
											@RequestBody ClientsModel client
										) throws Exception {
		return clientsService.updateClient(client);
	}
	
    @DeleteMapping(path = "/{id}/")
	public @ResponseBody String deleteClient(
											@PathVariable("id") int id
										) throws Exception {
		return clientsService.deleteClient(id);
	}	
}
