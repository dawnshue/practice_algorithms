"""
https://leetcode.com/problems/two-sum/description/
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
"""

def twoSums(nums, target):
    for x in range(0, len(nums)-1):
        for y in range(x, len(nums)):
            if nums[x] < target and nums[y] < target and nums[x] + nums[y] == target:
                return [x, y]

print("Solutions: {}").format(twoSums([2, 7, 11, 15], 9))