package com.finder.servingwebcontent.model;

/**
 * Represents a store.
 * @author chbarbosa
 *
 */
public class Store {
	/**
	 * The city name;
	 */
	private String city;

	/**
	 * The store address.
	 */
	private String addressName;

	/**
	 * Latitude info.
	 */
	private Double latitude;

	/**
	 * Longitude info.
	 */
	private Double longitude;

	/**
	 * The opening time.
	 */
	private String todayOpen;

	/**
	 * The closing time.
	 */
	private String todayClose;

	/**
	 * The default constructor.
	 */
	public Store() {
		super();
	}

	/**
	 * Constructor using all fields.
	 * @param city city name
	 * @param addressName store address.
	 * @param latitude latitude info
	 * @param longitude longitude info
	 * @param todayOpen opening time
	 * @param todayClose closing time
	 */
	public Store(String city, String addressName, Double latitude, Double longitude, String todayOpen,
			String todayClose) {
		super();
		this.city = city;
		this.addressName = addressName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.todayOpen = todayOpen;
		this.todayClose = todayClose;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getTodayOpen() {
		return todayOpen;
	}

	public void setTodayOpen(String todayOpen) {
		this.todayOpen = todayOpen;
	}

	public String getTodayClose() {
		return todayClose;
	}

	public void setTodayClose(String todayClose) {
		this.todayClose = todayClose;
	}

	@Override
	public String toString() {
		return "Store [city=" + city + ", addressName=" + addressName + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}
}
