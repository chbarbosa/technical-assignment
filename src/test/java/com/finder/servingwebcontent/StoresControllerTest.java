package com.finder.servingwebcontent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Test for {@link StoresController}.
 * @author chbarbosa
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
class StoresControllerTest {

	/**
	 * A constant for store path.
	 */
	private static final String STORES_PATH = "/stores";

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetStores() throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders
				.get(STORES_PATH))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testShowStoresByCity() throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders
				.get(STORES_PATH + "/fortal"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testLocateClosestStores() throws Exception {
		this.mockMvc.perform(
				MockMvcRequestBuilders
				.post(STORES_PATH)
				.content("{\"latitude\" : \"15\"}"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
