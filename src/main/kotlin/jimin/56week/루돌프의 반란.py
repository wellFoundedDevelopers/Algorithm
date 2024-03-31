import sys

n, m, p, c, d = map(int, sys.stdin.readline().split())  # 게임판 크기, 턴 횟수, 산타 수, 루돌프 충돌 점수, 산타 충돌 점수
rx, ry = map(lambda x: int(x) - 1, sys.stdin.readline().split())
santas = [[] for _ in range(p)]
santa_score = [0 for _ in range(p)]
for i in range(p):
    num, x, y = map(lambda x: int(x) - 1, sys.stdin.readline().split())
    santas[num] = [x, y]

dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, 1, 1, 1, 0, -1, -1, -1]

santa_health = [0 for _ in range(p)]

RODOLF = 0
SANTA = 1
SURVIVAL = p


def get_closest_santa():
    global santas, rx, ry, p, n
    mx = -1
    my = -1
    min_dst = 10000
    min_santa = -1
    for i in range(p):
        sx, sy = santas[i]
        if santa_health[i] != -1:
            dst = (rx - sx) ** 2 + (ry - sy) ** 2
            if min_dst > dst:
                min_dst = dst
                min_santa = i
                mx = sx
                my = sy
            elif min_dst == dst:
                if mx < sx:
                    min_dst = dst
                    min_santa = i
                    mx = sx
                    my = sy
                elif mx == sx:
                    if my < sy:
                        min_dst = dst
                        min_santa = i
                        mx = sx
                        my = sy

    return min_santa


def interact(closest_santa, drt):
    global santas, p, n, SURVIVAL
    sx, sy = santas[closest_santa]

    while True:
        isChanged = False

        for i in range(p):
            if santa_health[i] != -1 and i != closest_santa and sx == santas[i][0] and sy == santas[i][1]:
                santas[i][0] += dx[drt]
                santas[i][1] += dy[drt]
                sx += dx[drt]
                sy += dy[drt]
                closest_santa = i

                if not (0 <= sx < n and 0 <= sy < n):
                    SURVIVAL -= 1
                    santa_health[i] = -1
                else:
                    isChanged = True

        if not isChanged:
            break


def check_collision(who, closest_santa, drt):
    global RODOLF, SANTA, c, d, santa_score, n, santa_health, SURVIVAL, rx, ry
    if who == RODOLF:
        if rx == santas[closest_santa][0] and ry == santas[closest_santa][1]:  # 충돌함
            santa_score[closest_santa] += c
            santas[closest_santa][0] += dx[drt] * c
            santas[closest_santa][1] += dy[drt] * c
            # 기절
            santa_health[closest_santa] = 2

    else:
        if rx == santas[closest_santa][0] and ry == santas[closest_santa][1]:  # 충돌함
            santa_score[closest_santa] += d
            drt = (drt + 4) % 8
            santas[closest_santa][0] += dx[drt] * d
            santas[closest_santa][1] += dy[drt] * d
            # 기절
            santa_health[closest_santa] = 2

    if not (0 <= santas[closest_santa][0] < n and 0 <= santas[closest_santa][1] < n):
        SURVIVAL -= 1
        santa_health[closest_santa] = -1
    else:
        interact(closest_santa, drt)  # 상호작용


def do_rodolf():
    global santas, rx, ry, p, n, RODOLF
    closest_santa = get_closest_santa()
    sx, sy = santas[closest_santa]

    # 루돌프 좌표 이동
    min_dst = 10000
    drt = -1
    for i in range(8):
        if 0 <= rx + dx[i] < n and 0 <= ry + dy[i] < n:
            dst = (rx + dx[i] - sx) ** 2 + (ry + dy[i] - sy) ** 2
            if min_dst > dst:
                min_dst = dst
                drt = i

    rx += dx[drt]
    ry += dy[drt]
    check_collision(RODOLF, closest_santa, drt)


def do_santa():
    global p, n, SANTA, rx, ry
    for i in range(p):
        sx, sy = santas[i]

        if santa_health[i] != 0:
            continue

        # 산타 좌표 이동
        min_dst = (sx - rx) ** 2 + (sy - ry) ** 2
        drt = -1
        for j in range(0, 8, 2):
            if 0 <= sx + dx[j] < n and 0 <= sy + dy[j] < n and [sx + dx[j], sy + dy[j]] not in santas:
                dst = (sx + dx[j] - rx) ** 2 + (sy + dy[j] - ry) ** 2
                # print(f"{i} {j}여기 dst{dst} min_dst{dst} {santas}")
                if min_dst > dst:
                    min_dst = dst
                    drt = j

        if drt != -1:
            santas[i][0] = sx + dx[drt]
            santas[i][1] = sy + dy[drt]
            check_collision(SANTA, i, drt)


def do_game():
    global m, p, SURVIVAL, santa_health, santa_score

    for i in range(m):
        if santa_health.count(-1) == p:
            break
        do_rodolf()
        do_santa()
        for i in range(p):
            if santa_health[i] != -1:
                santa_health[i] = max(0, santa_health[i] - 1)
                santa_score[i] += 1

    print(*santa_score)


do_game()
