package com.finder.servingwebcontent;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.finder.servingwebcontent.exception.APIException;

/**
 * A web mock test for {@link StoresController} to check the exception scenarios.
 * @author chbarbosa
 *
 */
@WebMvcTest(StoresController.class)
class StoresControllerTest {

	/**
	 * A constant for store path.
	 */
	private static final String STORES_PATH = "/stores";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StoreService storeService;

	@Test
	void testGetStoresAPIException() throws Exception {
		when(storeService.countRegisteredStores()).thenReturn(3);
		when(storeService.getStoresPerCity()).thenThrow(APIException.class);
		this.mockMvc.perform(
				MockMvcRequestBuilders
				.get(STORES_PATH))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().string(containsString("Impossible to retrieve stores data")))
				.andExpect(view().name("stores-group"));
	}

	@Test
	void testShowStoresByCityAPIException() throws Exception {
		when(storeService.findStoreByCity("fortal")).thenThrow(APIException.class);
		this.mockMvc.perform(
				MockMvcRequestBuilders
				.get(STORES_PATH + "/fortal"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().string(containsString("Impossible to retrieve stores list of city")))
				.andExpect(view().name("stores-list"));
	}

	@Test
	void testLocateClosestStoresAPIException() throws Exception {
		when(storeService.findClosestStores(5d, 12d, 5)).thenThrow(APIException.class);
		this.mockMvc.perform(
				MockMvcRequestBuilders
				.get(STORES_PATH + "/geolocation")
			    .param("latitude", "5")
			    .param("longitude", "12"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().string(containsString("Unable to retrieve the closest stores")))
				.andExpect(view().name("closest-stores"));
	}

}
