package start;

public class City extends Point {
	private int indexCity;

	public City(Point point, int indexCity) {
		super(point.getX(), point.getY());
		this.indexCity = indexCity;
	}

	public int getIndexCity() {
		return indexCity;
	}

	@Override
	public double distanceTo(Point point) {
		return super.distanceTo(point);
	}

	public double distanceTo(City city) {
		return super.distanceTo(city);
	}
}
