# 총 박멸된 나무들
killed_tree = 0
# 박멸된 친구들을 가늠할 기준치
kill_offset = 10_000_000

# 상하좌우
dx_udlr = [1, -1, 0, 0]
dy_udlr = [0, 0, -1, 1]

# 대각선
dx_diagonal = [1, 1, -1, -1]
dy_diagonal = [1, -1, 1, -1]


def is_tree(x, y):
    return 0 < board[x][y] < kill_offset


def is_wall(x, y):
    return board[x][y] == -1


def is_empty(x, y):
    return board[x][y] == 0


def in_range(x, y):
    return x in range(n) and y in range(n)


# 박멸시간 줄이기
def reduce_kill_time():
    for x in range(n):
        for y in range(n):
            if board[x][y] > kill_offset:
                board[x][y] -= 1
                if board[x][y] == kill_offset:
                    board[x][y] = 0


def grow():
    for x in range(n):
        for y in range(n):
            if is_tree(x, y):
                board[x][y] += get_count_side_tree(x, y)


def get_count_side_tree(x, y):
    count = 0
    for i in range(4):
        nx = x + dx_udlr[i]
        ny = y + dy_udlr[i]
        if in_range(nx, ny) and is_tree(nx, ny):
            count += 1

    return count


# 번식
def breed():
    new_board = [[0] * n for _ in range(n)]
    for x in range(n):
        for y in range(n):
            new_board[x][y] = board[x][y]

    for x in range(n):
        for y in range(n):
            if not is_tree(x, y): continue
            count = 0
            store_side_empty = []
            for i in range(4):
                nx = x + dx_udlr[i]
                ny = y + dy_udlr[i]
                if in_range(nx, ny) and is_empty(nx, ny):
                    count += 1
                    store_side_empty.append([nx, ny])
            if count == 0: continue
            breed_count = board[x][y] // count
            for e_x, e_y in store_side_empty:
                new_board[e_x][e_y] += breed_count

    for x in range(n):
        for y in range(n):
            board[x][y] = new_board[x][y]


def kill():
    global killed_tree

    k_x = -1
    k_y = -1
    k_cnt = 0
    # 박멸할 위치 찾기
    for x in range(n):
        for y in range(n):
            if not is_tree(x, y): continue
            cnt = get_count_kill_tree(x, y)
            if cnt > k_cnt:
                k_x = x
                k_y = y
                k_cnt = cnt

    if k_cnt == 0: return
    # 박멸.
    killed_tree += board[k_x][k_y]
    board[k_x][k_y] = kill_offset + c
    for i in range(4):
        nx = k_x
        ny = k_y
        for _ in range(k):
            nx += dx_diagonal[i]
            ny += dy_diagonal[i]
            if not in_range(nx, ny): break
            # 벽이라면 그냥 끝낸다.
            if is_wall(nx, ny): break
            # 비어있다면 거기까지 제초제를 뿌리고 끝낸다.
            if is_empty(nx, ny):
                board[nx][ny] = kill_offset + c
                break
            # 이미 제초제가 있는 칸이라면 제초제 시간을 초기화 시킨다.
            if board[nx][ny] > kill_offset:
                board[nx][ny] = kill_offset + c
                break
            # 나무에 제초제를 뿌리는 경우
            killed_tree += board[nx][ny]
            board[nx][ny] = kill_offset + c


def get_count_kill_tree(x, y):
    count_kill_tree = board[x][y]
    for i in range(4):
        nx = x
        ny = y
        for _ in range(k):
            nx += dx_diagonal[i]
            ny += dy_diagonal[i]
            if not in_range(nx, ny): break
            if is_wall(nx, ny): break
            if is_empty(nx, ny): break
            if board[nx][ny] > kill_offset: break
            count_kill_tree += board[nx][ny]

    return count_kill_tree


if __name__ == '__main__':
    n, m, k, c = map(int, input().split())
    c = c + 1
    board = [list(map(int, input().split())) for _ in range(n)]

    for _ in range(m):
        grow()
        # for row in board:
        #     print(row)
        # print("==================")
        breed()
        # for row in board:
        #     print(row)
        # print("==================")
        kill()
        # for row in board:
        #     print(row)
        # print("==================")
        reduce_kill_time()
        # for row in board:
        #     print(row)
        # print("==================")

    print(killed_tree)
