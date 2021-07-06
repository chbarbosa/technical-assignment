package com.finder.servingwebcontent;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.finder.servingwebcontent.exception.APIException;
import com.finder.servingwebcontent.exception.FileAccessException;
import com.finder.servingwebcontent.model.CalculatedDistanceStore;
import com.finder.servingwebcontent.model.Store;

/**
 * Test for {@link StoreService}.
 * @author chbarbosa
 *
 */
class StoreServiceTest {

	/**
	 * Instance to be tested.
	 */
	private StoreService storeService;

	@Test
	void testConvertJson() {
		// Data
		this.storeService = new StoreService();

		// Test
		try {
			this.storeService.convertJson(FileHelper.readString("stores.json"));
			assertEquals(4, this.storeService.countRegisteredStores());
		} catch (FileAccessException e) {
			fail("This test can't throw a exception");
		}
	}

	/**
	 * Test the method {@link StoreService#findClosestStores(double, double, int)}.
	 */
	@Test
	void testFindClosestStoresAPIException() {
		// Data
		this.storeService = new StoreService();

		// Test
		assertThrows(APIException.class, () -> {
			this.storeService.findClosestStores(0, 0, 10);
		});
	}

	/**
	 * Test the method {@link StoreService#findClosestStores(double, double, int)}.
	 */
	@Test
	void testFindClosestStores() {
		// Data
		this.storeService = new StoreService();
		this.storeService.setStores(generateStoreDataForTest());

		// Test
		try {
			List<CalculatedDistanceStore> stores = this.storeService.findClosestStores(1d, 1d, 3);
			System.out.println(stores);
			assertEquals(3, stores.size());
			assertEquals("City E", stores.get(0).getStore().getCity());
			assertEquals("City D", stores.get(1).getStore().getCity());
			assertEquals("City C", stores.get(2).getStore().getCity());
		} catch (APIException e) {
			fail("This test can't throw a exception");
		}
	}

	private Optional<List<Store>> generateStoreDataForTest() {
		String todayOpen = "10:00";
		String todayClose = "22:00";
		return Optional.ofNullable(Arrays.asList(
				new Store("City A", "Address A, 234", 10d, 5d, todayOpen, todayClose),
				new Store("City A", "Address F, 432", 9d, 4d, todayOpen, todayClose),
				new Store("City B", "Address B, 234", 9d, 4.5d, todayOpen, todayClose),
				new Store("City C", "Address C, 234", 8d, 4d, todayOpen, todayClose),
				new Store("City D", "Address D, 234", 7d, 3.5d, todayOpen, todayClose),
				new Store("City E", "Address E, 234", 6d, 3d, todayOpen, todayClose)
				));
	}

	@Test
	void testCountRegisteredStores() {
		// Data
		this.storeService = new StoreService();
		// Test 1
		assertEquals(0, this.storeService.countRegisteredStores());

		this.storeService.setStores(generateStoreDataForTest());
		// Test 2
		assertEquals(6, this.storeService.countRegisteredStores());
	}

	@Test
	void testGetStoresPerCityAPIException() {
		// Data
		this.storeService = new StoreService();

		// Test
		assertThrows(APIException.class, () -> {
			this.storeService.findClosestStores(0, 0, 10);
		});
	}

	@Test
	void testGetStoresPerCity() {
		// Data
		this.storeService = new StoreService();
		this.storeService.setStores(generateStoreDataForTest());

		// Test
		try {
			Map<String, Long> storesPerCity = this.storeService.getStoresPerCity();
			assertEquals(2, storesPerCity.get("City A"));
			assertEquals(1, storesPerCity.get("City C"));
			assertEquals(1, storesPerCity.get("City E"));
		} catch (APIException e) {
			fail("This test can't throw a exception");
		}
	}

	@Test
	void testFindStoreByCityEmpty() {
		// Data
		this.storeService = new StoreService();
		this.storeService.setStores(generateStoreDataForTest());

		try {
			// Test A
			List<Store> stores = this.storeService.findStoreByCity("X");
			// Verify
			assertNotNull(stores);
			assertTrue(stores.isEmpty());
		} catch (APIException e) {
			fail("This test can't throw a exception");
		}
	}

	@Test
	void testFindStoreByCity() {
		// Data
		this.storeService = new StoreService();
		this.storeService.setStores(generateStoreDataForTest());

		try {
			// Test A
			List<Store> stores = this.storeService.findStoreByCity("City D");
			// Verify
			assertEquals(1, stores.size());
			assertEquals("City D", stores.get(0).getCity());
			assertEquals(7d, stores.get(0).getLatitude());
			assertEquals(3.5d, stores.get(0).getLongitude());
		} catch (APIException e) {
			fail("This test can't throw a exception");
		}
	}
}
