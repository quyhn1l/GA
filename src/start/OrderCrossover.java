package start;

import java.util.Random;

public class OrderCrossover implements Crossover {

    @Override
    public Population breed(Population parents, int offspringPopulationSize) {
        Population offspringPopulation = new Population();

        while (offspringPopulation.getSize() < offspringPopulationSize) {
            Chromosome[] selectedParents = selectDistinctParents(parents);
            Chromosome parent1 = selectedParents[0];
            Chromosome parent2 = selectedParents[1];
            Chromosome[] offspring = performCrossover(parent1, parent2);

            offspringPopulation.addChromosome(offspring[0]);
            offspringPopulation.addChromosome(offspring[1]);
            
        }

        return offspringPopulation;
    }

    private Chromosome[] performCrossover(Chromosome parent1, Chromosome parent2) {
        int numberGenes = parent1.getNumberGenes();
        Random random = new Random();
        int crossoverPoint1 = getRandomPosition(random, numberGenes);
        int crossoverPoint2 = getRandomPosition(random, numberGenes);

        if (crossoverPoint1 > crossoverPoint2) {
            int temp = crossoverPoint1;
            crossoverPoint1 = crossoverPoint2;
            crossoverPoint2 = temp;
        }

        Chromosome[] offspring = new Chromosome[2];
        offspring[0] = new Chromosome(numberGenes);
        offspring[1] = new Chromosome(numberGenes);

        // Copy segment between crossover points from parent1 to offspring1
        for (int i = crossoverPoint1; i <= crossoverPoint2; i++) {
            City gene = parent1.getGene(i);
            offspring[0].setGene(i, gene);
        }

        // Copy remaining unused numbers from parent2 to offspring1, wrapping around the list
        int offspringIndex = (crossoverPoint2 + 1) % numberGenes;
        for (int i = crossoverPoint2 + 1; i < crossoverPoint2 + numberGenes + 1; i++) {
            int parentIndex = i % numberGenes;
            City gene = parent2.getGene(parentIndex);
            if (!offspring[0].containsGene(gene)) {
                offspring[0].setGene(offspringIndex, gene);
                offspringIndex = (offspringIndex + 1) % numberGenes;
            }
        }

        // Repeat for offspring2 with the parent's role reversed
        for (int i = crossoverPoint1; i <= crossoverPoint2; i++) {
            City gene = parent2.getGene(i);
            offspring[1].setGene(i, gene);
        }

        offspringIndex = (crossoverPoint2 + 1) % numberGenes;
        for (int i = crossoverPoint2 + 1; i < crossoverPoint2 + numberGenes + 1; i++) {
            int parentIndex = i % numberGenes;
            City gene = parent1.getGene(parentIndex);
            if (!offspring[1].containsGene(gene)) {
                offspring[1].setGene(offspringIndex, gene);
                offspringIndex = (offspringIndex + 1) % numberGenes;
            }
        }

        return offspring;
    }

    private Chromosome selectParent(Population parents) {
        Random random = new Random();
        int index = random.nextInt(parents.getSize());
        return parents.getChromosome(index);
    }

    private Chromosome[] selectDistinctParents(Population parents) {
        Chromosome parent1 = selectParent(parents);
        Chromosome parent2;

        do {
            parent2 = selectParent(parents);
        } while (parent2 == parent1);

        return new Chromosome[]{parent1, parent2};
    }

    private int getRandomPosition(Random random, int numberGenes) {
        return random.nextInt(numberGenes);
    }
}
