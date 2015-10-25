package com.boyko.coordinates.service;

import com.boyko.coordinates.model.Coordinates;

public class CoordinatesCalculator {
	private static final Character WRAPPER_SIGN = '~';

	private class StringWrapper {
		private String input;

		public StringWrapper(String input) {
			this.input = input;
		}

		public int calculateWrappedString(int index, Coordinates coord) {
			return calculateWrappedStringRecursievly(index, input, coord);
		}

		private int calculateWrappedStringRecursievly(int index, String str, Coordinates coord) {
			char sign = str.charAt(++index);
			Direction.reverse(sign, coord);
			return isWrapper(sign) ? index : calculateWrappedStringRecursievly(index, str, coord);
		}
	}

	private Coordinates coordinates;

	public CoordinatesCalculator(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public void calculateCoordinates(String input) {
		int index = 0;
		calculateCoordinatesRecursievly(index, input, coordinates);
	}

	private Coordinates calculateCoordinatesRecursievly(int index, String input, Coordinates coord) {
		char sign = input.charAt(index);
		if (isWrapper(sign)) {
			StringWrapper wrapper = new StringWrapper(input);
			index = wrapper.calculateWrappedString(index, coord);
		}
		Direction.defaultDirection(sign, coord);
		index++;
		return index == input.length() ? coord : calculateCoordinatesRecursievly(index, input, coord);
	}

	private boolean isWrapper(char sign) {
		return sign == WRAPPER_SIGN;
	}
}
