package start;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Population {
private List<Chromosome> chromosomes;
    
    public Population() {
    	chromosomes = new ArrayList<>();
    }
    
    public void addChromosome(Chromosome chromosome) {
    	chromosomes.add(chromosome);
    	chromosome.calculateFitness();
    }
    
    public List<Chromosome> getChromosomes() {
        return chromosomes;
    }
    
    public Chromosome getChromosome(int index) {
        return chromosomes.get(index);
    }
    
    public int getSize() {
        return chromosomes.size();
    }
    
    public boolean containsChromosome(Chromosome chromosome) {
    	return chromosomes.contains(chromosome);
    }
    
    public void createRandomPopulation(Map map, int populationSize) {
        while (chromosomes.size() < populationSize) {
        	 createRandomChromosome(map);
        }
    }
    public void createRandomChromosome(Map map) {
        Random random = new Random();
        int numCities = map.getCitysNumber();
        Chromosome randomChromosome = new Chromosome(numCities);
        
        List<City> availableCities = new ArrayList<>();
        for (int i = 1; i <= numCities; i++) {
            availableCities.add(map.getCity(i));
        }
        
        for (int i = 0; i < numCities; i++) {
            int randomIndex = random.nextInt(availableCities.size());
            City city = availableCities.get(randomIndex);
            randomChromosome.addCity(city, i);
            availableCities.remove(randomIndex);
        }
        
        if (!containsChromosome(randomChromosome)) {
            addChromosome(randomChromosome);
        }
        randomChromosome.calculateFitness();
    }
    
    public void sortChromosomes() {
        Collections.sort(chromosomes);
    }
    
    public Chromosome getBestChromosome() {
        return Collections.max(chromosomes);
    }

    public Chromosome getWorstChromosome() {
        return Collections.min(chromosomes);
    }
    
    public double getAverageFitness() {
        double totalFitness = 0.0;
        
        for (Chromosome chromosome : chromosomes) {
            totalFitness += chromosome.getFitness();
        }
        
        return totalFitness / chromosomes.size();
    }
    public int numberGenes() {
        if (chromosomes.isEmpty()) {
            return 0;
        }
        
        return chromosomes.get(0).getNumberGenes();
    }
    
    @Override
    public String toString() {
    	return "Best chromosome: " + getBestChromosome().getFitness() + "\n"+"Pathway length: " +getBestChromosome().getPathwayLength() + "\n"+ "Average fitness: " + this.getAverageFitness();
    }
  
}
