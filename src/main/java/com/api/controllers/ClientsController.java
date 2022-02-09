package com.api.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.models.clients.Clients;
import com.api.models.clients.ToCreate;
import com.api.models.clients.ToUpdate;
import com.api.services.ClientsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/clients/")
public class ClientsController {
	
	@Autowired
	@Qualifier("clientsService")
	private ClientsService clientsService;

	@GetMapping(path = "/hello/")
	public @ResponseBody String sayHello() throws Exception {
		return clientsService.hello();
	}
	
	@GetMapping
	public @ResponseBody ArrayList<Clients> getClients(){
//		System.out.println("-->");
		return clientsService.getClients();
	}
	
	@PostMapping
	public @ResponseBody Clients createClient(
											@Validated(ToCreate.class) @RequestBody Clients client
										) throws Exception {
		return clientsService.createClient(client);
	}
	
	@PutMapping
	public @ResponseBody Clients updateClient(
											@Validated(ToUpdate.class) @RequestBody Clients client
										) throws Exception {
		return clientsService.updateClient(client);
	}
	
    @DeleteMapping(path = "/{id}/")
	public @ResponseBody ObjectNode deleteClient(
											@PathVariable("id") int id
										) throws Exception {    	
    	String[] msg = {"Deleted client",
    					"Try again - verify id"
    					};
    	
    	ObjectMapper mapper = new ObjectMapper();
    	ObjectNode objectNode = mapper.createObjectNode();
    	
    	objectNode = clientsService.deleteClient(id) ? objectNode.put("message", msg[0]) 
													 : objectNode.put("message", msg[1])
													 ;
		return objectNode;
		
	}
    
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ObjectNode handleMethodArgumentNotValid(
													MethodArgumentNotValidException ex
													) {
    	ObjectMapper mapper = new ObjectMapper();
    	ObjectNode errors = mapper.createObjectNode();
     
       ex.getBindingResult()
       		.getFieldErrors()
       			.forEach(
       					error -> 
       					errors.put(
       								error.getField(),
       								error.getDefaultMessage()
       								));
     
       return errors;
    }
}
