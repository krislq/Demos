package com.krislq.blow.snow;

/**
 * 
 * @author <a href="mailto:kris@krislq.com">Kris.lee</a>
 * @since Jun 20, 2013
 * @version 1.0.0
 */
public class Coordinate {
	public int x;
	public int y;

	public Coordinate(int newX, int newY) {
		x = newX;
		y = newY;
	}

	// public boolean equals(Coordinate other) {
	// if (x == other.x && y == other.y) {
	// return true;
	// }
	// return false;
	// }

	@Override
	public String toString() {
		return "Coordinate: [" + x + "," + y + "]";
	}
}
