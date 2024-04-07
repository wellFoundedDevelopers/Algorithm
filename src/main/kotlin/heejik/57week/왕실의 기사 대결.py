dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

l, n, q = 0, 0, 0
board: list = []
knight_pos = []  # 기사들의 위치가 배열 index 에 존재
knight_hp = []
knight_origin_hp = []
board_pos = []  # 기사들의 위치가 l*l 보드 형식으로 존재
moved_pos = []


def move(origin_number, number, d):
    print("number:", number, "origin_number:", origin_number, "d:", d)
    global moved_pos
    can_move = True
    for x, y in knight_pos[number]:
        nx = x + dx[d]
        ny = y + dy[d]
        if nx not in range(l) or ny not in range(l) or board[nx][ny] == 2:
            return False

    new_number = []
    for x, y in knight_pos[number]:
        nx = x + dx[d]
        ny = y + dy[d]
        if board_pos[nx][ny] != -1 and board_pos[nx][ny] != number:
            new_number.append(board_pos[nx][ny])
    for n_number in set(new_number):
        if can_move:
            can_move = move(origin_number=origin_number, number=n_number, d=d)

    if can_move:
        for x, y in knight_pos[number]:
            nx = x + dx[d]
            ny = y + dy[d]
            moved_pos[number].append([nx, ny])

        if number == origin_number:
            for num in range(n):
                if len(moved_pos[num]) == 0: continue
                pre_pos = knight_pos[num].copy()
                knight_pos[num].clear()
                for x, y in pre_pos:
                    if board_pos[x][y] == num:
                        board_pos[x][y] = -1
                for nx, ny in moved_pos[num]:
                    board_pos[nx][ny] = num
                    knight_pos[num].append([nx, ny])
                if num != origin_number:
                    get_damage(pushed_out_number=num)

    if number == origin_number:
        moved_pos = list(map(lambda x: list(), moved_pos))

    return can_move


def get_damage(pushed_out_number: int):
    is_deleted = False
    for x, y in knight_pos[pushed_out_number]:
        if board[x][y] == 1:
            knight_hp[pushed_out_number] -= 1
            if knight_hp[pushed_out_number] == 0:
                is_deleted = True
                break

    if is_deleted:
        for x, y in knight_pos[pushed_out_number]:
            board_pos[x][y] = -1
        knight_pos[pushed_out_number].clear()


def solve():
    global l, n, q, board, knight_pos, knight_hp, board_pos, moved_pos
    l, n, q = map(int, input().split())
    moved_pos = [[] for _ in range(n)]

    for _ in range(l):
        board.append(list(map(int, input().split())))
        board_pos.append([-1 for _ in range(l)])
    for number in range(n):
        r, c, h, w, k = map(int, input().split())
        knight_hp.append(k)
        knight_origin_hp.append(k)
        pos = []
        for i in range(r - 1, r - 1 + h):
            for j in range(c - 1, c - 1 + w):
                pos.append([i, j])
                board_pos[i][j] = number

        knight_pos.append(pos)

    for e in board_pos:
        print(e)
    for idx, w in enumerate(knight_pos):
        print(str(idx) + "번 기사의 위치는: ", w)
    print("남은 체력:", knight_hp)
    print("--------------")

    for _ in range(q):
        i, d = map(int, input().split())
        move(origin_number=i - 1, number=i - 1, d=d)
        for q in board_pos:
            print(q)
        for idx, w in enumerate(knight_pos):
            print(str(idx) + "번 기사의 위치는: ", w)
        print("남은 체력:", knight_hp)
    print("--------------")
    answer = 0
    for idx, hp in enumerate(knight_hp):
        if hp > 0:
            answer += (knight_origin_hp[idx] - knight_hp[idx])

    print(answer)


if __name__ == '__main__':
    # a = set()
    # a.add(6)
    # a.add(6)
    # a.add(3)
    # a.add(6)
    # print(a)
    #
    # for q in a:
    #     print(q)
    solve()

#
