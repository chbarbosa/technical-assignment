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
	 * The store position.
	 */
	private Position position;

	/**
	 * The calculated distance.
	 */
	private double distance;

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
		this.position = new Position(latitude, longitude);
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

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "CalculatedDistanceStore [store=" + store + ", position=" + position + ", distance=" + distance + "]";
	}

}
