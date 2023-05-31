package start;

public class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double distanceTo(Point point) {
		if (point == null) {
			throw new IllegalArgumentException("Invalid point: null");
		}

		int xDistance = Math.abs(this.getX() - point.getX());
		int yDistance = Math.abs(this.getY() - point.getY());
		double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
		return distance;
	}

}
