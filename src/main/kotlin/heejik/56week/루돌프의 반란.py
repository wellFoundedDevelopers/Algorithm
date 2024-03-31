from dataclasses import dataclass

# 상, 상우, 우, 하우, 하, 하좌, 좌, 상좌
# rudolf_move_pos = [[-1, 0], [-1, 1], [0, 1], [1, 1], [1, 0], [1, -1], [-1, -1]]

# 상 우 하 좌
santa_move_pos = [[-1, 0], [0, 1], [1, 0], [0, -1]]


# 산타는 보드 위에, 루돌프는 따로 저장
@dataclass
class Santa:
    number: int
    faint_count: int = 0  # 기절 판 수
    score: int = 0  # 점수


def calc_distance(rx, ry, sx, xy):
    return pow(rx - sx, 2) + pow(ry - xy, 2)


def startGame():
    for i in range(n):
        for j in range(n):
            if board[i][j] is not None:
                board[i][j].faint_count = max(board[i][j].faint_count - 1, 0)
    # print("루돌프의 x:", rudolf[0], "루돌프의 y:", rudolf[1])
    # for i in board:
    #     print(i)
    moveRudolf()
    moveSanta()
    # print("루돌프의 x:", rudolf[0], "루돌프의 y:", rudolf[1])
    # for i in board:
    #     print(i)
    currentBest = (3 ** 2 + 5 ** 2, (-3, -3))

    # print(turn)
    is_end = True
    for i in range(n):
        for j in range(n):
            if board[i][j] is not None:
                board[i][j].score += 1
                is_end = False

    return is_end


def moveRudolf():
    rx, ry = rudolf[:2]
    min_distance = 10 ** 5
    dx = -1
    dy = -1

    for i in range(n):
        for j in range(n):
            if board[i][j] is None: continue
            dis = calc_distance(rx, ry, i, j)
            # print("i:", i)
            # print("j:", j)
            # print("dis:", dis)
            # print()
            if dis <= min_distance:
                min_distance = dis
                dx = 1 if i > rx else 0 if i == rx else -1
                dy = 1 if j > ry else 0 if j == ry else -1
    # print("dx", dx)
    # print("dy", dy)
    rudolf[0] += dx
    rudolf[1] += dy
    rudolf[2] = [dx, dy]

    # 루돌프가 이동했는데 산타가 있다면
    if board[rudolf[0]][rudolf[1]] is not None:
        board[rudolf[0]][rudolf[1]].score += c
        crash(back_direction=rudolf[2], dis=c)


def moveSanta():
    rx = rudolf[0]
    ry = rudolf[1]
    visited = []
    santa_pos = [[] for _ in range(p)]
    for i in range(n):
        for j in range(n):
            if board[i][j] is not None:
                santa = board[i][j]
                # print(santa_pos)
                santa_pos[santa.number] = [i, j]
    for i, j in filter(lambda x: len(x) != 0, santa_pos):
        if [i, j] in visited: continue
        if board[i][j] is None: continue  # 탈락
        if board[i][j].faint_count != 0: continue  # 기절
        santa = board[i][j]
        board[i][j] = None  # 기존 자리 삭제
        min_dis = calc_distance(rx, ry, i, j)  # 기존 거리
        dir = [0, 0]  # 변경될 dx, dy
        for idx in range(4):  # 상 하 좌 우
            nx = i + santa_move_pos[idx][0]
            ny = j + santa_move_pos[idx][1]
            if nx not in range(n) or ny not in range(n): continue
            if board[nx][ny] is not None: continue  # 해당 자리에 이미 산타가 있다면 무시
            dis = calc_distance(rx, ry, nx, ny)  # 거리 계산
            # print("i:", i, "j:", j, "dis: ", dis)
            if dis < min_dis:  # 거리 비교
                # print("dkjdkdk idx:", idx, "i:", i, "j:", j)
                min_dis = dis
                dir = santa_move_pos[idx]  # 최신화
        if board[i + dir[0]][j + dir[1]] is None:
            visited.append([i + dir[0], j + dir[1]])
            # print("i + dir[0]: ", i + dir[0], "j + dir[1]: ", j + dir[1])
            board[i + dir[0]][j + dir[1]] = santa

        if rx == i + dir[0] and ry == j + dir[1]:
            visited.append([i + dir[0], j + dir[1]])
            # 산타가 움직였는데 루돌프와 충돌한다면
            board[rx][ry] = santa
            crash(back_direction=[-dir[0], -dir[1]], dis=d)
            santa.score += d


def crash(back_direction, dis):
    # 충돌된 산타의 위치
    rx = rudolf[0]
    ry = rudolf[1]
    board[rx][ry].faint_count = 2
    nx = rudolf[0]
    ny = rudolf[1]
    for _ in range(dis):
        nx += back_direction[0]
        ny += back_direction[1]
    # 밀려났는데 범위를 벗어나면
    if nx not in range(n) or ny not in range(n):
        board[rx][ry] = None
        return
    # 밀려난만큼 이동
    # 이동했을 때 비어있지 않으면
    if board[nx][ny] is not None:
        # 상호작용
        interact(sx=nx, sy=ny, dir=back_direction, santa=board[rx][ry])
        board[rx][ry] = None
    else:
        # 산타 갈아끼우기
        board[nx][ny] = board[rx][ry]
        board[rx][ry] = None


def interact(sx, sy, dir, santa):
    # print('hihi')
    nx = sx
    ny = sy
    # 충돌된 산타
    # santa.faint_count = turn
    now_santa = santa
    while True:
        tmp = board[nx][ny]
        board[nx][ny] = now_santa
        nx += dir[0]
        ny += dir[1]
        # 범위 나가면 끝
        if nx not in range(n) or ny not in range(n):
            break
        # 이동 후 비어있으면 충돌된 산타를 넣으면 됨
        if board[nx][ny] is None:
            board[nx][ny] = tmp
            break
        now_santa = tmp


n, m, p, c, d = 0, 0, 0, 0, 0
rudolf: list = list()
board: list = list()
turn = 0


def solve():
    global rudolf, board, n, m, p, c, d, turn
    n, m, p, c, d = map(int, input().split())
    board = [[None for _ in range(n)] for _ in range(n)]
    rx, ry = map(lambda x: int(x) - 1, input().split())
    santa_list = [] * p
    # 좌표, 방향
    rudolf = [rx, ry, [0, 0]]
    for i in range(p):
        number, sx, sy = map(lambda x: int(x) - 1, input().split())
        santa = Santa(number=number)
        board[sx][sy] = santa
        santa_list.append(santa)

    for _ in range(m):
        turn += 1
        is_game_end = startGame()
        if is_game_end: break

    for santa in sorted(santa_list, key=lambda x: x.number):
        print(santa.score, end=" ")


if __name__ == '__main__':
    solve()
