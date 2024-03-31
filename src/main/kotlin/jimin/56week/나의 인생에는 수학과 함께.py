import sys
from collections import deque

n = int(sys.stdin.readline())
ground = []
for i in range(n):
    ground.append(list(sys.stdin.readline().split()))

MAX = -1
MIN = 1


def bfs(road_type):
    global MAX, MIN, n, ground

    dx = [0, 1]
    dy = [1, 0]

    queue = deque([[0, 0]])
    visited = [[10000 * road_type for _ in range(n)] for _ in range(n)]
    visited[0][0] = int(ground[0][0])

    result = 10000 * road_type
    while queue:
        nx, ny = queue.popleft()

        if nx == n-1 and ny == n-1:
            if road_type == MIN:
                result = min(visited[nx][ny], result)
            else:
                result = max(visited[nx][ny], result)
            break

        for i in range(2):
            if 0 <= nx + dx[i] < n and 0 <= ny + dy[i] < n:
                num = 0
                if ground[nx + dx[i]][ny + dy[i]].isdigit():
                    if ground[nx][ny] == '*':
                        num = visited[nx][ny] * int(ground[nx + dx[i]][ny + dy[i]])
                    elif ground[nx][ny] == '+':
                        num = visited[nx][ny] + int(ground[nx + dx[i]][ny + dy[i]])
                    else:
                        num = visited[nx][ny] - int(ground[nx + dx[i]][ny + dy[i]])
                else:
                    num = visited[nx][ny]

                if road_type == MIN:
                    if visited[nx + dx[i]][ny + dy[i]] > num:
                        visited[nx + dx[i]][ny + dy[i]] = num
                else:
                    if visited[nx + dx[i]][ny + dy[i]] < num:
                        visited[nx + dx[i]][ny + dy[i]] = num

                queue.append([nx + dx[i], ny + dy[i]])

    return result


print(bfs(MAX), bfs(MIN))