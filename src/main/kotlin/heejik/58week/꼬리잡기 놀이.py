from collections import deque

score = 0
teams = []
round = 0
dx = [0, -1, 0, 1]
dy = [1, 0, -1, 0]


def in_range(x, y):
    return x in range(n) and y in range(n)


def get_player():
    for x in range(n):
        for y in range(n):
            if board[x][y] == 1:
                find_my_team(x, y)


def find_my_team(x, y):
    tmp_team = []
    queue = deque()
    queue.append([x, y, 1])
    board[x][y] = 4
    tmp_team.append([x, y])
    while queue:
        x, y, val = queue.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if in_range(nx, ny) and board[nx][ny] % 4 != 0 and board[nx][ny] - val <= 1:
                queue.append([nx, ny, board[nx][ny]])
                tmp_team.append([nx, ny])
                board[nx][ny] = 4

    teams.append(tmp_team)


def start_game():
    global round

    get_player()
    while round < k:
        play()
        round += 1
    print(score)


def play():
    for team in teams:
        move(team)

    shot()


def move(team):
    h_x, h_y = team[0]
    s_x, s_y = team[1]
    cant_dir = -1
    go_dir = -1

    # 갈 수 없는 방향
    for i in range(4):
        if [dx[i], dy[i]] == [s_x - h_x, s_y - h_y]:
            cant_dir = i
            break

    # 이동 방향 결정
    for i in range(4):
        if i == cant_dir: continue
        nx = h_x + dx[i]
        ny = h_y + dy[i]
        if in_range(nx, ny) and board[nx][ny] == 4:
            go_dir = i

    # 이전 친구들은 앞에 애들의 위치에 간다 맨 앞은 제외하고
    for idx in range(len(team) - 1, 0, -1):
        team[idx] = team[idx - 1]

    team[0] = [team[0][0] + dx[go_dir], team[0][1] + dy[go_dir]]


def shot():
    global score

    ball_direction = (round // n) % 4
    line = round % n

    # 오른쪽 발사, line 은 오름차순 0, 1, 2, ...
    if ball_direction == 0:
        for y in range(n):
            b_x = line
            b_y = y
            for team in teams:
                for idx, [x, y] in enumerate(team):
                    if x == b_x and y == b_y:
                        score += (idx + 1) ** 2
                        team.reverse()
                        return

    # 위쪽 발사, line 은 오름차순 0, 1, 2, ...
    elif ball_direction == 1:
        for x in range(n - 1, -1, -1):
            b_x = x
            b_y = line
            for team in teams:
                for idx, [x, y] in enumerate(team):
                    if x == b_x and y == b_y:
                        score += (idx + 1) ** 2
                        team.reverse()
                        return

    # `왼쪽 발사, line 은 내림차순 6, 5, 4, ...
    elif ball_direction == 2:
        for y in range(n - 1, -1, -1):
            b_x = n - 1 - line
            b_y = y
            for team in teams:
                for idx, [x, y] in enumerate(team):
                    if x == b_x and y == b_y:
                        score += (idx + 1) ** 2
                        team.reverse()
                        return

    # 아래 발사, line 은 내림차순 6, 5, 4, ...
    else:
        for x in range(n):
            b_x = x
            b_y = n - 1 - line
            for team in teams:
                for idx, [x, y] in enumerate(team):
                    if x == b_x and y == b_y:
                        score += (idx + 1) ** 2
                        team.reverse()
                        return


if __name__ == '__main__':
    n, m, k = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(n)]
    start_game()
