package start;
import java.io.BufferedReader;
import java.io.FileReader;

public class Map {
	private int citiesNumber;
	private City[] cities;
	public Map(String fileName) {
		genData(fileName);
	}
	public int getCitysNumber() {
		return citiesNumber;
	}
	
	private void addCity(City city) {
		int indexCity = city.getIndexCity();
		if (indexCity > citiesNumber) {
			throw new LocationNumOutOfBounds();
		}
		else {
			cities[indexCity - 1] = city;
		}
	}
	
	public City getCity(int cityNumber) {
		return cities[cityNumber-1];
	}
	
	private void genData (String fileName) {
		String line;
	     int ni, xi, yi;
	     int cityNum;
	     int x, y;
	     try {
	         FileReader fr = new FileReader(fileName);
	         BufferedReader br = new BufferedReader(fr);
	         br.readLine();
	         br.readLine();
	         br.readLine();
	         line = br.readLine();
	         citiesNumber = Integer.parseInt(line.substring(11, line.length()).trim());
	         br.readLine();
	         br.readLine();
	         cities = new City[citiesNumber];
	         while((line = br.readLine()) != null) {
	             if (line.equals("EOF")) {
	                 break;
	                }
	                ni = 0;
	                while (line.charAt(ni) != ' '){
	                    ni++;
	                }
	                xi = ni + 1;
	                while (line.charAt(xi) != ' '){
	                    xi++;
	                }
	                yi = xi + 1;
	                while (yi != line.length()){
	                    yi++;
	                }
	                cityNum = Integer.parseInt(line.substring(0, ni));
	                x = Integer.parseInt(line.substring(ni + 1, xi));
	                y = Integer.parseInt(line.substring(xi+1, yi));
	                addCity(new City(new Point(x,y), cityNum));
	            }
	            br.close();
	        } catch(Exception e) {
	            System.out.println("Invalid problem source file.");
	            e.printStackTrace();
	        }
	}
	private class LocationNumOutOfBounds extends RuntimeException {
	    LocationNumOutOfBounds ( ) {
	        super("Location Number out of array bounds.");
	    }
	}
}
