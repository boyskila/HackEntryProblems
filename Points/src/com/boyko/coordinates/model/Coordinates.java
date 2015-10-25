package com.boyko.coordinates.model;

public class Coordinates {
	public int x;
	public int y;

	public Coordinates() {
		x = 0;
		y = 0;
	}

	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
