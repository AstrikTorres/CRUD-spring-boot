package com.api.services;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.api.daos.ClientsDao;
import com.api.models.clients.Clients;

@Service
public class ClientsService {

	public String hello() throws Exception {
//		System.out.println("--");
		return "HELLO!";
	}

	public Clients createClient(Clients client) {
		String name = client.getName();
		Date created = client.getCreated();
		
		if(
			name != null 
			&& created != null
		) 
		return ClientsDao.createClient(client);
		
		return null;
	}

	public ArrayList<Clients> getClients() {
//		System.out.println("service");
		return ClientsDao.readClients();
	}

	public Clients updateClient(Clients client) {
		int id = client.getId();
		String name = client.getName();
		Date modified = client.getModified();
		
		if(
			name != null && id > 0 
			&& modified != null
		) 
		return ClientsDao.updateClient(client);
		
		return null;
	}

	public boolean deleteClient(int id) {
		
		return ClientsDao.deleteClient(id)  ? true
											: false
											;
	}

}
