# if 양의 숫자 > 늑대 숫자 : 늑대 전부 잡아먹힘
# else: 양이 전부 잡아먹힘

# . 빈공간 / # 울타리 / v 늑대 / k 양
# 몇 마리의 양과 늑대가 살아남을까?
# 울타리로 막히지 않은 영역에는 양과 늑대가 없고, 양과 늑대는 대각선으로 이동 못함.

import sys
from collections import deque

r, c = map(int, sys.stdin.readline().split())
ground = []
for i in range(r):
    ground.append(list(sys.stdin.readline()))

result_k = 0
result_v = 0
visited = [[False for _ in range(c)] for _ in range(r)]


def bfs(x, y):
    global r, c, ground, result_k , result_v, visited
    queue = deque([[x, y]])

    visited[x][y] = True

    dx = [0, 0, -1, 1]
    dy = [1, -1, 0, 0]

    count_k = 0
    count_v = 0

    if ground[x][y] == 'k':
        count_k += 1
    elif ground[x][y] == 'v':
        count_v += 1

    while queue:
        nx, ny = queue.popleft()

        for i in range(4):
            if 0 <= nx + dx[i] < r and 0 <= ny + dy[i] < c and not visited[nx + dx[i]][ny + dy[i]]:
                if ground[nx + dx[i]][ny + dy[i]] == '#':
                    continue
                elif ground[nx + dx[i]][ny + dy[i]] == 'v':
                    count_v += 1
                    queue.append([nx + dx[i], ny + dy[i]])
                elif ground[nx + dx[i]][ny + dy[i]] == 'k':
                    count_k += 1
                    queue.append([nx + dx[i], ny + dy[i]])
                else:
                    queue.append([nx + dx[i], ny + dy[i]])
                visited[nx + dx[i]][ny + dy[i]] = True

    if count_k > count_v:
        result_k += count_k
    else:
        result_v += count_v


for i in range(r):
    for j in range(c):
        if not visited[i][j] and (ground[i][j] == 'v' or ground[i][j] == 'k'):
            bfs(i, j)


print(result_k, result_v)