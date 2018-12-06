"""
Given a string, 
find the *length* of the longest substring without repeating characters.
"""

"""
Iterate once through string tracking longest substring encountered
"""
def largest(str):
    if len(str) <= 1:
        return len(str)
    
    lStart = 0
    lEnd = 1
    start = 0
    exists[str[0]] = 0
    for end in range(1, len(str)):
        if str[end] in exists:
            if (end - start) > (lEnd - lStart):
                lEnd = end
                lStart = start
            start = exists[str[end]]+1
        exists[str[end]] = end
    if(end - start) > (lEnd - lStart):
        lEnd = end
        lStart = start
    return (lEnd - lStart)
"""
Can be optimized by just keeping track of the max length
"""