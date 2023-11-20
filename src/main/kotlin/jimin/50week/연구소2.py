import sys
from collections import deque
from itertools import combinations

n, m = map(int, sys.stdin.readline().split())

ground = []
for i in range(n):
    ground.append(list(map(int, sys.stdin.readline().split())))

mini = 100_000_000


def bfs(viruses):
    global n, mini, ground
    queue = deque(viruses)

    visited = [[0 for _ in range(n)] for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if ground[i][j] == 1:
                visited[i][j] = -1

    for x, y in viruses:
        visited[x][y] = 1

    dx = [0, 0, -1, 1]
    dy = [1, -1, 0, 0]

    while queue:
        nx, ny = queue.popleft()

        for i in range(4):
            if 0 <= nx + dx[i] < n and 0 <= ny + dy[i] < n and visited[nx + dx[i]][ny + dy[i]] == 0:
                queue.append([nx + dx[i], ny + dy[i]])
                visited[nx + dx[i]][ny + dy[i]] = visited[nx][ny] + 1

    can = True
    for i in range(n):
        for j in range(n):
            if visited[i][j] == 0:
                can = False
                break

    if not can:
        return

    maxi = 0
    for v in visited:
        maxi = max(maxi, max(v))

    mini = min(maxi - 1, mini)


possible = []
for i in range(n):
    for j in range(n):
        if ground[i][j] == 2:
            possible.append([i, j])


for combi in combinations(possible, m):
    bfs(list(combi))

if mini == 100_000_000:
    print(-1)
else:
    print(mini)


