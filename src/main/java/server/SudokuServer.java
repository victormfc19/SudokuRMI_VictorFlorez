package server;

import implement.SudokuImplement;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SudokuServer {

    public static void main(String[] args) throws RemoteException {

        Registry reg = LocateRegistry.createRegistry(9090);
        SudokuImplement sudoku = new SudokuImplement();

        reg.rebind("Sudoku", sudoku);
        System.out.println("Servidor iniciado..");
    }
}
