package com.finder.servingwebcontent;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.finder.servingwebcontent.exception.APIException;
import com.finder.servingwebcontent.model.CalculatedDistanceStore;
import com.finder.servingwebcontent.model.Position;
import com.finder.servingwebcontent.model.Store;

/**
 * This Controller is responsible for receive and answer all requests for store from the pages.
 * @author chbarbosa
 *
 */
@Controller
public class StoresController {

	/**
	 * The logger for {@link StoresController}.
	 */
	private Logger logger = LoggerFactory.getLogger(StoresController.class.getName());

	/**
	 * A constant for the size limit of closest stores list.
	 */
	private static final int LIST_SIZE_LIMIT = 5;

	/**
	 * The stores service.
	 */
	@Autowired
	private StoreService storeService;

	/**
	 * Gets the number of stores per city.
	 * @param model a holder for model attributes
	 * @return the page to show the cities with the number of stores
	 */
	@GetMapping("/stores")
	public String getStores(Model model) {
		logger.info("Getting the number of registered stores per Cities");
		try {
			int registeredStores = this.storeService.countRegisteredStores();
			Map<String, Long> storesPerCity = this.storeService.getStoresPerCity();
			model.addAttribute("number", registeredStores);
			model.addAttribute("map", storesPerCity);
		} catch (APIException e) {
			logger.error("Error getting stores data", e);
			model.addAttribute("msg", "Impossible to retrieve stores data");
		}
		return "stores-group";
	}

	/**
	 * List all stores in the specified city.
	 * @param city the specified city
	 * @param model a holder for model attributes
	 * @return the page to show the list of stores in a city
	 */
	@GetMapping("/stores/{city}")
	public String showStoresByCity(@PathVariable String city, Model model) {
		logger.info("Looking for registered stores in {}", city);
		try {
			model.addAttribute("city", city);
			List<Store> stores = this.storeService.findStoreByCity(city);
			model.addAttribute("number", stores.size());
			model.addAttribute("stores", stores);
		} catch (APIException e) {
			logger.error("Error getting stores list by city " + city, e);
			model.addAttribute("msg", "Impossible to retrieve stores list of city " + city);
		}
		return "stores-list";
	}

	/**
	 * Finds the closest stores for the given position.
	 * @param informedPosition the informed position
	 * @param model a holder for model attributes
	 * @return the page to shows the closest stores
	 */
	@GetMapping("/stores/geolocation")
	public String locateClosestStores(@ModelAttribute Position informedPosition, Model model) {
		logger.info("Looking for the closest stores for {}", informedPosition);
		try {
			List<CalculatedDistanceStore> stores = this.storeService.findClosestStores(
					informedPosition.getLatitude(),
					informedPosition.getLongitude(),
					LIST_SIZE_LIMIT);
			// This list is already ordered
			model.addAttribute("limit", LIST_SIZE_LIMIT);
			model.addAttribute("stores", stores);
		} catch (APIException e) {
			logger.error("Error getting closest stores data", e);
			model.addAttribute("msg", "Impossible to retrieve closest stores data");
		}
		return "closest-stores";
	}

}