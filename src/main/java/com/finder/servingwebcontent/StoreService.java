package com.finder.servingwebcontent;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.finder.servingwebcontent.exception.APIException;

@Service
public class StoreService {
	/**
	 * The list of stores.
	 */
	private Optional<List<Store>> stores = Optional.empty();

	public void convertJson(String jsonString) {
		this.stores = Optional.ofNullable(StoreHelper.convertJson(jsonString));
		System.out.println(this.stores.get());
	}
	/**
	 * Finds the closest stores to a given position.
	 * @param latitudePosition
	 * @param longitudePostion
	 * @param listSize
	 * @return
	 * @throws APIException when there are no stores
	 */
	public List<Store> findClosestStores(double latitudePosition, double longitudePostion, int listSize) throws APIException{
		List<Store> list = this.stores.orElseThrow(() -> new APIException("No stores registered"));
		return null;
	}
	public int countRegisteredStores() {
		return this.stores.map(List::size).orElse(0);
	}
	public Map<String, Integer> getStoresPerCity(){
		return null;
	}
}
