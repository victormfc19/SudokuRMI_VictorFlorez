package implement;

import Interface.SudokuInterface;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class SudokuImplement extends UnicastRemoteObject implements SudokuInterface {

    private int SIZE;
    private int SUBGRID_SIZE;

    public SudokuImplement() throws RemoteException {
        super(9090);
    }

    @Override
    public void setSIZE(int SIZE) {
        this.SIZE = SIZE;
        this.SUBGRID_SIZE = (int) Math.sqrt(SIZE);
    }

    @Override
    public int[][] generateSudoku() {
        int[][] sudoku = new int[SIZE][SIZE];
        fillDiagonal(sudoku);
        fillRemaining(sudoku);
        return sudoku;
    }


    // Llena la diagonal de la matriz con números aleatorios (ningún número repetido en la misma fila o columna)
    @Override
    public void fillDiagonal(int[][] sudoku) {
        for (int i = 0; i < SIZE; i = i + SUBGRID_SIZE) {
            fillSubgrid(sudoku, i, i);
        }
    }


    // Llena una subgrilla de tamaño SUBGRID_SIZE x SUBGRID_SIZE con números aleatorios
    @Override
    public void fillSubgrid(int[][] sudoku, int row, int col) {

        int[] nums = new int[SIZE];

        for (int i = 0; i < SIZE; i++)
            nums[i] = i + 1;

        nums = shuffleArray(nums);

        int numIndex = 0;
        for (int i = 0; i < SUBGRID_SIZE; i++) {
            for (int j = 0; j < SUBGRID_SIZE; j++) {
                int position = numIndex++;
                sudoku[row + i][col + j] = nums[position];
            }
        }
    }


    // Método para barajar el contenido de un array
    @Override
    public int[] shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }

        return array;
    }


    // Método para verificar si un número ya está en una fila específica
    @Override
    public boolean isSafeRow(int[][] sudoku, int row, int num) {
        for (int col = 0; col < SIZE; col++) {
            if (sudoku[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    // Método para verificar si un número ya está en una columna específica
    public boolean isSafeCol(int[][] sudoku, int col, int num) {
        for (int row = 0; row < SIZE; row++) {
            if (sudoku[row][col] == num) {
                return false;
            }
        }
        return true;
    }


    // Método para verificar si un número ya está en una subgrilla específica
    @Override
    public boolean isSafeSubgrid(int[][] sudoku, int startRow, int startCol, int num) {
        for (int row = 0; row < SUBGRID_SIZE; row++) {
            for (int col = 0; col < SUBGRID_SIZE; col++) {
                if (sudoku[row + startRow][col + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }


    // Método recursivo para rellenar el sudoku de forma válida
    @Override
    public boolean fillRemaining(int[][] sudoku) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (sudoku[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }
        if (isEmpty) {
            return true;
        }
        for (int num = 1; num <= SIZE; num++)
            if (isSafe(sudoku, row, col, num)) {

                sudoku[row][col] = num;
                if (fillRemaining(sudoku))
                    return true;

                sudoku[row][col] = 0;

            }

        return false;
    }


    // Método para verificar si es seguro colocar un número en una celda específica
    @Override
    public boolean isSafe(int[][] sudoku, int row, int col, int num) {
        return isSafeRow(sudoku, row, num) &&
                isSafeCol(sudoku, col, num) &&
                isSafeSubgrid(sudoku, row - row % SUBGRID_SIZE, col - col % SUBGRID_SIZE, num);
    }


    // Método para imprimir la matriz sudoku
    @Override
    public void printSudoku() {
        System.out.println();

        int[][] sudoku = generateSudoku();

        for (int i = 0; i < sudoku.length; i++) {
            if (i % Math.sqrt(sudoku.length) == 0 && i != 0) {
                System.out.println("------------------------");
            }
            for (int j = 0; j < sudoku[i].length; j++) {
                if (j % Math.sqrt(sudoku.length) == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

}
