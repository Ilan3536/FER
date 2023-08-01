package ui.ga;

import java.util.Arrays;
import java.util.Random;

import ui.data.Dataset;
import ui.nn.Layer;
import ui.nn.NeuralNetwork;
import ui.utils.MatrixUtils;

public class GeneticAlgorithm {
	
	private int[] layerSizes;
	private int iter;
	private int popSize;
	private int elitism;
	private double p;
	private double K;
	
	public GeneticAlgorithm(int[] layerSizes, int iter, int popSize, int elitism, double p, double k) {
		this.layerSizes = layerSizes;
		this.iter = iter;
		this.popSize = popSize;
		this.elitism = elitism;
		this.p = p;
		this.K = k;
	}
	
	public Chromosome train(Dataset data) {
		Random rand = new Random();
		
		Chromosome[] population = createPopulation(rand, data.inputSize());
		evaluatePopulation(population, data);
		Arrays.sort(population);
		
		int generation = 0;
		for ( ; generation < iter; generation++) {			
			if (generation % 2000 == 0 && generation != 0) {
				System.out.println(String.format("[Train error @%d]: %.6f", generation, 1 / population[popSize - 1].getFitness()));
			}
			
			Chromosome[] newPopulation = new Chromosome[popSize];
			for (int i = 0; i < elitism; i++) {
				newPopulation[i] = population[popSize - i - 1];
			}
			
			for (int i = elitism; i < popSize; i++) {
				Chromosome parent1 = pickParent(population, rand);
				Chromosome parent2 = pickParent(population, rand);
				while (parent1 == parent2) {
					parent2 = pickParent(population, rand);
				}
				
				Chromosome child = cross(parent1, parent2, data.inputSize());
				mutate(child, rand, data.inputSize());
				
				newPopulation[i] = child;
			}
			population = newPopulation;
			evaluatePopulation(population, data);
			Arrays.sort(population);
		}
		System.out.println(String.format("[Train error @%d]: %.6f", generation, 1 / population[popSize - 1].getFitness()));
		return population[popSize - 1];
	}
	
	private void mutate(Chromosome child, Random rand, int inputSize) {
		Layer[] layers = child.getNn().getLayers();
		
		int layerNum = 0;

		for (Layer l : layers) {
			int layerSize = layerSizes[layerNum];
			for (int i = 0; i < layerSize; i++) {
				double[][] weights = l.getWeights();
				
				double[][] vector = new double[layerSize][weights[0].length];
				for (int j = 0; j < layerSize; j++) {
					for (int o = 0; o < weights[0].length; o++) {
						vector[j][o] = rand.nextDouble() < p
							? rand.nextGaussian() * K												
							: 0.0;
					}
				}
				l.setWeights(MatrixUtils.add(weights, vector));

				vector = new double[layerSize][1];
				for (int j = 0; j < layerSize; j++) {
					vector[j][0] = rand.nextDouble() < p
						? rand.nextGaussian() * K												
						: 0.0;
				}
				l.setBiases(MatrixUtils.add(l.getBiases(), vector));
			}

			layerNum++;
		}
	}
	
	private Chromosome cross(Chromosome parent1, Chromosome parent2, int inputSize) {
		Layer[] layers1 = parent1.getNn().getLayers();
		Layer[] layers2 = parent2.getNn().getLayers();
		Layer[] newLayers = new Layer[layerSizes.length];
				
		int lastSize = inputSize;
		for (int i = 0; i < layerSizes.length; i++) {
			int layerSize = layerSizes[i];
			
			double[][] weights = new double[layerSize][lastSize];
			double[][] weights1 = layers1[i].getWeights();
			double[][] weights2 = layers2[i].getWeights();
			for (int j = 0; j < layerSize; j++) {
				for (int k = 0; k < lastSize; k++) {
					weights[j][k] = (weights1[j][k] + weights2[j][k]) / 2.0;
				}
			}
			
			double[][] biases = new double[layerSize][1];
			double[][] biases1 = layers1[i].getBiases();
			double[][] biases2 = layers2[i].getBiases();
			for (int j = 0; j < layerSize; j++) {
				biases[j][0] = (biases1[j][0] + biases2[j][0]) / 2.0;
			}
			
			newLayers[i] = new Layer(
					layerSize,
					weights,
					biases);
			lastSize = layerSize;
		}
		
		return new Chromosome(new NeuralNetwork(newLayers));
	}

	private Chromosome pickParent(Chromosome[] population, Random rand) {
		double sum = 0;
		for (Chromosome c : population) {
			sum += c.getFitness();
		}
		
		double randomNum = rand.nextDouble() * sum;
		double accumulatedSum = 0;
		
		for (Chromosome c : population) {
			accumulatedSum += c.getFitness();
			if (randomNum <= accumulatedSum) {
				return c;
			}
		}
		return population[population.length - 1];
	}
	
	
	private void evaluatePopulation(Chromosome[] population, Dataset data) {
		for (int i = 0; i < popSize; i++) {
			evaluateIndividual(population[i], data);
		}
	}
	
	private void evaluateIndividual(Chromosome c, Dataset data) {
		c.setFitness(1.0 / c.getNn().computeData(data));
	}
	
	private Chromosome[] createPopulation(Random rand, int inputSize) {
		Chromosome[] nns = new Chromosome[popSize];
		
		for (int i = 0; i < popSize; i++) {
			nns[i] = createNN(rand, inputSize);
		}
		
		return nns;
	}

	private Chromosome createNN(Random rand, int inputSize) {
		Layer[] layers = new Layer[layerSizes.length];
		int lastSize = inputSize;
		
		for (int i = 0; i < layerSizes.length; i++) {
			int layerSize = layerSizes[i];
			layers[i] = new Layer(
					layerSize,
					MatrixUtils.initMatrix(rand, layerSize, lastSize),
					MatrixUtils.initMatrix(rand, layerSize, 1));
			lastSize = layerSize;
		}
		
		return new Chromosome(new NeuralNetwork(layers));
	}
}
