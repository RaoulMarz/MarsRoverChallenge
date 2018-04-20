package roverframework;

public class RoverPosition {
	private Coordinate gridposition;
	private Coordinate MaxBounds;
	private CompassDirection orientation;
	
	public Coordinate getGridposition() {
		return gridposition;
	}

	public void setGridposition(Coordinate gridposition) {
		this.gridposition = gridposition;
	}

	public CompassDirection getOrientation() {
		return orientation;
	}

	public void setOrientation(CompassDirection orientation) {
		this.orientation = orientation;
	}

	public void incrementY() {
		if (gridposition.getY() < getMaxBounds().getY())
			gridposition.setY(gridposition.getY() + 1);
	}
	
	public void decrementY() {
		if (gridposition.getY() > 0)
			gridposition.setY(gridposition.getY() - 1);
	}

	public void incrementX() {
		if (gridposition.getX() < getMaxBounds().getX())
			gridposition.setX(gridposition.getX() + 1);
	}

	public void decrementX() {
		if (gridposition.getX() > 0)
			gridposition.setX(gridposition.getX() - 1);
	}
	
	public void rotateLeft() {
		if (getOrientation() == null)
			return;
		int ordinalOrientation = getOrientation().ordinal();
		ordinalOrientation -= 1;
		if (ordinalOrientation < 0)
			ordinalOrientation = 3;
		setOrientation(CompassDirection.setOrdinal(ordinalOrientation));
	}
	
	public void rotateRight() {
		if (getOrientation() == null)
			return;
		int ordinalOrientation = getOrientation().ordinal();
		ordinalOrientation += 1;
		if (ordinalOrientation >= 4)
			ordinalOrientation = 0;
		setOrientation(CompassDirection.setOrdinal(ordinalOrientation));
	}
	
	public Coordinate getMaxBounds() {
		return MaxBounds;
	}

	public void setMaxBounds(Coordinate maxBounds) {
		MaxBounds = maxBounds;
	}
	
	public RoverPosition(String initString) {
		if (initString != null) {
			String[] positionTokens = initString.split("");
			if (positionTokens != null) {
				
			}
		}
	}
	
	public static CompassDirection parseDirectionSymbol(Character direction) {
		CompassDirection result = null;
		switch (direction) {
		case 'N' : {
			result = CompassDirection.NORTH;
			break;
		}
		case 'E' : {
			result = CompassDirection.EAST;
			break;
		}
		case 'S' : {
			result = CompassDirection.SOUTH;
			break;
		}
		case 'W' : {
			result = CompassDirection.WEST;
			break;
		}
		}
		return result;
	}
	
	public String toString() {
		return gridposition.toString() + " " + orientation.toString();
	}

	public RoverPosition(Coordinate position, CompassDirection orientation) {
		gridposition = position;
		this.orientation = orientation;
		MaxBounds = new Coordinate(999, 999);
	}
	
	public RoverPosition(RoverPosition anotherPosition) {
		gridposition = anotherPosition.getGridposition();
		this.orientation = anotherPosition.getOrientation();
		this.MaxBounds = anotherPosition.getMaxBounds();
	}
	
	public RoverPosition(RoverPosition anotherPosition, char command) {
		gridposition = anotherPosition.getGridposition();
		this.orientation = anotherPosition.getOrientation();
		this.MaxBounds = anotherPosition.getMaxBounds();
	}
}
