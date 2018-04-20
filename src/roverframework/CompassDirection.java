package roverframework;

public enum CompassDirection { 
	NORTH, EAST, SOUTH, WEST;
	
	public static CompassDirection setOrdinal(int ordinalValue) {
		for (CompassDirection matchOrdinal : CompassDirection.values()) {
			if (ordinalValue == matchOrdinal.ordinal()) {
				CompassDirection result = matchOrdinal;
				return result;
			}
		}
		return null;
	}
	
	public String toString() {
		switch (this) {
		case NORTH: {
			return "N";
		}
		case SOUTH: {
			return "S";
		}
		case EAST: {
			return "E";
		}
		case WEST: {
			return "W";
		}
		default: {
			return "";
		}
		}
	}
};