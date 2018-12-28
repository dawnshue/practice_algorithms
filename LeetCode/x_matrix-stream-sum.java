/** 
Design and implement a matrix processing library that would be used in a stream processing pipeline. The library will consume an large (4-5GB) integer MxN matrix  at start up time and it need to support 2 operations:

void update(int i, int j, int value) : this method will update matrix[i][j] with the new “value”

long sum(int i, int j, int x, int y) : this method will return the sum of all element of the submatrix where the top left of the sub matrix is [i,j] and the bottom right of the sub matrix is [x, y].

Requirement: 
This library expect sum(int i, int j, int x, int y) operation to be called very frequent (thousands of time per second) and update(int i, int j, int value) operation to be called very infrequent (once every hour(s)) so sum() implementation should be as fast as possible

Example: 
=======
matrix:
1 2 3
4 5 6
7 8 9

sum(1, 1, 2, 2) = 28
sum(1, 0, 1, 1) = 9
sum(1, 0, 2, 1) = 24
**/

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  
  public static void main(String[] args) {
    int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    Solution solution = new Solution(matrix);
    
    Solution.printMatrix(matrix);
    System.out.println();
    Solution.printMatrix(solution.getSums());
    System.out.println();
    solution.update(1,1,0);
    Solution.printMatrix(solution.getSums());
  }
  
private int[][] sums;
  
  public Solution(int[][] matrix) {
    initializeSums(matrix);
  }
  public int[][] getSums() {
    return sums;
  }
  public static void printMatrix(int[][] matrix) {
    for(int row = 0; row < matrix.length; row++) {
      for(int col = 0; col < matrix[0].length; col++) {
        System.out.print(matrix[row][col]);
        System.out.print(" ");
      }
      System.out.println();
    }
  }
  
  public int sum(int i, int j, int x, int y) {
    int sum = sums[x][y];
    if(i != 0)
      sum -= sums[i-1][y];
    if(j != 0)
      sum -= sums[x][j-1];
    if(i!= 0 && j!= 0)
      sum += sums[i-1][j-1];
    return sum;
  }
  public void update(int i, int j, int value) {
    int original = sums[i][j];
    if(i != 0)
      original -= sums[i-1][j];
    if(j!=0)
      original -= sums[i][j-1];
    if(i!=0 && j!=0)
      original += sums[i-1][j-1];
    
    int diff = value - original;
    for(int row = i; row < sums.length; row++) {
      for(int col = j; col < sums[0].length; col++) {
        sums[row][col] += diff;
      }
    }
  }
  
  private void initializeSums(int[][] matrix) {
    sums = new int[matrix.length][matrix[0].length];
    for(int row = 0; row < matrix.length; row++) {
      for(int col = 0; col < matrix[0].length; col++) {
        if(row == 0 && col == 0) {
          sums[row][col] = matrix[row][col];
        } else {
          
          sums[row][col] = matrix[row][col];
          if(row != 0)
            sums[row][col] += sums[row-1][col];
          if(col != 0)
            sums[row][col] += sums[row][col-1];
          if(row!=0 && col!=0)
            sums[row][col] = sums[row][col] - sums[row-1][col-1];

        }
      }
    }
  }
}

