from collections import deque

n, m, k = 0, 0, 0
# 터렛들 집합
board_turret = []
# 터렛이 최근에 쏜 시점 집합
board_turret_recent_attack = []
# 공격 당한 터렛들 집합 Boolean 으로 표시
board_turret_attacked = []

round = 1

visited = []


def start_game():
    global round

    while round <= k:
        attack_pos = pick_attack()
        attacked_pos = pick_attacked()
        # print("attack_pos:", attack_pos, "attacked_pos:", attacked_pos)
        # print("before_attack", board_turret)
        attack(attack_pos=attack_pos, attacked_pos=attacked_pos)
        # print("after_attack", board_turret)
        # 부서지지 않은 포탑이 1개라면 즉시 게임 종료.
        zero_cnt = 0
        for x in range(n):
            for y in range(m):
                if board_turret[x][y] != 0:
                    zero_cnt += 1
        if zero_cnt == 1:
            break

        repair_turret(attack_pos=attack_pos)
        # print("after_repair", board_turret)
        # print("attack_round", board_turret_recent_attack)

        round += 1
    # print("last: ", board_turret)
    print(max(sum(board_turret, [])))


def pick_attack():
    # 공격력이 가장 낮은 포탑 찾기
    min_power = min(filter(lambda x: x != 0, sum(board_turret, [])))
    pos_min_power = []

    for x in range(n):
        for y in range(m):
            if board_turret[x][y] == min_power:
                pos_min_power.append([x, y])
    if len(pos_min_power) == 1: return pos_min_power[0]

    # 최근에 공격한 포탑 찾기
    pos_min_power_recent_attack = []
    pos_min_power_recent_round = max(board_turret_recent_attack[x][y] for x, y in pos_min_power)
    for x, y in pos_min_power:
        if board_turret_recent_attack[x][y] == pos_min_power_recent_round:
            pos_min_power_recent_attack.append([x, y])

    if len(pos_min_power_recent_attack) == 1: return pos_min_power_recent_attack[0]
    # 행과 열의 합
    # print(max(x + y for x, y in pos_min_power_recent_attack))
    max_sum_x_y = max(x + y for x, y in pos_min_power_recent_attack)
    # 열이 가장 큰
    filtered_attack = filter(lambda x: x[0] + x[1] == max_sum_x_y,
                             [[x, y] for x, y in pos_min_power_recent_attack])
    return max(filtered_attack, key=lambda x: x[1])


def pick_attacked():
    # 공격력이 높은 낮은 포탑 찾기
    max_power = max(sum(board_turret, []))
    pos_max_power = []

    for x in range(n):
        for y in range(m):
            if board_turret[x][y] == max_power:
                pos_max_power.append([x, y])
    if len(pos_max_power) == 1: return pos_max_power[0]

    # 공격한 지 오래된 포탑 찾기
    pos_max_power_old_attack = []
    pos_max_power_old_round = min(board_turret_recent_attack[x][y] for x, y in pos_max_power)
    for x, y in pos_max_power:
        if board_turret_recent_attack[x][y] == pos_max_power_old_round:
            pos_max_power_old_attack.append([x, y])

    if len(pos_max_power_old_attack) == 1: return pos_max_power_old_attack[0]
    # 행과 열의 합 가장 작은
    min_sum_x_y = min(x + y for x, y in pos_max_power_old_attack)
    # 열이 가장 작은
    filtered_attack = filter(lambda x: x[0] + x[1] == min_sum_x_y,
                             [[x, y] for x, y in pos_max_power_old_attack])

    return min(filtered_attack, key=lambda x: x[1])


def attack(attack_pos, attacked_pos):
    # print("wwwwwwwwwwww", board_turret)
    # print("dddddddddddd", attack_pos, attacked_pos)
    board_turret[attack_pos[0]][attack_pos[1]] += n + m
    board_turret_recent_attack[attack_pos[0]][attack_pos[1]] = round
    # 어택당한 포탑 초기화
    for x in range(n):
        for y in range(m):
            board_turret_attacked[x][y] = False

    if not attack_laser(attack_pos, attacked_pos):
        attack_cannon(attack_pos, attacked_pos)


def attack_laser(attack_pos, attacked_pos):
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]

    for x in range(n):
        for y in range(m):
            visited[x][y] = False

    queue = deque()
    queue.append([attack_pos, [attack_pos]])
    # print(board_turret)
    power = board_turret[attack_pos[0]][attack_pos[1]]

    while queue:
        [x, y], poses = queue.popleft()

        # print(poses)
        # print("poses", poses)
        if [x, y] == attacked_pos:
            poses.remove(attack_pos)
            poses.remove(attacked_pos)
            # 피해 입히기
            for attacked_x, attacked_y in poses:
                board_turret_attacked[attacked_x][attacked_y] = True
                board_turret[attacked_x][attacked_y] = max(0, board_turret[attacked_x][attacked_y] - (power // 2))
            board_turret_attacked[x][y] = True
            board_turret[x][y] = max(0, board_turret[x][y] - power)
            return True
        for i in range(4):
            nx = (x + dx[i]) % n
            ny = (y + dy[i]) % m
            if visited[nx][ny]: continue
            if board_turret[nx][ny] == 0: continue
            if [nx, ny] in poses: continue

            queue.append([[nx, ny], poses + [[nx, ny]]])
            visited[nx][ny] = True
    return False


def attack_cannon(attack_pos, attacked_pos):
    dx = [0, 1, 0, -1, 1, 1, -1, -1]
    dy = [1, 0, -1, 0, 1, -1, 1, -1]

    power = board_turret[attack_pos[0]][attack_pos[1]]
    x = attacked_pos[0]
    y = attacked_pos[1]
    # print("cannon: ", power)
    board_turret_attacked[x][y] = True
    board_turret[x][y] = max(0, board_turret[x][y] - power)
    for i in range(8):
        nx = (x + dx[i]) % n
        ny = (y + dy[i]) % m
        # print("in cannon:", nx, ny)
        if board_turret[nx][ny] == 0: continue
        if nx == x and ny == y: continue
        if nx == attack_pos[0] and ny == attack_pos[1]: continue
        board_turret_attacked[nx][ny] = True
        board_turret[nx][ny] = max(0, board_turret[nx][ny] - (power // 2))


def repair_turret(attack_pos):
    # for row in board_turret_attacked:
    #     print(row)
    for x in range(n):
        for y in range(m):
            if [x, y] == attack_pos: continue
            if board_turret_attacked[x][y]: continue
            if board_turret[x][y] == 0: continue
            board_turret[x][y] += 1


if __name__ == '__main__':
    n, m, k = map(int, input().split())
    for _ in range(n):
        board_turret.append(list(map(int, input().split())))
        board_turret_recent_attack.append([0 for _ in range(m)])
        board_turret_attacked.append([False for _ in range(m)])

    visited = [[False for _ in range(m)] for _ in range(n)]
    start_game()
