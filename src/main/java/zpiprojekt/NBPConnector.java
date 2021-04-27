package zpiprojekt;

import com.google.gson.Gson;
import zpiprojekt.nbp.Price;
import zpiprojekt.nbp.Rate;
import zpiprojekt.nbp.RateTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class NBPConnector {

	private static  URL url;
	private static  HttpURLConnection connection;


	private static void connect(String urlString) throws IOException {
		url = new URL(urlString);
		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoInput(true);
	}

	public static RateTable readJsonTable(String url) throws IOException {
		connect(url);
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
		Gson gson = new Gson();
		return gson.fromJson(br, RateTable.class);
	}

	public static ArrayList<Price> readJsonPrices(String url) throws IOException {
		connect(url);
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
		Gson gson = new Gson();
		return new ArrayList<Price>(Arrays.asList(gson.fromJson(br, Price[].class)));
	}
}



