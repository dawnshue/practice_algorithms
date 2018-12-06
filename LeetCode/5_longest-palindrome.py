"""
Return the longest palindrome from a string
"""

def longestPalindrome(str):
    if len(str) <= 1:
        return len(str)
    longest = 1
    for s in range(1, len(str) - 1):
        curr = getPalindromeLen(str, s-1, s+1, 1)
        if(curr > longest):
            longest = curr
        curr = getPalindromeLen(str, s, s+1, 0)
        if(curr > longest):
            longest = curr
        if (s + longest/2) > len(str):
            break

def getPalindromeLen(str, a, b, length):
    if(str[a] == str[b]):
        if (a>=1) and (b<len(str)-1):
            return getPalindromeLen(str, a-1, b+1, length+1)
        else:
            return length+1
    return length