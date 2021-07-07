package com.finder.servingwebcontent.model;

public class Position {

	/**
	 * The latitude of the specific coordinate.
	 */
	private double latitude;

	/**
	 * The longitude of the specific coordinate.
	 */
	private double longitude;

	/**
	 * Default constructor.
	 */
	public Position() {
		super();
	}

	/**
	 * Constructor using all fields.
	 * @param latitude latitude of the coordinate
	 * @param longitude longitude of the coordinate
	 */
	public Position(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
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
		return "Position [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
