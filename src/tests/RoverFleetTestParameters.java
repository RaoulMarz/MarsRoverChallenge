package tests;

import roverframework.Rover;

public class RoverFleetTestParameters {
	public Boolean validate(String input) {
		Rover newRover = new Rover(input);
		return newRover.isValid();
	}
	
}
