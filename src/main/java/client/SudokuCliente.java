package client;



//import Interface.SudokuInterface;

import Interface.SudokuInterface;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SudokuCliente {

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {

        int t, opt;
        String menu = "1. Matriz 4x4 \n2. Matriz 9x9 \n3. Matriz 16x16 \n4. Salir";

        do{
            opt = Integer.parseInt(JOptionPane.showInputDialog( null, menu, "Cliente", JOptionPane.INFORMATION_MESSAGE));
            Registry sudokuRegistry = LocateRegistry.getRegistry("127.0.0.1", 9090);
            SudokuInterface sudoku = (SudokuInterface) sudokuRegistry.lookup("Sudoku");

            switch (opt){

                case 1:
                    t = 4;
                    sudoku.setSIZE(t);
                    sudoku.printSudoku();
                    break;

                case 2:
                    t = 9;
                    sudoku.setSIZE(t);
                    sudoku.printSudoku();
                    break;

                case 3:
                    t = 16;
                    sudoku.setSIZE(t);
                    sudoku.printSudoku();
                    break;

                case 4:
                    break;

            }

        }while(opt != 4);


    }
}
