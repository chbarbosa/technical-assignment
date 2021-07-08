package com.finder.servingwebcontent;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.finder.servingwebcontent.exception.APIException;
import com.finder.servingwebcontent.model.CalculatedDistanceStore;
import com.finder.servingwebcontent.model.Store;
import com.finder.servingwebcontent.model.StoreHelper;

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

	public void setStores(Optional<List<Store>> stores) {
		this.stores = stores;
	}
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
	 * @param sizeLimit the specified size list of the closest stores
	 * @return the found list
	 * @throws APIException when there are no stores
	 */
	public List<CalculatedDistanceStore> findClosestStores(double latitudePosition, double longitudePosition, int sizeLimit) throws APIException {
	    List<CalculatedDistanceStore> list = getStores().stream()
	    	.map(s -> new CalculatedDistanceStore(s, this.calculatesDistanceToStore(latitudePosition, longitudePosition, s), latitudePosition, longitudePosition))
	    	.sorted(Comparator.comparing(CalculatedDistanceStore::getDistance))
	    	.collect(Collectors.toList());
		if (list.size() <= sizeLimit) {
			return list;
		}
		return list.subList(0, sizeLimit);
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
	 * @throws APIException when there are no stores
	 */
	public Map<String, Long> getStoresPerCity() throws APIException{
		return getStores().stream()
				  .collect(Collectors.groupingBy(Store::getCity, TreeMap::new, Collectors.counting()));
	}
	/**
	 * Finds the store list of the specified city.
	 * @param cityName the city name
	 * @return the found list
	 * @throws APIException when there are no stores
	 */
	public List<Store> findStoreByCity(String cityName) throws APIException{
		Predicate<Store> eqCityName =  s -> s.getCity().equals(cityName);
		return getStores().stream().filter(eqCityName).collect(Collectors.toList());
	}
	/**
	 * Calculates the distance from a point to a specific store.
	 * @param latitude latitude reference
	 * @param longitude longitude reference
	 * @param store specified store
	 * @return the found distance in kilometers
	 */
	private double calculatesDistanceToStore(double latitude, double longitude, Store store) {
		Double storeLat = store.getLatitude();
		Double storeLon = store.getLongitude();
		if ((latitude == storeLat) && (longitude == storeLon)) {
			return 0;
		} else {
			double theta = longitude - storeLon;
			double dist = Math.sin(Math.toRadians(latitude)) * Math.sin(Math.toRadians(storeLat))
					+ Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(storeLat))
							* Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			// in kilometers
			return dist * 1.609344;
		}
	}
	/**
	 * Retrieves the stores list.
	 * @return the list
	 * @throws APIException if there are no stores
	 */
	private List<Store> getStores() throws APIException {
		return this.stores.orElseThrow(() -> new APIException("No stores registered"));
	}
}
