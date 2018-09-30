"""
Problem: https://leetcode.com/problems/n-queens/description/
The n queens puzzle is the problem of placing n queens on an n-by-n chessboard
such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle.
Each solution contains a distinct board configuration of the n-queens placement, 
where 'Q' and '.' both indicate a queen and an empty space respectively.
"""

"""
Thought process:
Since it is an n-by-n board with n queens, each line must only have 1 queen.
It may improve efficiency to have a board of invalid positions to check against
Or mathematical check: for position (r,c) to be valid, the previous positions
(x,y) such that y != c && y != c - |x-r| && y != c + |x-r|

"""

# solutions stores all complete & valid boards
# board[r] indicates for row r, the queen is in column board[r]
# invalids[c] indicates for column c, the queen is in row invalids[c]
# row is the current row that you are attempting to place a queen
# size is the size of the board
def getConfigurations(solutions, board, invalids, row, size):
    print("board:{}, invalids:{}".format(board, invalids))
    if size == 0:
        return
    if board ==  None:
        board = [0] * (size+1)
        invalids = [0] * (size+1)
    for c in range(1, size+1):
        if isValid(board, invalids, row, c):
            newboard = list(board)
            newboard[row] = c
            newinvalids = list(invalids)
            newinvalids[c] = row
            if(row == size):
                solutions.append(newboard)
                return
            else:
                getConfigurations(solutions, newboard, newinvalids, row+1, size)

def isValid(board, invalids, row, col):
    if invalids[col] != 0:
        return False
    for r in range(1, row):
        if board[r] == col - (row - r) or board[r] == col + (row - r):
            return False
    return True

n = 4
solutions = []
getConfigurations(solutions, None, None, 1, n)
print("Solutions: {}".format(len(solutions)))
for s in solutions:
    print("{}".format(s))
