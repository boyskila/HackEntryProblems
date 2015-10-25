package com.boyko.main;

import java.util.Scanner;

import com.boyko.coordinates.model.Coordinates;
import com.boyko.coordinates.service.CoordinatesCalculator;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String directions = sc.next();
		Coordinates coordinates = new Coordinates();
		//coord.setCoordinates(-10, -10);
		CoordinatesCalculator calculator = new CoordinatesCalculator(coordinates);
		calculator.calculateCoordinates(directions);
		System.out.println(coordinates);
		sc.close();
	}
}
