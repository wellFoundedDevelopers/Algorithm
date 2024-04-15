dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

dx_reverse = [1, 0, -1, 0]
dy_reverse = [0, 1, 0, -1]

# True -> 바깥쪽(초기값), False -> 안쪽
catcher_outside = True
catcher_dir = 0

n, m, h, k = map(int, input().split())
runners = []
for _ in range(m):
    # d:1 좌우, 오른쪽 d:2: 상하, 아래쪽
    x, y, d = map(int, input().split())
    runners.append([x - 1, y - 1, d])

tree_board = [[False] * n for _ in range(n)]

for _ in range(h):
    i, j = map(lambda x: int(x) - 1, input().split())
    tree_board[i][j] = True
catcher = [n // 2, n // 2]
move_catcher_offset = []
move_catcher_offset_reverse = []

round = 1
point = 0


def in_range(x, y):
    return x in range(n) and y in range(n)


def get_catcher_dxy():
    catcher_dx = dx[catcher_dir] if catcher_outside else dx_reverse[catcher_dir]
    catcher_dy = dy[catcher_dir] if catcher_outside else dy_reverse[catcher_dir]

    return catcher_dx, catcher_dy


def make_catcher_move():
    change_offset = 2
    timer = 1
    count = 0
    i = 1
    while i < (n ** 2):
        count += 1
        if count == change_offset:
            timer += 1
            count = 0
        move_catcher_offset.append(i)
        i += timer


def make_catcher_move_reverse():
    for value in move_catcher_offset:
        move_catcher_offset_reverse.append((n ** 2) - 1 - value)
    move_catcher_offset_reverse.reverse()
    print(move_catcher_offset)
    print(move_catcher_offset_reverse)


def move_catcher():
    global catcher_outside, catcher_dir

    # 이동하기d
    catcher_dx, catcher_dy = get_catcher_dxy()
    catcher[0] += catcher_dx
    catcher[1] += catcher_dy

    # 안쪽 바깥쪽 바뀐 지 체크
    is_change_direction = round != 0 and round % ((n ** 2) - 1) == 0  # 24, 48, ...
    if is_change_direction:
        catcher_outside = not catcher_outside

    # 이동 후 방향 바꾸기
    _round = round % ((n ** 2) - 1)

    if catcher_outside:
        if _round in move_catcher_offset:
            catcher_dir = (catcher_dir + 1) % 4
    else:
        if _round in move_catcher_offset_reverse:
            catcher_dir = (catcher_dir + 1) % 4


def get_distance(x1, y1, x2, y2):
    return abs(x1 - x2) + abs(y1 - y2)


def move_runners():
    for idx, (r_x, r_y, d) in enumerate(runners):
        c_x, c_y = catcher
        dist = get_distance(r_x, r_y, c_x, c_y)

        if dist > 3: continue
        nx = r_x + dx[d]
        ny = r_y + dy[d]
        if not in_range(nx, ny):
            nd = (d + 2) % 4
            nx = r_x + dx[nd]
            ny = r_y + dy[nd]
            if catcher == [nx, ny]:
                runners[idx] = [r_x, r_y, nd]
                continue
            runners[idx] = [nx, ny, nd]
        else:
            if catcher == [nx, ny]: continue
            runners[idx] = [nx, ny, d]


def catch_me_if_you_can():
    global point

    n_cx, n_cy = catcher
    remove_runner = []

    if not tree_board[n_cx][n_cy]:
        for idx, (r_x, r_y, d) in enumerate(runners):
            if n_cx == r_x and n_cy == r_y:
                remove_runner.append([r_x, r_y, d])

    for i in range(2):
        catcher_dx, catcher_dy = get_catcher_dxy()
        n_cx += catcher_dx
        n_cy += catcher_dy
        if not in_range(n_cx, n_cy):
            break
        if not tree_board[n_cx][n_cy]:
            for idx, (r_x, r_y, d) in enumerate(runners):
                if n_cx == r_x and n_cy == r_y:
                    remove_runner.append([r_x, r_y, d])

    for val in remove_runner:
        runners.remove(val)
        point += round


if __name__ == '__main__':
    make_catcher_move()
    make_catcher_move_reverse()

    while round <= k:
        move_runners()
        move_catcher()
        catch_me_if_you_can()
        round += 1

    print(point)
