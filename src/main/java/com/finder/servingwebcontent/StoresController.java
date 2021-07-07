package com.finder.servingwebcontent;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.finder.servingwebcontent.exception.APIException;
import com.finder.servingwebcontent.model.Store;

@Controller
public class StoresController {

	private Logger logger = LoggerFactory.getLogger(StoresController.class.getName());

	@Autowired
	private StoreService storeService;

	@GetMapping("/stores")
	public String greeting(Model model) {

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
	public String details(@PathVariable String city, Model model) {
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

}