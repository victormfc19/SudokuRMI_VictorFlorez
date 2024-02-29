package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SudokuInterface extends Remote {

    public void setSIZE(int SIZE) throws RemoteException;
    public int[][] generateSudoku() throws RemoteException;
    public void fillDiagonal(int[][] sudoku) throws RemoteException;
    public void fillSubgrid(int[][] sudoku, int row, int col) throws RemoteException;
    public int[] shuffleArray(int[] array) throws RemoteException;
    public boolean isSafeRow(int[][] sudoku, int row, int num) throws RemoteException;
    public boolean isSafeCol(int[][] sudoku, int col, int num) throws RemoteException;
    public boolean isSafeSubgrid(int[][] sudoku, int startRow, int startCol, int num) throws RemoteException;
    public boolean fillRemaining(int[][] sudoku) throws RemoteException;
    public boolean isSafe(int[][] sudoku, int row, int col, int num) throws RemoteException;
    public void printSudoku() throws RemoteException;

}
