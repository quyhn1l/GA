package start;

public class ElitistSelection implements PopulationReplacement {
	public Population replace(Population parents, Population offspring) {
		int populationSize = parents.getSize();
		int elitismCount = (int) (populationSize * 0.5); // Select top 10% as elites (adjust percentage as needed)

		// Create a new population to store the next generation
		Population nextGeneration = new Population();

		// Sort the parents and offspring populations by fitness (assuming higher
		// fitness is better)
		parents.sortChromosomes();
		offspring.sortChromosomes();
		// Transfer elites from the parents population to the next generation
		for (int i = populationSize - elitismCount; i < populationSize; i++) {
			Chromosome elite = parents.getChromosome(i);
			nextGeneration.addChromosome(elite);;
		}

		// Fill the remaining population slots with the best individuals from the
		// offspring population
		for (int i = 0; i < populationSize - elitismCount; i++) {
			Chromosome offspringIndividual = offspring.getChromosome(i);
			nextGeneration.addChromosome(offspringIndividual);
		}
		return nextGeneration;
	}
}