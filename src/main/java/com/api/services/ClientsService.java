package com.api.services;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.api.daos.ClientsDao;
import com.api.models.ClientsModel;

@Service
public class ClientsService {

	public String hello() throws Exception {
//		System.out.println("--");
		return "HELLO!";
	}

	public ClientsModel createClient(ClientsModel client) {
		String name = client.getName();
		Date created = client.getCreated();
		
		if(name != null 
				&& created != null) {
			return ClientsDao.createClient(client);
		}
		else return null;
	}

	public ArrayList<ClientsModel> getClients() {
//		System.out.println("service");
		return ClientsDao.reedClients();
	}

	public ClientsModel updateClient(ClientsModel client) {
		int id = client.getId();
		String name = client.getName();
		Date modified = client.getModified();
		
		if(name != null && id > 0 
				&& modified != null) {
			return ClientsDao.updateClient(client);
		}
		else return null;
	}

	public String deleteClient(int id) {
		boolean ok = ClientsDao.deleteClient(id);
		
		if(ok) return "Se eliminó el cliente";
		else return "No se pudo eliminar el cliente, verifique id";
	}

}
