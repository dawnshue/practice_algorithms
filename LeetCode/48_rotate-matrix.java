package shue.vangie.algorithms;

/*
Rotate a matrix/image (int[n][n]) by 90 degrees.
We assume matrix must be a square?
*/
import java.io.*;

public class RotateMatrix {
    public static void rotateMatrix(int[][] input, int[][] output, int x1, int y1, int x2, int y2) {
        if(x1 > x2)
        return;
        
        if ((x1 == x2 && y1 == y2)) {
            output[x1][y1] = input[x1][y1];
            return;
        }
        
        for(int i = 0; i < (x2-x1); i++) {
            output[x2-i][y1] = input[x1][y1+i];
            output[x1][y1+i] = input[x1+i][y2];
            output[x1+i][y2] = input[x2][y2-i];
            output[x2][y2-i] = input[x2-i][y1];
        }
        rotateMatrix(input, output, x1+1, y1+1, x2-1, y2-1);
    }
    
    public static int[][] rotateMatrix90(int[][] matrix) {
        if(matrix.length != matrix[0].length)
        throw new IllegalArgumentException("Cannot rotate non-square matrix: "+matrix.length+" by "+matrix[0].length);
        if(matrix.length == 1)
        return matrix;
        
        int[][] output = new int[matrix.length][matrix.length];
        RotateMatrix.rotateMatrix(matrix, output, 0, 0, matrix.length-1, matrix.length-1);
        return output;
    }
    
    public static void printMatrix(int[][] matrix) {
        for(int a = 0; a < matrix.length; a++) {
            for(int b = 0; b < matrix[0].length; b++) {
                System.out.print(matrix[a][b]+" ");
            }
            System.out.println();
        }
    }
    
    public static void main(String args[]) {
        int n = 4;
        int[][] matrix = new int[n][n];
        for(int a = 0; a < n; a++) {
            for(int b = 0; b < n; b++) {
                matrix[a][b] = a+b;
            }
        }

        System.out.println("Before matrix: ");
        RotateMatrix.printMatrix(matrix);
        System.out.println("After matrix: ");
        RotateMatrix.printMatrix(RotateMatrix.rotateMatrix90(matrix));
    }

    // Solution that rotates in place
}
