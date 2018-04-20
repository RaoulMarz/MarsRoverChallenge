package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import roverframework.CompassDirection;
import roverframework.Coordinate;
import roverframework.Rover;

public class TestHarness {
	
	@Test
	public void testRoverModel() {
		CompassDirection testValue = CompassDirection.NORTH;
		Coordinate testValue2 = new Coordinate(5, 5);
		Rover myMoonRover = new Rover("5 5 N"); 
		assertEquals(testValue, myMoonRover.getMapPosition().getOrientation());
		assertEquals(testValue2, myMoonRover.getMapPosition().getGridposition());
	}

}
