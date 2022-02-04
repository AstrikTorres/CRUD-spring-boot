package com.api.models;

import java.util.Date;

public class ClientsModel {
	
	private int id;
	private String name;
	private Date modified;
	private Date created;
	
	public ClientsModel() {
		
	}
	
	public ClientsModel(int id) {
		super();
		this.id = id;
	}

	public ClientsModel(String name, Date created) {
		this.name = name;
		this.created = created;
	}
	
	public ClientsModel(int id, String name, Date modified) {
		super();
		this.id = id;
		this.name = name;
		this.modified = modified;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}
