// package shue.vangie.interview.problems
/*
Given a function which tells you whether a battle ship exists in the coordinates, 
find how many battleships are on the map.
*/
public class Battleship {

    public static boolean hasBattleship(int[][] map, int a, int b, int x, int y) {
        // Since you will have at most n*m battleships,
        // iterating through battleship coordinates is faster
        
        /*
        What would be the best way to store these coordinates?
        1. list of coordinates
        2. create a map of sum of battleships at each coordinate:
        Will build it using n*m iterations, but retrieving will be O(n)
        Retrieving formula: Sum(x2,y2) - Sum(x1,y2) - Sum(x2,y1) + Sum(x1,y1)
        */
    }

    // Iterative way of counting battleships, time O:n*m
    public static int countBattleshipsIteratively(int[][] map, int x1, int y1, int x2, int y2) {
        int total = 0;
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                total += (Battleship.hasBattleship(map, x, y, x+1, y+1)? 1 : 0);
            }
        }
    }

    // Recursive way of counting battleships
    // Binary tree traversal: 2*T(n/2) + O(1) == O:n
    public static int countBattleships(int[][] map, int a, int b, int x, int y) {
        if(a == x || b == y)
        return 0;

        if(Battleship.hasBattleship(map, a, b, x, y)) {
            if(x == a+1 && y == b+1)
            return 1;
            
            int halfX = (a+x)/2;
            int halfY = (b+y)/2;
            // recursively divide map into 4 pieces
            return Battleship.countBattleships(map, a, b, halfX, halfY)
            + Battleship.countBattleships(map, halfX, halfY, x, y)
            + Battleship.countBattleships(map, a, halfY, halfX, y)
            + Battleship.countBattleships(map, halfX, b, x, halfY);
        }
        return 0;
    }
    
    public static void main(String args[]) {
        
        int n = 2;
        int m = 2;
        int[][] map = new int[n][m];

        System.out.println(Battleship.countBattleships(map, 0, 0, n, m));
    }

}
