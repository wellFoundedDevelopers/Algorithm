import sys

# n*n
# 술래는 처음 정중앙.

# 크기, 도망자수, 나무수, 게임수
n, m, h, k = map(int, sys.stdin.readline().split())

runners = []
ground_runners = [[[] for _ in range(n)] for _ in range(n)]
for i in range(m):
    x, y, d = map(lambda x: int(x) - 1, sys.stdin.readline().split())
    runners.append([x, y, d, 0])
    ground_runners[x][y].append(i)

# h개의 나무
# 나무는 도망자와 초기에 겹쳐져 주어질 수 있음.
trees = [[0 for _ in range(n)] for _ in range(n)]
for i in range(h):
    x, y = map(lambda x: int(x) - 1, sys.stdin.readline().split())
    trees[x][y] = 1

# m명의 도망자. 처음 지정된 곳 서있다. 중앙에서 시작 x
# 도망자는 좌우 / 상하 로만 움직이는 2가지 유형
# 좌우 -> 오른쪽, 상하 -> 아래쪽 보고 시작.

center = [n // 2, n // 2, 0]

X = 0
Y = 1
D = 2
DIR = 3

dx = [[0, 0], [1, -1]]
dy = [[1, -1], [0, 0]]
dx_c = [-1, 0, 1, 0]
dy_c = [0, 1, 0, -1]

HORIZONTAL = 0
VERTICAL = 1

DIE = [-1, -1, -1, -1]

num = 1
count = 0  # num이 2번 나오는지 확인
now = 0  # 현재 몇번째인지 num까지 가기 위해
typ = 0

result = 0
survival = m


def do_runner():
    global n, runners, center, trees, X, Y, D, DIR, dx, dy

    for i in range(m):
        # 도망자는 현재 술래와의 거리가 3 이하인 도망자만 움직임.
        # 거리: |x1 - x2| + |y1 - y2|
        if runners[i] == DIE:
            continue

        if abs(runners[i][X] - center[X]) + abs(runners[i][Y] - center[Y]) > 3:
            continue

        # 움직이는 규칙
        # 현재 바라보는 방향으로 1칸 움직인다 했을 때 격자를 벗어나지 X
        # 움직이려는 칸에 술래가 있으면 움직이지 X
        # 움직이려는 칸에 술래가 있지 X -> 해당 칸으로 이동. 나무가 있어도 ㄱㅊ

        nx = runners[i][X] + dx[runners[i][D]][runners[i][DIR]]
        ny = runners[i][Y] + dy[runners[i][D]][runners[i][DIR]]
        if 0 <= nx < n and 0 <= ny < n:
            if not (center[X] == nx and center[Y] == ny):
                ground_runners[runners[i][X]][runners[i][Y]].remove(i)
                runners[i][X] = nx
                runners[i][Y] = ny
                ground_runners[nx][ny].append(i)
        else:
            # 현재 바라보고 있는 방향으로 1칸 움직인다 했을 때 격자를 벗어나는 경우
            # 먼저 방향을 반대로 틀어준다. 이후 바라보고 있는 방향으로 1칸 움직일때, 해당 위치에 술래가 없으면 1칸 앞으로 이동.
            runners[i][DIR] = (runners[i][DIR] + 1) % 2
            nx = runners[i][X] + dx[runners[i][D]][runners[i][DIR]]
            ny = runners[i][Y] + dy[runners[i][D]][runners[i][DIR]]
            if 0 <= nx < n and 0 <= ny < n:
                if not (center[X] == nx and center[Y] == ny):
                    ground_runners[runners[i][X]][runners[i][Y]].remove(i)
                    runners[i][X] = nx
                    runners[i][Y] = ny
                    ground_runners[nx][ny].append(i)


def do_center(t):
    global n, runners, center, trees, X, Y, D, dx_c, dy_c, DIE, count, now, num, result, survival, typ
    # 술래는 처음 위 방향으로 시작해서 달팽이 모양으로 움직임.
    # 만약 끝에 도달하게 되면 다시 거꾸로 중심으로 이동.
    # 다시 중심에 오게 되면 처음처럼 위 방향으로 시작하여 시계방향으로 도는 것을 k턴에 걸쳐 반복.

    # 술래는 1번의 텀 동안 정확히 한 칸 해당하는 방향으로 이동.
    # 이동후 위치가 만약 이동방향이 틀어지는 지점이면, 방향을 바로 틀어줌.

    center[X] += dx_c[center[D]]
    center[Y] += dy_c[center[D]]
    now += 1

    if center[X] == 0 and center[Y] == 0:
        count = 1
        typ = 1
        center[D] = 2
        now = 1
    elif center[X] == n // 2 and center[Y] == n // 2:
        count = -1
        typ = 0
        center[D] = 3
        now = 1

    if typ == 0:  # 처음 시계 방향
        if now == num:
            now = 0
            count += 1
            center[D] = (center[D] + 1) % 4

        if count == 2:
            num += 1
            count = 0
    else:  # 거꾸로 중심으로
        if now == num:
            now = 0
            count += 1
            center[D] = (center[D] - 1) % 4

        if count == 2:
            num -= 1
            count = 0

    # 이동 직후 술래는 턴을 넘기기 전 시야 내에 있는 도망자를 잡게 된다. -> 도망자 사라짐
    # 시야는 현재 바라보고 있는 방향을 기준으로 현재 칸을 포함해서 3칸.
    # 만약 나무가 놓여 있다면, 해당 칸에 있는 도망자는 나무에 가려져 보이지 않게됨.

    die_num = 0
    for i in range(3):
        nx = center[X] + dx_c[center[D]] * i
        ny = center[Y] + dy_c[center[D]] * i

        if not (0 <= nx < n and 0 <= ny < n):
            continue

        if trees[nx][ny] == 1:
            continue

        for j in ground_runners[nx][ny]:
            runners[j] = DIE
            die_num += 1
            survival -= 1

        ground_runners[nx][ny] = []

    # 술래는 현재 턴을 t번째 턴이라고 했을 때, t * 현재 턴에서 잡힌 도망자의 수 만큼 점수 얻음.
    result += t * die_num


# m명의 도망자가 먼저 동시에! 움직이고 -> 술래가 움직이고 (k번 반복)
for i in range(1, k + 1):
    do_runner()
    # print(runners)
    # for j in range(n):
    #     print(ground_runners[j])
    do_center(i)
    # print(center, num, count, now, center[D])
    # print(runners)
    # for j in range(n):
    #     print(ground_runners[j])

    # if survival <= 0:
    #     break

print(result)