package com.taskrequestapi.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.taskrequestapi.models.Header;
import com.taskrequestapi.models.Task;
import com.taskrequestapi.models.TaskExecuted;

public class RequestHttpUtil {
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36";
	private static final String CONTENT_TYPE = "application/json; charset=UTF-8";

	private static HttpURLConnection conn;

	public TaskExecuted request(Task task) {
		TaskExecuted taskExecuted = new TaskExecuted();
		JsonObject jsonResponse = null;
		try {
			URL obj = new URL(task.getUrl());

			conn = (HttpURLConnection) obj.openConnection();
			conn.setRequestMethod(task.getMethod());
			conn.setDoInput(true);
			conn.setDoOutput(true);
			for (Header header : task.getHeader()) {
				conn.setRequestProperty(header.getKey(), header.getValue());
			}

			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Content-Type", CONTENT_TYPE);

			int responseCode = conn.getResponseCode();
			if (responseCode == 200) {
				taskExecuted.setErro(false);
			} else {
				taskExecuted.setErro(true);
			}
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
				taskExecuted.setResult(jsonResponse.toString());
			} catch (Exception e) {
				e.printStackTrace();
				taskExecuted.setResult(e.toString());
			}
			// }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			taskExecuted.setResult(e.toString());
		}
		Timestamp times = new Timestamp(new Date().getTime());
		taskExecuted.setDate(times);
		return taskExecuted;
	}
}
