package com.finder.servingwebcontent;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.finder.servingwebcontent.exception.APIException;

/**
 * The service for {@link Store} class.
 * @author chbarbosa
 *
 */
@Service
public class StoreService {
	/**
	 * The list of stores.
	 */
	private Optional<List<Store>> stores = Optional.empty();

	/**
	 * Converts the json string to a list of {@link Store} instances.
	 * @param jsonString
	 */
	public void convertJson(String jsonString) {
		this.stores = Optional.ofNullable(StoreHelper.convertJson(jsonString));
	}
	/**
	 * Finds the closest stores to a given position.
	 * @param latitudePosition the user latitude position
	 * @param longitudePosition the user longitude position
	 * @param listSize the specified size list of the closest stores
	 * @return the found list
	 * @throws APIException when there are no stores
	 */
	public List<Store> findClosestStores(double latitudePosition, double longitudePosition, int listSize) throws APIException{
		List<Store> list = this.stores.orElseThrow(() -> new APIException("No stores registered"));
		return null;
	}
	/**
	 * Counts the registered stores.
	 * @return the total number of stores
	 */
	public int countRegisteredStores() {
		return this.stores.map(List::size).orElse(0);
	}
	/**
	 * Get the quantity of stores per city.
	 * @return the organized data
	 */
	public Map<String, Integer> getStoresPerCity(){
		return null;
	}
	/**
	 * Finds the store list of the specified city.
	 * @param cityName the city name
	 * @return the found list
	 */
	public List<Store> findStoreByCity(String cityName){
		return null;
	}
}
