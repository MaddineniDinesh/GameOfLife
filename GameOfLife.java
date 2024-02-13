package com.assignment.fcs;

import java.util.Arrays;
import java.util.Scanner;

public class GameOfLife {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("********Game Of Life*********");
        boolean check = true;
        do {
            System.out.println("1.Overview");
            System.out.println("2.Play");
            System.out.println("3.Exit");
            System.out.println("Enter choice...");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("The mathematician Conway imagined a game, called game of life, which\r\n"
                            + "considered cells that are susceptible to reproduce, disappear, or survive when they obey certain\r\n"
                            + "rules. These cells are represented by elements on a grid of squares, where a grid has an arbitrary\r\n"
                            + "size. Thus, each cell (except those on the boundaries of the grid) is surrounded by eight squares\r\n"
                            + "that contain other cells. The rules are stated as follows:\r\n"
                            + "1. Survival: Each cell that has two or three adjacent cells survives until the next generation.\r\n"
                            + "2. Death: Each cell that has at least four adjacent cells disappears (or dies) by overpopulation.\r\n"
                            + "Also, each cell that has at most one adjacent cell dies by isolation.\r\n"
                            + "3. Birth: Each empty square (i.e., dead cell) that is adjacent to exactly three cells gives birth\r\n"
                            + "to a new cell for the next generation.\r\n"
                            + "It is worth noting that all births and deaths occur at the same time during a generation.");
                    break;
                case 2:
                    System.out.println("Play");
                    int rows, columns;
                    System.out.println("Enter number of rows...");
                    rows = sc.nextInt();
                    System.out.println("Enter number of columns...");
                    columns = sc.nextInt();
                    int[][] board = initializeBoard(rows, columns);
                    int[][] temp=new int[rows][columns];
                    int[][] initial=new int[rows][columns];
                    initial=board;
                    int[][] des = null;
                    int generation = 0;
                    System.out.println("Generation....." + generation);
                    display(board);
                    for (int i = 0; i < Integer.MAX_VALUE; i++) {
                        generation++;
                        System.out.println("Generation....." + generation);
                        GameOfLife gof = new GameOfLife();
                        int[][] finalBoard = gof.gameOfLife(board);
                        if (Arrays.deepEquals(finalBoard, board) || Arrays.deepEquals(finalBoard, des)|| Arrays.deepEquals(finalBoard,temp)|| Arrays.deepEquals(initial,finalBoard)) {
                            display(finalBoard);
                            System.out.println("Stable Configuration reached. Exiting...");
                            break;
                        }
                        display(finalBoard);
                        board = finalBoard;
                        if (i % columns == 0 && i > 1) {
                            des = finalBoard;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exit");
                    check = false;
                    break;
                default:
                    System.out.println("Enter correct choice");
            }
        } while (check);
        sc.close();
    }

    private int[][] gameOfLife(int[][] board) {
        int rows = board.length;
        int columns = board[0].length;
        int[][] finalBoard = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int count = checkAdjacentValues(board, i, j);
                if (board[i][j] == 0 && count == 3) {
                    finalBoard[i][j] = 1; // Birth
                } else if (board[i][j] == 1 && (count < 2 || count > 3)) {
                    finalBoard[i][j] = 0; // Death
                } else {
                    finalBoard[i][j] = board[i][j]; // Survival
                }
            }
        }
        return finalBoard;
    }

    private int checkAdjacentValues(int[][] board, int row, int col) {
        int count = 0;
        int numRows = board.length;
        int numCols = board[0].length;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i == row && j == col)
                    continue;
                if (i >= 0 && i < numRows && j >= 0 && j < numCols) {
                    if (board[i][j] == 1)
                        count++;
                }
            }
        }
        return count;
    }

    private static int[][] initializeBoard(int rows, int columns) {
        int[][] board = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = (int) (Math.random() * 2);
            }
        }
        return board;
    }

    private static void display(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.printf("%2d", board[i][j]);
            }
            System.out.println(" ");
        }
    }
}
