dx = [0, 0, -1, 1]
dy = [1, -1, 0, 0]
sum_move_distance = 0


def get_distance(x1, y1, x2, y2):
    return abs(x1 - x2) + abs(y1 - y2)


def solve():
    for _ in range(k):
        move()
        if len(man_pos) == 0:
            break
        rotate()
        # for row in board:
        #     print(row)
        # print("----------------------------")
    print(sum_move_distance)
    print(exit_pos[0] + 1, exit_pos[1] + 1)


def move():
    global sum_move_distance, man_pos
    # 사람 한 명씩
    pop_idx = []
    for idx, [x, y] in enumerate(man_pos):
        # 4 방향을 돌며
        dir = -1
        dis = get_distance(x, y, exit_pos[0], exit_pos[1])
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx not in range(n) or ny not in range(n): continue
            if board[nx][ny] != 0: continue
            # print(idx, i)
            distance = get_distance(nx, ny, exit_pos[0], exit_pos[1])
            if distance <= dis:
                dis = distance
                dir = i
        # print(dir)
        if dir == -1: continue
        if [x + dx[dir], y + dy[dir]] == exit_pos:
            pop_idx.append(idx)
        else:
            man_pos[idx] = [x + dx[dir], y + dy[dir]]
        sum_move_distance += 1

    man_pos = [item for idx, item in enumerate(man_pos) if idx not in pop_idx]
    # for idx in pop_idx:
    #     man_pos.pop(idx)


def rotate():
    global exit_pos, man_pos
    x1, y1, x2, y2 = find_small_rect()
    offset_x = x1
    offset_y = y1
    # print("find_small_rect", find_small_rect())
    n_rect = [[0 for _ in range(x2 - x1 + 1)] for _ in range(x2 - x1 + 1)]

    offset = x2 - x1
    # print(man_pos)
    tmp_man = []
    tmp_exit = []
    for x in range(offset + 1):
        for y in range(offset + 1):
            pre_x = x + offset_x
            pre_y = y + offset_y
            new_x = y
            new_y = abs((x2 - x1) - x)
            # print(pre_x, pre_y, new_x, new_y)
            n_rect[new_x][new_y] = board[pre_x][pre_y]
            while True:
                if [pre_x, pre_y] in man_pos:
                    man_pos.remove([pre_x, pre_y])
                    tmp_man.append([new_x + offset_x, new_y + offset_y])
                else:
                    break
            if [pre_x, pre_y] == exit_pos:
                tmp_exit = [new_x + offset_x, new_y + offset_y]

    man_pos.extend(tmp_man)
    if len(tmp_exit) != 0: exit_pos = tmp_exit
    # print(n_rect)
    # print("exit_pos:", exit_pos)
    # print("man_pos:", man_pos)
    for x in range(x1, x2 + 1):
        for y in range(y1, y2 + 1):
            board[x][y] = max(0, n_rect[x - offset_x][y - offset_y] - 1)


def find_small_rect():
    offset = 0
    while True:
        offset += 1
        for x in range(n - offset):
            for y in range(n - offset):
                if can_rect(x, y, x + offset, y + offset):
                    return [x, y, x + offset, y + offset]


# x1 = 작은 x, y1 = 작은 y, x2 = 큰 x, y2 = 큰 y
def can_rect(x1, y1, x2, y2):
    is_in_man = False
    is_in_exit = False
    for x in range(x1, x2 + 1):
        for y in range(y1, y2 + 1):
            if exit_pos == [x, y]: is_in_exit = True
            if [x, y] in man_pos: is_in_man = True

    return is_in_man and is_in_exit


n, m, k = 0, 0, 0
board = []
man_pos = []
exit_pos = []

if __name__ == '__main__':
    n, m, k = map(int, input().split())
    for _ in range(n):
        board.append(list(map(int, input().split())))
    for _ in range(m):
        man_pos.append(list(map(lambda x: int(x) - 1, input().split())))
    exit_pos = list(map(lambda x: int(x) - 1, input().split()))
    solve()
