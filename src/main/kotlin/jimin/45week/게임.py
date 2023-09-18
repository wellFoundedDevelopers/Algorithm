'''
0-1 bfs를 몰라서 찾아봄
https://nicotina04.tistory.com/168
'''

from collections import deque

n = int(input())
dangers = []
for i in range(n):
    dangers.append(list(map(int, input().split())))
m = int(input())
deaths = []
for i in range(m):
    deaths.append(list(map(int, input().split())))

ground = [[0 for _ in range(501)] for _ in range(501)]
SAFE = 0
DANGER = 1
DEATH = -1
for dan in dangers:
    x1, y1, x2, y2 = dan
    if x1 > x2:
        x1, x2 = x2, x1
    if y1 > y2:
        y1, y2 = y2, y1
    for x in range(x1, x2 + 1):
        for y in range(y1, y2 + 1):
            ground[x][y] = DANGER

for dea in deaths:
    x1, y1, x2, y2 = dea
    if x1 > x2:
        x1, x2 = x2, x1
    if y1 > y2:
        y1, y2 = y2, y1
    for x in range(x1, x2 + 1):
        for y in range(y1, y2 + 1):
            ground[x][y] = DEATH

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
mini = -1
queue = deque([[0, 0, 0]])
visited = [[0 for _ in range(501)] for _ in range(501)]
visited[0][0] = 1
while queue:
    nx, ny, nn = queue.popleft()
    if nx == 500 and ny == 500:
        mini = nn
        break
    for i in range(4):
        if 0 <= nx + dx[i] <= 500 and 0 <= ny + dy[i] <= 500 and not visited[nx + dx[i]][ny + dy[i]]:
            if ground[nx + dx[i]][ny + dy[i]] == SAFE:
                visited[nx + dx[i]][ny + dy[i]] = 1
                queue.appendleft([nx + dx[i], ny + dy[i], nn])
            elif ground[nx + dx[i]][ny + dy[i]] == DANGER:
                visited[nx + dx[i]][ny + dy[i]] = 1
                queue.append([nx + dx[i], ny + dy[i], nn + 1])

print(mini)
