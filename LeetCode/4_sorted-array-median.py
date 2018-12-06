"""
There are two sorted arrays nums1 and nums2 of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
"""
import math

def median(nums1, nums2):
    m = len(nums1)
    n = len(nums2)
    get_avg = ((m + n) % 2 == 0)
    median_pos = int(math.ceil(float(m + n)/2)) - 1
    #print("median pos: {}, {}, {}".format(m+n,(m+n)/2,median_pos))
    m_pos = 0
    n_pos = 0
    for x in range(median_pos):
        #print("{}, {}, {}, {}".format(m_pos, n_pos, nums1[m_pos], nums2[n_pos]))
        if(nums1[m_pos] < nums2[n_pos]):
            m_pos += 1
        else:
            n_pos += 1
    if(nums1[m_pos] < nums2[n_pos]):
        median = nums1[m_pos]
        m_pos += 1
    else:
        median = nums2[n_pos]
        n_pos += 1
    
    if get_avg:
        if(nums1[m_pos] < nums2[n_pos]):
            median = float(median + nums1[m_pos]) / 2
        else:
            median = float(median + nums2[n_pos]) / 2
    return median

def log_median(nums1, nums2):
    m = len(nums1)
    n = len(nums2)
    

nums1 = [1, 4]
nums2 = [2, 4]
print("{}".format(median(nums1, nums2)))
"""
But this does not satisfy the time constraint
"""