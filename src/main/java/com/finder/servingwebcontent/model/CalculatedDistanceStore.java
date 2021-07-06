package com.finder.servingwebcontent.model;


/**
 * Represents a store with a calculated distance to a specific coordinate.
 * @author chbarbosa
 *
 */
public class CalculatedDistanceStore {
	/**
	 * The specified {@link Store} instance.
	 */
	private Store store;

	/**
	 * The calculated distance.
	 */
	private double distance;

	/**
	 * The latitude of the specific coordinate.
	 */
	private double latitude;

	/**
	 * The longitude of the specific coordinate.
	 */
	private double longitude;

	/**
	 * Constructor using all fields.
	 * @param store the specified store
	 * @param distance the calculated distance
	 * @param latitude latitude of the coordinate
	 * @param longitude longitude of the coordinate
	 */
	public CalculatedDistanceStore(Store store, double distance, double latitude, double longitude) {
		super();
		this.store = store;
		this.distance = distance;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "CalculatedDistanceStore [store=" + store + ", distance=" + distance + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}

}
