/*
https://leetcode.com/problems/zigzag-conversion/description/
Write the code that will take a string and make this conversion 
given a number of rows:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
*/

/*
Approach 1: 
Create a matrix of the zigzag & then iterate through it
You need 1+n-2=(n-1) columns for every n+n-2=(2n-2) characters
(x < 2n-2) ? (x-n+1) : x/2 + (x % (2n-2) > 0 ? 1 : 0)
                        ^ (n-1)*x/(2n-2)
*/


package shue.vangie.algorithms;
import java.lang.*;
public class ZigZag {

    /*
    Approach 2:
    Build your string as a 1D array of length str.length
    String[] ans = new String[str.length];
    int x = 0;
    ans[x] = str[0]
    */
    public static String zigZag(String str, int n) {
        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < n; r++) {
            if(str.length()-1 < r)
            break;

            sb.append(str.charAt(r));
            for(int x = 1; x <= ((str.length-1) / (2*n-2)); x++) {
                if(r != 0 && r != n) {
                    sb.append(str.charAt(r+x*(2*n - 2-r)));
                }
                sb.append(str.charAt(r + x*(2*n - 2)));
            }
            
        }
        return sb.toString();
    }
    
    public static void main(String args[]) {
        String str = "ALONGSTRING";
        System.out.println("zigzag: "+ZigZag.zigZag(str, 4));
    }
}