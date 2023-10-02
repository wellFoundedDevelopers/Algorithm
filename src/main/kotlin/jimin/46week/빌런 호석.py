'''
부르트포스! 완탐!

층이 1층부터 시작함. 0층을 제외시켜야함.
자리수보다 작게 현재 층이 주어질 수 있음. 자리수만큼 0을 앞에 채워줘야함.

'''



import sys


def dfs(idx, p, new):
    global x, k, result, n

    if idx == k:
        if new != x and new.count('0') != len(new):
            result += 1
        return
    for i in range(10):
        if p >= numbers[int(x[idx])][i] and new + str(i) <= n:
            dfs(idx + 1, p - numbers[int(x[idx])][i], new + str(i))


n, k, p, x = sys.stdin.readline().split()
k = int(k)
p = int(p)
if len(x) < k:
    x = '0' * (k - len(x)) + x
result = 0

numbers = [[0, 4, 3, 3, 4, 3, 2, 3, 1, 2], #0
           [4, 0, 5, 3, 2, 5, 6, 1, 5, 4], #1
           [3, 5, 0, 2, 5, 4, 3, 4, 2, 3], #2
           [3, 3, 2, 0, 3, 2, 3, 2, 2, 1], #3
           [4, 2, 5, 3, 0, 3, 4, 3, 3, 2], #4
           [3, 5, 4, 2, 3, 0, 1, 4, 2, 1], #5
           [2, 6, 3, 3, 4, 1, 0, 5, 1, 2], #6
           [3, 1, 4, 2, 3, 4, 5, 0, 4, 3], #7
           [1, 5, 2, 2, 3, 2, 1, 4, 0, 1], #8
           [2, 4, 3, 1, 2, 1, 2, 3, 1, 0]] #9


dfs(0, p, '')
print(result)