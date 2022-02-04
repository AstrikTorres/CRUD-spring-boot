package com.api.testControllers;

public class JsonData {
	int idServicio = 0;
	int idProducto = 0;
	String referencia = null;
	String telefono = null;
	String horaLocal = null;
	String upc = null;

	public JsonData(int idServicio, 
					int idProducto, 
					String referencia, 
					String telefono, 
					String horaLocal,
					String upc
					) {
		super();
		this.idServicio = idServicio;
		this.idProducto = idProducto;
		this.referencia = referencia;
		this.telefono = telefono;
		this.horaLocal = horaLocal;
		this.upc = upc;
	}
	
	

	public JsonData(int idServicio, 
					int idProducto, 
					String telefono, 
					String horaLocal, 
					String upc
					) {
		super();
		this.idServicio = idServicio;
		this.idProducto = idProducto;
		this.telefono = telefono;
		this.horaLocal = horaLocal;
		this.upc = upc;
	}



	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getHoraLocal() {
		return horaLocal;
	}

	public void setHoraLocal(String horaLocal) {
		this.horaLocal = horaLocal;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}
	
	

}
