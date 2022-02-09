package com.api.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.api.models.clients.Clients;
import com.api.utill.MyConnectionDB;

public class ClientsDao {

	public static Clients createClient(Clients newClient) {
		MyConnectionDB connection = new MyConnectionDB();
    	PreparedStatement ps = null;
    	
		try(Connection conn = connection.getConnection()) {
    		String query = "INSERT INTO clientes (`name`, `created`) VALUES (?, ?)";
    		
			ps = conn.prepareStatement(query);
				ps.setString(1, newClient.getName());
				ps.setObject(2, newClient.getCreated());

			if(ps.executeUpdate() == 1) {
				System.out.println("saved client :D");
	    		String query2 = "SELECT * FROM clientes WHERE id = (SELECT MAX(id) from clientes)";
	    		
	    		ps = conn.prepareStatement(query2);
	    		ResultSet rs = ps.executeQuery();
	    		
	    		if (rs.next()) {
	    			Clients savedClient = new Clients(
																rs.getInt("id"),
																rs.getString("name"),
																rs.getDate("modified"),
																rs.getDate("created")
															  );
	    			return savedClient;
				}
				rs.close();			
			}
			
		} catch (SQLException e) {
			System.err.println(e);
		}
		return newClient;
	}

	public static ArrayList<Clients> readClients() {
		ArrayList<Clients> clients = null;
		
		MyConnectionDB connection = new MyConnectionDB();
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
		try(Connection conn = connection.getConnection()) {
			clients = new ArrayList<Clients>();
    		String query = "SELECT * FROM crud_prueba.clientes";
    		
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Clients client = new Clients(
														rs.getInt("id"),
														rs.getString("name"),
														rs.getDate("modified"),
														rs.getDate("created")
													  );
				clients.add(client);
			}
			rs.close();
			
		} catch (SQLException e) {
			System.err.println(e);
		}
		return clients;
	}
	
	public static Clients updateClient(Clients client) {
		MyConnectionDB connection = new MyConnectionDB();
    	PreparedStatement ps = null;

		try(Connection conn = connection.getConnection()) {
    		String query = "UPDATE `crud_prueba`.`clientes` "
    						+ "SET `name` = ?, `modified` = ? WHERE (`id` = ?)";
    		
			ps = conn.prepareStatement(query);
				ps.setString(1, client.getName());
				ps.setObject(2, client.getModified());
				ps.setInt(3, client.getId());
				
			if(ps.executeUpdate() == 1) {
				System.out.println("updated client :D");
	    		String query2 = "SELECT * FROM clientes WHERE id = ?";
	    		
	    		ps = conn.prepareStatement(query2);
					ps.setInt(1, client.getId());
					
	    		ResultSet rs = ps.executeQuery();
	    		while (rs.next()) {
					client.setId(rs.getInt("id"));
					client.setName(rs.getString("name"));
					client.setModified(rs.getDate("modified"));
					client.setCreated(rs.getDate("created"));
				}
				rs.close();			
			}			
		} catch (SQLException e) {
			System.err.println(e);
		}
		return client;
	}

	public static boolean deleteClient(int id) {
		MyConnectionDB connection = new MyConnectionDB();
    	PreparedStatement ps = null;

		try(Connection conn = connection.getConnection()) {
    		String query = "DELETE FROM `crud_prueba`.`clientes` WHERE (`id` = ?)";
    		
			ps = conn.prepareStatement(query);
				ps.setInt(1, id);
			
			return ps.executeUpdate() == 1  ? true 
											: false
											;
			
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

}
