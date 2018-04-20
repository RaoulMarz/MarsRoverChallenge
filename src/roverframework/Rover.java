package roverframework;

public class Rover {
	private RoverPosition mapPosition = null;
	private char lastCommand;

	public Rover(RoverPosition position) {
		this.mapPosition = position;
	}

	public Rover(String input) {
		String[] values = input.split("\\s+");
		int tokenindex = 0;
		int posX = 0, posY = 0;
		CompassDirection roverDirection = CompassDirection.NORTH;
		for (String inputToken : values) {
			if (tokenindex < 2) {
				int numvalue = Integer.valueOf(inputToken);
				if (numvalue >= 0) {
					if (tokenindex == 0)
						posX = numvalue;
					if (tokenindex == 1)
						posY = numvalue;
				}
			} else {
				char inputOrientation = inputToken.charAt(0);
				roverDirection = RoverPosition.parseDirectionSymbol(inputOrientation);
				if (roverDirection == null) {
					mapPosition = null;
					return;
				}
				Coordinate roverPos = new Coordinate(posX, posY);
				mapPosition = new RoverPosition(roverPos, roverDirection);
			}
			tokenindex += 1;
			if (tokenindex >= 3)
				break;
		}
	}
	
	public void setBounds(Coordinate mapBounds) {
		if (mapPosition != null)
			mapPosition.setMaxBounds(mapBounds);
	}
	
	public boolean isValid() {
		return (mapPosition != null);
	}

	public void applyCommand(char command) {
		RoverPosition resultPosition = getPositionFromCommand(command);
		if (resultPosition != null)
			mapPosition = resultPosition;
	}

	private RoverPosition getPositionFromCommand(char command) {
		RoverPosition newposition = null;
		lastCommand = command;
		if ((command == 'M') || (command == 'L') || (command == 'R')) {
			lastCommand = command;
			switch (command) {
			case 'M': {
				newposition = commandForward(mapPosition);
				break;
			}
			case 'L': {
				newposition = commandRotate(mapPosition, command);
				break;
			}
			case 'R': {
				newposition = commandRotate(mapPosition, command);
				break;
			}
			}
		}
		return newposition;
	}

	public RoverPosition getMapPosition() {
		return mapPosition;
	}

	public char getLastCommand() {
		return lastCommand;
	}
	
	public String toString() {
		if (mapPosition == null)
			return "";
		else
			return mapPosition.toString();
	}

	private RoverPosition commandForward(RoverPosition fromPosition) {
		RoverPosition result = new RoverPosition(fromPosition);
		switch (fromPosition.getOrientation()) {
		case NORTH: {
			result.incrementY();
			break;
		}
		case EAST: {
			result.incrementX();
			break;
		}
		case SOUTH: {
			result.decrementY();
			break;
		}
		case WEST: {
			result.decrementX();
			break;
		}
		}
		return result;
	}

	private RoverPosition commandRotate(RoverPosition fromPosition, char command) {
		RoverPosition result = new RoverPosition(fromPosition);
		switch (command) {
		case 'L': {
			result.rotateLeft();
			break;
		}
		case 'R': {
			result.rotateRight();
			break;
		}
		}
		return result;
	}
}
