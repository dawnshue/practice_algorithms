import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


// binary_add(string, string) -> string
// binary numbers in string form
// binary_add('10', '1') -> '11'
// binary_add('11', '11') -> '110'
// NOTE: dont cast the strings to ints

public class Solution {
    public static void main(String args[] ) throws Exception {
        String sol = Solution.binary_add("11", "11");
        System.out.println(sol);
    }
    
    public static String binary_add(String a, String b) {
        String ans = "";
        boolean bIsShorter = b.length() < a.length();
        String shorter = bIsShorter ? b : a;
        int lenLong = bIsShorter ? a.length() : b.length();
        if(b.length() != a.length()) {
            while(shorter.length() < lenLong) {
                shorter = '0' + shorter;
            }
            if(bIsShorter) {
                b = shorter;
            } else {
                a = shorter;
            }
        }
        
        int pos = a.length()-1;
        boolean carryOver = false;
        while(pos >= 0) {
            int sum = (carryOver ? 1 : 0) + (a.charAt(pos)=='1' ? 1:0) + (b.charAt(pos)=='1'?1:0);
            if(sum > 1) {
                carryOver = true;
                ans = (sum == 2 ? '0' : '1') + ans;
            } else {
                carryOver = false;
                ans = (sum == 1 ? '1' : '0') + ans;
            }
            pos--;
        }
        if(carryOver){
        ans = "1" + ans;
        }
        return ans;
    }
}