package start;
import java.util.Random;

public class BitFlipMutation implements Mutation{

	@Override
	public Population mutatePopulation(Population population) {
		int populationSize = population.getSize();
        int numberGenes = population.numberGenes();
      
        Random random = new Random();

        for (int i = 0; i < populationSize; i++) {
            Chromosome chromosome = population.getChromosome(i);
            for (int j = 0; j < numberGenes; j++) {
                if (random.nextDouble() < Const.MUTATION_PROB) {
                    // Perform bit flip mutation
                    City gene1 = chromosome.getGene(j);
                    int randomIndex = random.nextInt(numberGenes);
                    City gene2 = chromosome.getGene(randomIndex);

                    // Swap the two genes
                    chromosome.setGene(j, gene2);
                    chromosome.setGene(randomIndex, gene1);
                }
            }
            chromosome.calculateFitness();
            
        }
        return population;
	}

}
