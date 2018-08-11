package com.taskrequestapi.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RequestHttpUtil {
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36";
	private static final String CONTENT_TYPE = "application/json; charset=UTF-8";

	private static HttpURLConnection conn;

	public JsonObject request(String url, String method, String TOKEN) {
		JsonObject jsonResponse = null;
		try {
			URL obj = new URL(url);

			conn = (HttpURLConnection) obj.openConnection();
			conn.setRequestMethod(method);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", TOKEN);
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Content-Type", CONTENT_TYPE);

			int responseCode = conn.getResponseCode();

			// if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String response = "";
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response += inputLine;
			}
			try {
				JsonParser parser = new JsonParser();
				jsonResponse = new JsonObject();
				jsonResponse = parser.parse(response).getAsJsonObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonResponse;
	}
}
