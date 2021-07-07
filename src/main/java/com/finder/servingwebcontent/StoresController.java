package com.finder.servingwebcontent;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.finder.servingwebcontent.exception.APIException;
import com.finder.servingwebcontent.model.CalculatedDistanceStore;
import com.finder.servingwebcontent.model.Position;
import com.finder.servingwebcontent.model.Store;

@Controller
public class StoresController {

	private Logger logger = LoggerFactory.getLogger(StoresController.class.getName());

	private static final int LIST_SIZE_LIMIT = 5;

	@Autowired
	private StoreService storeService;

	@GetMapping("/stores")
	public String showStores(Model model) {

		try {
			int registeredStores = this.storeService.countRegisteredStores();
			Map<String, Long> storesPerCity = this.storeService.getStoresPerCity();
			model.addAttribute("number", registeredStores);
			model.addAttribute("map", storesPerCity);
		} catch (APIException e) {
			logger.error("Error getting stores data", e);
			model.addAttribute("msg", "Impossible to retrieve stores data");
		}
		return "stores";
	}

	@GetMapping("/stores/{city}")
	public String showStoresByCity(@PathVariable String city, Model model) {
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

	//@RequestMapping(path = "/stores", method = RequestMethod.POST)
	@PostMapping("/stores")
	public String locateClosestStores(@ModelAttribute Position userPosition, Model model) {

		try {
			List<CalculatedDistanceStore> stores = this.storeService.findClosestStores(
					userPosition.getLatitude(),
					userPosition.getLongitude(),
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