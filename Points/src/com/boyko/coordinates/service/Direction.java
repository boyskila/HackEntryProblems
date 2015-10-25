package com.boyko.coordinates.service;

import com.boyko.coordinates.model.Coordinates;

public class Direction {
	public static void defaultDirection(char sign, Coordinates coord) {
		switch (sign) {
		case '>':
			coord.x++;
			break;
		case '<':
			coord.x--;
			break;
		case 'v':
			coord.y++;
			break;
		case '^':
			coord.y--;
			break;
		}
	}
	public static void reverse(char sign, Coordinates coord) {
		switch (sign) {
		case '>':
			coord.x--;
			break;
		case '<':
			coord.x++;
			break;
		case 'v':
			coord.y--;
			break;
		case '^':
			coord.y++;
			break;
		}
	}
}
