package com.api.services;

import org.springframework.stereotype.Service;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ConnectionService {

	public String connection(String data, String path, String auth) throws Exception {
		try {
			System.out.println("\n------------------------------\n");
			URL url = new URL("https://gestopago.portalventas.net/sistema/" + path);
			System.out.println("url: " + url);
			System.out.println("data: " + data);
			if (!auth.isEmpty()) System.out.println("auth: " + auth);

			byte[] postDataBytes = data.toString().getBytes("UTF-8");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			if (!data.isEmpty()) conn.setRequestProperty("Content-Lenght", String.valueOf(postDataBytes.length));
			if (!auth.isEmpty()) conn.setRequestProperty("Authorization", auth);
			conn.setDoOutput(true);
			if (!data.isEmpty()) conn.getOutputStream().write(postDataBytes);

			if (conn.getResponseCode() !=200) {
				System.out.println("HTTP Error code : " + conn.getResponseCode());
				throw new RuntimeException("failed : HTTP Error code : " + conn.getResponseCode());           
			}
			System.out.println("code: " + conn.getResponseCode());
			System.out.println("Content-Type: " + conn.getContentType());

			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String output = br.readLine();
			if (output != null) {
				System.out.println("response: " + output);
				return output;
			}
			conn.disconnect();
			System.out.println("Respuesta vacia");
		}
		catch (Exception e) {
			return "Exception in NetClientGet: " + e;
		}
		return null;
	}

	public String hello() throws Exception {
		System.out.println("--");
		return "HELLO!";
	}
}
