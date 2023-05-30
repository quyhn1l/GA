package start;

import java.util.Random;

public class TournamentSelection implements ParentSelector {

    private int tournamentSize;

    public TournamentSelection(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    @Override
    public Chromosome getParent(Population population) {
    	Population tournamentPopulation = new Population();
        Random random = new Random();
        int populationSize = population.getSize();
        boolean[] selectedIndices = new boolean[populationSize];

        while (tournamentPopulation.getSize() < tournamentSize) {
            int randomIndex = random.nextInt(populationSize);
            if (!selectedIndices[randomIndex]) {
                selectedIndices[randomIndex] = true;
                Chromosome chromosome = population.getChromosome(randomIndex);
                tournamentPopulation.addChromosome(chromosome);
            }
        }

        return tournamentPopulation.getBestChromosome();
    }
}
