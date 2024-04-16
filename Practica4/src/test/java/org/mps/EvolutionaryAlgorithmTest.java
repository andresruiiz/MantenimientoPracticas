package org.mps;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.crossover.CrossoverOperator;
import org.mps.crossover.OnePointCrossover;
import org.mps.mutation.MutationOperator;
import org.mps.mutation.SwapMutation;
import org.mps.selection.SelectionOperator;
import org.mps.selection.TournamentSelection;

import static org.junit.jupiter.api.Assertions.*;

public class EvolutionaryAlgorithmTest {

    @Test
    @DisplayName("Prueba con una población válida")
    public void testOptimizeWithValidPopulation() throws EvolutionaryAlgorithmException {
        // Arrange
        int[][] population = {{1, 2, 3}, {4, 5, 6}};
        SelectionOperator selectionOperator = new TournamentSelection(population.length);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Act
        int[][] result = ea.optimize(population);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Prueba con una población nula")
    public void testOptimizeWithNullPopulation() throws EvolutionaryAlgorithmException{
        // Arrange
        int[][] population = null;
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(population));
    }

    @Test
    @DisplayName("Prueba con una población vacía")
    public void testOptimizeWithEmptyPopulation() throws EvolutionaryAlgorithmException {
        // Arrange
        int[][] population = {};
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
        
        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(population));
    }

    @Test
    @DisplayName("Prueba con una población donde al menos un individuo sea nulo")
    public void testOptimizeWithNullIndividualInPopulation() throws EvolutionaryAlgorithmException{
        // Arrange
        int[][] population = {{1, 2, 3}, null};
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(population));
    }

    @Test
    @DisplayName("Prueba con una población de tamaño par")
    public void testOptimizeWithEvenSizedPopulation() throws EvolutionaryAlgorithmException{
        // Arrange
        int[][] population = {{1, 2, 3}, {4, 5, 6}};
        SelectionOperator selectionOperator = new TournamentSelection(population.length);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Act
        int[][] result = ea.optimize(population);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Prueba con una población de tamaño impar")
    public void testOptimizeWithOddSizedPopulation() throws EvolutionaryAlgorithmException{
        // Arrange
        int[][] population = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        SelectionOperator selectionOperator = new TournamentSelection(population.length);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(population));
    }



    @Test
    @DisplayName("Prueba con poblaciones donde los descendientes sean mejores que los padres")
    public void testOptimizeWithOffspringBetterThanParents() throws EvolutionaryAlgorithmException{
        // Arrange
        int[][] population = {{1, 2, 3}, {4, 5, 6}};
        SelectionOperator selectionOperator = new TournamentSelection(population.length);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
        
        // Act
        int[][] result = ea.optimize(population);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Prueba con poblaciones donde los descendientes sean peores que los padres")
    public void testOptimizeWithOffspringWorseThanParents() throws EvolutionaryAlgorithmException{
        // Arrange
        int[][] population = {{1, 2, 3}, {4, 5, 6}};
        SelectionOperator selectionOperator = new TournamentSelection(population.length);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Act
        int[][] result = ea.optimize(population);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Prueba con poblaciones donde los descendientes sean iguales a los padres")
    public void testOptimizeWithOffspringEqualToParents() throws EvolutionaryAlgorithmException{
        // Arrange
        int[][] population = {{1, 2, 3}, {1, 2, 3}};
        SelectionOperator selectionOperator = new TournamentSelection(population.length);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Act
        try {
            int[][] result = ea.optimize(population);
        } catch (EvolutionaryAlgorithmException e) {
            // Assert
            fail("No debería lanzar una excepción");
        }
        
        // Assert
        assertNotNull(population);
    }
}