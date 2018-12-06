/*
You are given two non-empty linked lists representing two non-negative integers. 
The digits are stored in reverse order and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
*/

package shue.vangie.interview;

import java.io.*;
import java.util.*;

class Solution {

    /*
    If literally dealing with linked list implementation,
    Call list.next() and list.val()
    if(a.val == null)
    return b;
    if(b.val == null)
    return a;
    int carry = 0;
    ListNode head = new ListNode(0);
    ListNode curr = head;
    while(a != null && b != null) {
        int sum = carry + (a!=null ? a : 0) + (b!=null ? b : 0);
        carry = 0;
        if(sum > 9) {
            carry = sum / 10;
            sum = sum - carry*10;
        }
        curr.val = curr.val + sum;
        curr.next = new ListNode(carry);
        curr = curr.next;
    }
    */

  public static List<Integer> add(List<Integer> a, List<Integer> b) {
    if(a.size() == 0)
    return b;
    if(b.size() == 0)
    return a;

    List<Integer> ans = new LinkedList<>();
    List<Integer> smaller = (a.size() < b.size()) ? a : b;
    List<Integer> larger = (smaller == a) ? b : a;

    int carry = 0;
    for(int p = 0; p < smaller.size(); p++) {
      if(a.get(p) < 0 || b.get(p) < 0)
      throw new IllegalArgumentException("Negative value found in list.");

      int add = carry + a.get(p) + b.get(p);
      carry = 0;
      if (add > 9) {
        carry = add / 10;
        add = add - carry * 10;
      }
      ans.add(add);
    }

    if(a.size() != b.size()) {
      for(int p = smaller.size(); p < larger.size(); p++) {
        int sum = carry + larger.get(p);
        carry = 0;
        if(sum > 9) {
          carry = sum / 10;
          sum = sum - carry * 10;
        }
        ans.add(sum);
      }
    }

    if(carry > 0)
    ans.add(carry);

    return ans;

  }

	public static void main (String[] args) {
		List<Integer> a = new LinkedList<>(Arrays.asList(2, 4, 3));
    List<Integer> b = new LinkedList<>(Arrays.asList(5, 6, 4));
    List<Integer> ans = Solution.add(a, b);
    System.out.println(ans);
	}
}


