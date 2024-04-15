dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

n, m, k = 0, 0, 0
board = []
pos = []
direction = []
man_power = []
gun_power = []
point = []


def get_gun(pid):
    # 내려 놓는다.
    x, y = pos[pid]
    if gun_power[pid] != 0:
        board[x][y].append(gun_power[pid])
        gun_power[pid] = 0
    # 가장 쎈 총을 줍는다.
    if len(board[x][y]) != 0:
        best_gun = max(board[x][y])
        gun_power[pid] = best_gun
        board[x][y].remove(best_gun)


def move(pid):
    x, y = pos[pid]
    dir = direction[pid]
    if x + dx[dir] not in range(n) or y + dy[dir] not in range(n):
        direction[pid] = (dir + 2) % 4
    ndir = direction[pid]
    nx = x + dx[ndir]
    ny = y + dy[ndir]
    pos[pid] = [nx, ny]

    is_fight = False

    for pid2, (t_x, t_y) in enumerate(pos):
        if pid2 == pid: continue
        if t_x == nx and t_y == ny:
            # 이동했는데 사람이 있다면 싸움
            fight(pid1=pid, pid2=pid2)
            is_fight = True
            break

    # 싸우지 않았는데 총이 있으면 획득
    if not is_fight:
        get_gun(pid=pid)


def fight(pid1, pid2):
    # print("pid1:", pid1, "pid2:", pid2)
    # print("before fight pos:", pos)

    pid1_power = man_power[pid1] + gun_power[pid1]
    pid2_power = man_power[pid2] + gun_power[pid2]

    if pid1_power > pid2_power:
        win = pid1
        lose = pid2
    elif pid2_power > pid1_power:
        win = pid2
        lose = pid1
    else:
        win = pid1 if man_power[pid1] > man_power[pid2] else pid2
        lose = pid2 if man_power[pid1] > man_power[pid2] else pid1

    point[win] += abs(pid1_power - pid2_power)

    lose_x, lose_y = pos[lose]
    # 진 놈 총 내려놓기
    if gun_power[lose] != 0:
        board[lose_x][lose_y].append(gun_power[lose])
        gun_power[lose] = 0
    # 진 놈 튀기
    while True:
        lose_dir = direction[lose]
        nx = lose_x + dx[lose_dir]
        ny = lose_y + dy[lose_dir]
        # print("lose_dir:", lose_dir)
        if nx not in range(n) or ny not in range(n) or [nx, ny] in pos:
            direction[lose] = (direction[lose] + 1) % 4
            continue
        pos[lose] = [nx, ny]
        break
    # 진 놈이 이동해서 총 먹기
    get_gun(pid=lose)

    # 이긴 놈은 총 먹기
    get_gun(pid=win)
    # print("after fight pos:", pos)


def start_game():
    for _ in range(k):
        for id in range(m):
            move(pid=id)
        # print("pos:", pos)

    print(*point)


if __name__ == '__main__':
    n, m, k = map(int, input().split())
    for _ in range(n):
        board.append(list(map(lambda x: [int(x)], input().split())))

    for i in range(m):
        _x, _y, _d, _s = map(int, input().split())
        pos.append([_x - 1, _y - 1])
        direction.append(_d)
        man_power.append(_s)
        gun_power.append(0)
        point.append(0)

    start_game()
