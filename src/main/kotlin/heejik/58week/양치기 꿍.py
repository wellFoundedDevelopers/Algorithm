from collections import deque

n, m = 0, 0
board = []
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
wolf_cnt = 0
sheep_cnt = 0


def solve():
    for i in range(n):
        for j in range(m):
            if board[i][j] != '#':
                bfs(i, j)

    print(sheep_cnt, wolf_cnt)


def bfs(sx, sy):
    global wolf_cnt, sheep_cnt

    tmp_wolf_cnt = 0
    tmp_sheep_cnt = 0
    if board[sx][sy] == 'v': tmp_wolf_cnt += 1
    if board[sx][sy] == 'k': tmp_sheep_cnt += 1
    board[sx][sy] = '#'

    queue = deque()
    queue.append([sx, sy])

    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx not in range(n) or ny not in range(m): continue
            if board[nx][ny] == '#': continue
            if board[nx][ny] == 'v': tmp_wolf_cnt += 1
            if board[nx][ny] == 'k': tmp_sheep_cnt += 1
            board[nx][ny] = '#'
            queue.append([nx, ny])

    if tmp_wolf_cnt >= tmp_sheep_cnt:
        wolf_cnt += tmp_wolf_cnt
    else:
        sheep_cnt += tmp_sheep_cnt


if __name__ == '__main__':
    n, m = map(int, input().split())
    for _ in range(n):
        board.append(list(input()))
    solve()
