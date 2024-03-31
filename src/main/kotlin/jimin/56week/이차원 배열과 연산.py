import sys
from collections import defaultdict

r, c, k = map(int, sys.stdin.readline().split())
r -= 1
c -= 1
MAX_SIZE = 100

numbers = [[0 for _ in range(MAX_SIZE)] for _ in range(MAX_SIZE)]

for i in range(3):
    tmp = list(map(int, sys.stdin.readline().split()))
    for j in range(3):
        numbers[i][j] = tmp[j]

cnt_x = 3
cnt_y = 3
cnt = 0
while cnt <= 100:
    if numbers[r][c] == k:
        print(cnt)
        sys.exit()

    cnt += 1
    maxi = 0
    if cnt_x >= cnt_y:
        for i in range(cnt_x):
            counts = defaultdict(int)
            for j in range(cnt_y):
                if numbers[i][j] != 0:
                    counts[numbers[i][j]] += 1
            new_number = sorted(list(counts.items()), key = lambda x: (x[1], x[0]))

            for j in range(0, MAX_SIZE, 2):
                if j < len(new_number) * 2:
                    numbers[i][j] = new_number[j // 2][0]
                    numbers[i][j + 1] = new_number[j // 2][1]
                else:
                    numbers[i][j] = 0
                    numbers[i][j + 1] = 0

            maxi = max(maxi, min(len(new_number) * 2, MAX_SIZE))
        cnt_y = maxi
    else:
        for j in range(cnt_y):
            counts = defaultdict(int)
            for i in range(cnt_x):
                if numbers[i][j] != 0:
                    counts[numbers[i][j]] += 1
            new_number = sorted(list(counts.items()), key = lambda x: (x[1], x[0]))

            for i in range(0, MAX_SIZE, 2):
                if i < len(new_number) * 2:
                    numbers[i][j] = new_number[i // 2][0]
                    numbers[i + 1][j] = new_number[i // 2][1]
                else:
                    numbers[i][j] = 0
                    numbers[i + 1][j] = 0

            maxi = max(maxi, min(len(new_number) * 2, MAX_SIZE))
        cnt_x = maxi

print(-1)