package com.finder.servingwebcontent.model;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * A helper for {@link Store}.
 * @author chbarbosa
 *
 */
public class StoreHelper {

	/**
	 * The attribute "stores" from the json with a json array of stores.
	 */
	private static final String ATT_STORES = "stores";
	/**
	 * The {@link Gson} instance to convert json data.
	 */
	private static Gson gson = new Gson();

	/**
	 * Private constructor.
	 */
	private StoreHelper() {
		super();
	}

	/**
	 * Converts the json string to a list of {@link Store} instances.
	 * @param jsonString the json string to converted
	 * @return the converted list of {@link Store}
	 */
	public static List<Store> convertJson(String jsonString) {
		// Convert the json string into a JsonObject instancce
		JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
		// Retrieve json array of stores
		JsonArray jsonArray = jsonObject.get(ATT_STORES).getAsJsonArray();
		// Convert the json array to a list of Stores
		return Arrays.asList(gson.fromJson(jsonArray, Store[].class));
	}

}
