import sys
from collections import deque

l, n, q = map(int, sys.stdin.readline().split())

chesses = []
knights = []
knights_info = []

origin_health = []

BLANK = 0
TRAP = 1
WALL = 2
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

for i in range(l):
    chesses.append(list(map(int, sys.stdin.readline().split())))

for i in range(n):
    r, c, h, w, k = map(int, sys.stdin.readline().split())
    r -= 1
    c -= 1
    knights.append([r, c, h, w, k])
    origin_health.append(k)

    tmp = []
    for j in range(r, r + h):
        for t in range(c, c + w):
            tmp.append([j, t])
    knights_info.append(tmp)


def is_possible_to_move_knight(num, d):
    global dx, dy, n, l, chesses, knights, knights_info

    queue = deque([num])
    after_info = [[] for _ in range(n)]

    while queue:
        now = queue.popleft()
        nr, nc, nh, nw, nk = knights[now]

        if nk <= 0:
            continue

        if nr + dx[d] + nh > l or nc + dy[d] + nw > l:
            return

        new = []
        for i in range(nr + dx[d], nr + dx[d] + nh):
            for j in range(nc + dy[d], nc + dy[d] + nw):
                if chesses[i][j] == 2:
                    return
                new.append([i, j])

        for i in range(n):
            if i != now and knights[i][4] > 0:
                for j in range(len(knights_info[i])):
                    if knights_info[i][j] in new:
                        queue.append(i)
                        break

        after_info[now] = new

    for i in range(n):
        if len(after_info[i]) != 0:
            knights_info[i] = after_info[i]
            knights[i][0] += dx[d]
            knights[i][1] += dy[d]

            if num != i:
                for t in range(knights[i][0], knights[i][0] + knights[i][2]):
                    for j in range(knights[i][1], knights[i][1] + knights[i][3]):
                        if chesses[t][j] == 1:
                            knights[i][4] -= 1


for j in range(q):
    i, d = map(int, sys.stdin.readline().split())
    i -= 1

    if knights[i][4] <= 0:
        continue

    is_possible_to_move_knight(i, d)

result = 0
for i in range(n):
    if knights[i][4] > 0:
        result += origin_health[i] - knights[i][4]
print(result)



