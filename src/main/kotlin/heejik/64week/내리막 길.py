dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]


def dfs(x, y):
    # 끝에 도달한다면
    if x == m - 1 and y == n - 1: return 1
    # 간 적이 있다면
    if dp[x][y] != -1: return dp[x][y]

    dp[x][y] = 0

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if nx not in range(m) or ny not in range(n): continue
        height = board[x][y]
        new_height = board[nx][ny]
        if new_height >= height: continue

        dp[x][y] += dfs(nx, ny)

    return dp[x][y]


if __name__ == '__main__':
    m, n = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(m)]
    dp = [[-1 for _ in range(n)] for _ in range(m)]
    print(dfs(0, 0))
