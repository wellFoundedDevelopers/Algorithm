'''
빗물을 밑에서 부터 살펴본다.
막힌 부분의 최소 최대를 구하고, 그 사이에 비어있는 곳에 빗물이 고인다.
'''

import sys
h, w = map(int, sys.stdin.readline().split())
blocks = list(map(int, sys.stdin.readline().split()))
blocks_info = [[0 for _ in range(w)] for _ in range(h)]
result = 0

for idx, val in enumerate(blocks):
    for i in range(val):
        blocks_info[i][idx] = 1

for i in range(h):
    mini = w
    maxi = 0
    for j in range(w):
        if blocks_info[i][j] == 1:
            mini = min(mini, j)
            maxi = max(maxi, j)

    for j in range(mini + 1, maxi):
        if blocks_info[i][j] == 0:
            result += 1

print(result)




