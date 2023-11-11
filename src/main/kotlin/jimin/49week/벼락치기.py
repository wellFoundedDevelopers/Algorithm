'''
배낭문제
'''

import sys
n, t = map(int, sys.stdin.readline().split())
subjects = [[]]

for i in range(n):
    k, s = map(int, sys.stdin.readline().split())
    subjects.append([k, s])

dp = [[0 for _ in range(n + 1)] for _ in range(t + 1)]

for i in range(1, n + 1):
    for j in range(1, t + 1):
        if subjects[i][0] > j:
            dp[j][i] = dp[j][i - 1]
        else:
            dp[j][i] = max(dp[j][i - 1], subjects[i][1] + dp[j - subjects[i][0]][i - 1])

print(dp[-1][-1])