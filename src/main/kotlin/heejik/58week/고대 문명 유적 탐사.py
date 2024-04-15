from collections import deque
import heapq

total_score = 0


def solve():
    global total_score

    for _ in range(k):
        total_score = 0
        b_x, b_y, b_degree = get_best_pos()
        if b_x == -1: break
        n_board = rotate(b_x, b_y, b_degree)
        for i in range(5):
            for j in range(5):
                board[i][j] = n_board[i][j]
        scored_pos = get_score(board)
        total_score += len(scored_pos)
        fill_piece(scored_pos)
        # for row in board:
        #     print(row)
        # print("-----------------------")
        while True:
            scored_pos = get_score(board)
            if len(scored_pos) < 3: break
            total_score += len(scored_pos)
            fill_piece(scored_pos)
        print(total_score, end=" ")


def get_best_pos():
    pos_list = []
    for degree in [90, 180, 270]:
        for x in range(1, 4):
            for y in range(1, 4):
                n_board = rotate(x, y, degree)
                score = len(get_score(n_board))
                heapq.heappush(pos_list, [-score, degree, y, x])

    minus_score, degree, y, x = heapq.heappop(pos_list)
    if -minus_score == 0: return -1, -1, -1
    return x, y, degree


def rotate(x, y, degree):
    n_board = [[0] * 5 for _ in range(5)]
    tmp_board = [[0] * 3 for _ in range(3)]
    for i in range(5):
        for j in range(5):
            n_board[i][j] = board[i][j]

    x_offset = x - 1
    y_offset = y - 1
    for _ in range(degree // 90):
        for i in range(3):
            for j in range(3):
                tmp_board[i][j] = n_board[3 - 1 - j + x_offset][i + y_offset]

        for i in range(3):
            for j in range(3):
                n_board[i + x_offset][j + y_offset] = tmp_board[i][j]

    return n_board


dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]


def get_score(n_board):
    scored_pos = []
    visited = [[False] * 5 for _ in range(5)]
    for i in range(5):
        for j in range(5):
            if visited[i][j]: continue
            queue = deque()
            queue.append([i, j])
            visited[i][j] = True
            tmp_store = [[i, j]]

            while queue:
                x, y = queue.popleft()
                for d in range(4):
                    nx = x + dx[d]
                    ny = y + dy[d]
                    if nx not in range(5) or ny not in range(5): continue
                    if visited[nx][ny]: continue
                    if n_board[nx][ny] != n_board[x][y]: continue
                    visited[nx][ny] = True
                    queue.append([nx, ny])
                    tmp_store.append([nx, ny])

            if len(tmp_store) < 3: continue
            for t_x, t_y in tmp_store:
                heapq.heappush(scored_pos, [t_y, -t_x])

    return scored_pos


def fill_piece(deleted_pos):
    while deleted_pos:
        y, x = heapq.heappop(deleted_pos)
        board[-x][y] = piece.pop(0)


if __name__ == '__main__':
    k, m = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(5)]
    piece = list(map(int, input().split()))
    solve()
