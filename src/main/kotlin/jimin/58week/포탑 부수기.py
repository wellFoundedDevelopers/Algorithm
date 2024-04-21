import sys
from collections import deque

n, m, k = map(int, sys.stdin.readline().split())
ground = []
for i in range(n):
    ground.append(list(map(lambda x: [int(x), 0], sys.stdin.readline().split())))

dx = [0, 1, 1, 1, 0, -1, -1, -1]
dy = [1, 1, 0, -1, -1, -1, 0, 1]

real_path = [[] for _ in range(n * m + 1)]


def get_fighter(time):
    global n, m, ground

    mini_power = 5001
    mini_time = -1
    mx = -1
    my = -1
    for i in range(n):
        for j in range(m):
            if ground[i][j][0] == 0:
                continue

            if mini_power > ground[i][j][0]:
                mini_power = ground[i][j][0]
                mini_time = ground[i][j][1]
                mx = i
                my = j
            elif mini_power == ground[i][j][0]:
                if mini_time < ground[i][j][1]:
                    maxi_power = ground[i][j][0]
                    mini_time = ground[i][j][1]
                    mx = i
                    my = j
                elif mini_time == ground[i][j][1]:
                    if mx + my < i + j:
                        maxi_power = ground[i][j][0]
                        mini_time = ground[i][j][1]
                        mx = i
                        my = j
                    elif mx + my == i + j:
                        if my < j:
                            maxi_power = ground[i][j][0]
                            mini_time = ground[i][j][1]
                            mx = i
                            my = j

    if mx != -1 and my != -1:
        ground[mx][my][1] = time

    return [mx, my]


def get_victim():
    global n, m, ground

    maxi_power = 0
    maxi_time = 1002
    mx = -1
    my = -1
    for i in range(n):
        for j in range(m):
            if ground[i][j][0] == 0:
                continue

            if maxi_power < ground[i][j][0]:
                maxi_power = ground[i][j][0]
                maxi_time = ground[i][j][1]
                mx = i
                my = j
            elif maxi_power == ground[i][j][0]:
                if maxi_time > ground[i][j][1]:
                    maxi_power = ground[i][j][0]
                    maxi_time = ground[i][j][1]
                    mx = i
                    my = j
                elif maxi_time == ground[i][j][1]:
                    if mx + my > i + j:
                        maxi_power = ground[i][j][0]
                        maxi_time = ground[i][j][1]
                        mx = i
                        my = j
                    elif mx + my == i + j:
                        if my > j:
                            maxi_power = ground[i][j][0]
                            maxi_time = ground[i][j][1]
                            mx = i
                            my = j

    return [mx, my]


def do_laser(fx, fy, vx, vy):
    global n, m, ground, dx, dy, real_path

    visited = [[[] for _ in range(m)] for _ in range(n)]
    queue = deque([[fx, fy]])
    visited[fx][fy].append([fx, fy])

    isPossible = False
    while queue:
        nx, ny = queue.popleft()

        if nx == vx and ny == vy:
            isPossible = True
            break

        for i in range(0, 8, 2):
            if ground[(nx + dx[i]) % n][(ny + dy[i]) % m][0] != 0 and len(
                    visited[(nx + dx[i]) % n][(ny + dy[i]) % m]) == 0:
                queue.append([(nx + dx[i]) % n, (ny + dy[i]) % m])
                visited[(nx + dx[i]) % n][(ny + dy[i]) % m].extend(visited[nx][ny])
                visited[(nx + dx[i]) % n][(ny + dy[i]) % m].append([(nx + dx[i]) % n, (ny + dy[i]) % m])

    # for i in range(n):
    #     print(visited[i])
    # print()
    if isPossible:
        visited[vx][vy].pop(0)
        visited[vx][vy].pop(-1)
        real_path = visited[vx][vy]

        # print(real_path)


def do_bomb(fx, fy, vx, vy):
    global n, m, ground, dx, dy, real_path

    path = []
    for i in range(0, 8):
        if [(vx + dx[i]) % n, (vy + dy[i]) % m] != [fx, fy] and ground[(vx + dx[i]) % n][(vy + dy[i]) % m][0] != 0:
            path.append([(vx + dx[i]) % n, (vy + dy[i]) % m])

    real_path = path


def get_attack(fx, fy, vx, vy):
    global n, m, ground, dx, dy, real_path

    ground[fx][fy][0] += n + m
    attack = ground[fx][fy][0]
    ground[vx][vy][0] = max(0, ground[vx][vy][0] - attack)
    attack //= 2

    for i in range(n):
        for j in range(m):
            if [i, j] in real_path:
                ground[i][j][0] = max(0, ground[i][j][0] - attack)
            else:
                if [i, j] != [fx, fy] and [i, j] != [vx, vy] and ground[i][j][0] != 0:
                    ground[i][j][0] += 1


for i in range(1, k + 1):
    fx, fy = get_fighter(i)
    vx, vy = get_victim()

    if fx == vx and fy == vy:
        break

    # print(f"fight {fx, fy} {ground[fx][fy]} victim {vx, vy}")
    real_path = [[] for _ in range(n * m + 1)]
    visited = [[False for _ in range(m)] for _ in range(n)]
    visited[fx][fy] = True

    do_laser(fx, fy, vx, vy)
    if len(real_path) == n * m + 1:
        do_bomb(fx, fy, vx, vy)
        # print("domb")

    # print(f"real_path {real_path}")

    get_attack(fx, fy, vx, vy)

    # for i in range(n):
    #     print(ground[i])

    # print()

maxi = 0
for i in range(n):
    for j in range(m):
        maxi = max(maxi, ground[i][j][0])

print(maxi)

