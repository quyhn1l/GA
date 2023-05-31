package start;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		Map map = new Map("C:\\Users\\DELL\\eclipse-workspace\\GAforTSP\\eil51.tsp");

		ParentSelector parentSelector = new TournamentSelection(Const.TOURNAMENT_SIZE);
		Crossover crossover = new OrderCrossover();
		Mutation mutation = new BitFlipMutation();
		PopulationReplacement replacement = new ElitistSelection();

		Population population = new Population();
		population.createRandomPopulation(map, Const.POPULATION_SIZE);
		population.sortChromosomes();


		// Initialize the parent selector
		for (int generation = 0; generation <= Const.MAX_GENERATIONS; generation++) {
			Population parents = new Population();
			for (int i = 0; i < Const.PARENT_POOL_SIZE; i++) {
				Chromosome chromosome = parentSelector.getParent(population);
				parents.addChromosome(chromosome);

			}

			Population offsprings = crossover.breed(parents, Const.PARENT_POOL_SIZE);

			mutation.mutatePopulation(offsprings);

			population = replacement.replace(population, offsprings);
			population.sortChromosomes();
			if (generation % Const.REPORT_INTERVAL == 0) {
                System.out.println("Generation: " + generation);
                System.out.println(parents);
                if (generation == Const.MAX_GENERATIONS) {
                	System.out.println(population.getBestChromosome());
                }
                System.out.println("---------------------------------------------------------------------------------");
            }

		}
	}

}
