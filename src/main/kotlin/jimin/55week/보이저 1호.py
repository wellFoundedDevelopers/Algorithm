import sys

n, m = map(int, sys.stdin.readline().split())

space = []
for i in range(n):
    space.append(list(sys.stdin.readline()))

x, y = map(lambda x: int(x) - 1, sys.stdin.readline().split())

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]
type_info = ["U", "R", "D", "L"]
U = 0
R = 1
D = 2
L = 3


def bfs(type):
    global n, m, x, y, U, R, D, L
    nx, ny = x, y
    visited = [[[False for _ in range(4)] for _ in range(m)] for _ in range(n)]
    count = 0

    while True:
        count += 1
        visited[nx][ny][type] = True

        if not (0 <= nx + dx[type] < n) or not (0 <= ny + dy[type] < m):
            break

        if visited[nx + dx[type]][ny + dy[type]][type]:
            count = -1
            break

        if space[nx + dx[type]][ny + dy[type]] == "/":
            nx += dx[type]
            ny += dy[type]
            if type == U:
                type = R
            elif type == R:
                type = U
            elif type == D:
                type = L
            else:
                type = D

        elif space[nx + dx[type]][ny + dy[type]] == "\\":
            nx += dx[type]
            ny += dy[type]
            if type == U:
                type = L
            elif type == R:
                type = D
            elif type == D:
                type = R
            else:
                type = U

        elif space[nx + dx[type]][ny + dy[type]] == "C":
            break
        else:
            nx += dx[type]
            ny += dy[type]

    return count

maxi_type = -1
maxi_count = -1
for i in range(4):
    count = bfs(i)

    if count == -1:
        maxi_type = i
        maxi_count = "Voyager"
        break
    else:
        if count > maxi_count:
            maxi_type = i
            maxi_count = count

print(type_info[maxi_type])
print(maxi_count)