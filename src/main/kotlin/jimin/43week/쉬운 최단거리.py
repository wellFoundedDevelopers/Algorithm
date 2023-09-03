from collections import deque


def bfs(x, y):
    global n, m
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]

    queue = deque([])
    result[x][y] = 0
    queue.append([x, y])

    while queue:
        nx, ny = queue.popleft()
        for i in range(4):
            if 0 <= nx + dx[i] < n and 0 <= ny + dy[i] < m:
                if ground[nx + dx[i]][ny + dy[i]] == 1 and result[nx + dx[i]][ny + dy[i]] == -1:
                    result[nx + dx[i]][ny + dy[i]] = result[nx][ny] + 1
                    queue.append([nx + dx[i], ny + dy[i]])


n, m = map(int, input().split())
ground = []
result = [[-1 for _ in range(m)] for _ in range(n)]
x = 0
y = 0
for i in range(n):
    g = list(map(int, input().split()))
    for j in range(m):
        if g[j] == 0:
            result[i][j] = 0
        elif g[j] == 2:
            x = i
            y = j
    ground.append(g)

bfs(x, y)
for i in range(n):
    print(" ".join(map(str, result[i])))
