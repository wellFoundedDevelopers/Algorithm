import sys

n, m = map(int, sys.stdin.readline().split())
numbers = []
sum_info = [[0 for _ in range(n + 1)]]

for i in range(n):
    numbers.append(list(map(int, sys.stdin.readline().split())))

for i in range(1, n + 1):
    sub_info = [0]
    tmp = [0]
    for j in range(1, n + 1):
        tmp.append(tmp[-1] + numbers[i - 1][j - 1])
        sub_info.append(sum_info[i - 1][j] + tmp[-1])

    sum_info.append(sub_info)

for i in range(m):
    x1, y1, x2, y2 = map(int, sys.stdin.readline().split())

    print(sum_info[x2][y2] - sum_info[x1 - 1][y2] - sum_info[x2][y1 - 1] + sum_info[x1 - 1][y1 - 1])