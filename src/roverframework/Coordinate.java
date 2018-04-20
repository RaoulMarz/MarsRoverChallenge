package roverframework;

public class Coordinate {
	private int x, y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void readInput(String input) {
		String[] values = input.split("\\s+");
		int tokenindex = 0;
		for (String inputToken : values) {
			int numvalue = Integer.valueOf(inputToken);
			if (numvalue >= 0) {
				if (tokenindex == 0)
					setX(numvalue);
				else
					setY(numvalue);
			}
			tokenindex += 1;
			if (tokenindex >= 2)
				break;
		}
	}
	
	public String toString() {
		return Integer.toString(x) + " " + Integer.toString(y);
	}
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
