package start;

public class PathWay {
	private City[] thePathway;
	
	public PathWay(int numberCities) {
		thePathway = new City[numberCities];
	}
	
	public void addCity(City c, int index) {
		thePathway[index] = c;
	}
	
	public City getCity(int index) {
		return thePathway[index];
	}
	
	public double getPathwayLength() {
        double length = 0;
        for (int i = 0 ; i < thePathway.length-1 ; i++) {
            length += getCity(i).distanceTo(getCity(i+1));
        }
        
        length += getCity(thePathway.length - 1).distanceTo(getCity(0));
        
        return length;
    }
	public boolean contains (City c) {
        for (City city : thePathway) {
            if (city != null && c.getIndexCity() == city.getIndexCity()) {
                return true;
            }
        }
        return false;
    }
	
	public int getNumberCities() {
		return thePathway.length;
	}
	
	public City[] getPathway() {
        return thePathway;
    }
}
