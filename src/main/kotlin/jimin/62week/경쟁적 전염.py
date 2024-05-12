import sys

n, k = map(int, sys.stdin.readline().split())
ground = []
for i in range(n):
    ground.append(list(map(int, sys.stdin.readline().split())))

s, x, y = map(int, sys.stdin.readline().split())
x -= 1
y -= 1

virus = [[] for _ in range(1001)]
for i in range(n):
    for j in range(n):
        if ground[i][j] != 0:
            virus[ground[i][j]].append([i, j])


dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

for i in range(s):
    if ground[x][y] != 0:
        break

    for j in range(1, 1001):
        if len(virus[j]) > 0:
            tmp = []
            for vx, vy in virus[j]:
                for k in range(4):
                    if 0 <= vx + dx[k] < n and 0 <= vy + dy[k] < n and ground[vx + dx[k]][vy + dy[k]] == 0:
                        ground[vx + dx[k]][vy + dy[k]] = j
                        tmp.append([vx + dx[k], vy + dy[k]])
            virus[j].extend(tmp)


print(ground[x][y])