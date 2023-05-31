
package start;

public class Chromosome extends PathWay implements Comparable<Chromosome> {
	private double fitness;

	public Chromosome(int numberCities) {
		super(numberCities);
		this.fitness = 0.0; // Khởi tạo fitness ban đầu là 0

	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public void calculateFitness() {
		fitness = 1.0 / getPathwayLength(); // Ví dụ tính fitness bằng nghịch đảo độ dài của PathWay
		setFitness(fitness);
	}

	public int getNumberGenes() {
		return this.getNumberCities();
	}

	public City getGene(int index) {
		return getCity(index);
	}

	public void setGene(int index, City gene) {
		addCity(gene, index);
	}

	public boolean containsGene(City gene) {
		for (int i = 0; i < getNumberCities(); i++) {
			City currentGene = getGene(i);
			if (currentGene != null && currentGene.equals(gene)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(Chromosome other) {
		if (this.fitness < other.fitness) {
			return -1; // Cá thể hiện tại có fitness nhỏ hơn cá thể other
		} else if (this.fitness > other.fitness) {
			return 1; // Cá thể hiện tại có fitness lớn hơn cá thể other
		} else {
			return 0; // Cá thể hiện tại có fitness bằng cá thể other
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < getNumberCities(); i++) {
			City city = getGene(i);
			sb.append(city.getIndexCity());
			if (i < getNumberCities() - 1) {
				sb.append(" -> ");
			}
		}
		return sb.toString();
	}
}
