package com.api.models.clients;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Clients {
	
	@Min(value = 1, message = "The id cannot be blank", groups = ToUpdate.class)
	private int id;
	
	@NotBlank(message = "name of client cannot be blank", groups = ToCreate.class)
	private String name;
	
	@NotNull(message = "Date of updated cannot be null", groups = ToUpdate.class)
	private Date modified;
	
	@NotNull(message = "Date of created cannot be null", groups = ToCreate.class)
	private Date created;
	
	public Clients() {
		
	}
	
	public Clients(int id) {
		super();
		this.id = id;
	}

	public Clients(String name, Date created) {
		this.name = name;
		this.created = created;
	}
	
	public Clients(int id, String name, Date modified) {
		super();
		this.id = id;
		this.name = name;
		this.modified = modified;
	}

	public Clients(int id, String name, Date modified, Date created) {
		super();
		this.id = id;
		this.name = name;
		this.modified = modified;
		this.created = created;
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
