package com.finder.servingwebcontent;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class StoreHelper {

	private StoreHelper() {
		super();
	}

	public static List<Store> convertJson(String jsonString) {
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
		JsonArray jsonArray = jsonObject.get("stores").getAsJsonArray();
		return Arrays.asList(gson.fromJson(jsonArray, Store[].class));
	}

}
