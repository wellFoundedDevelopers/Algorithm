from collections import deque

score = 0

# 위, 앞, 옆
dice_number = [1, 2, 3]
dice_direction = 0
dice_pos = [0, 0]

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def in_range(x, y):
    return x in range(n) and y in range(n)


def change_dice_number(direction):
    global dice_number
    up, front, side = dice_number
    # 오른쪽
    if direction == 0:
        n_up, n_front, n_side = 7 - side, front, up
    # 왼쪽
    elif direction == 2:
        n_up, n_front, n_side = side, front, 7 - up
    # 위쪽
    elif direction == 3:
        n_up, n_front, n_side = front, 7 - up, side
    # 아래쪽
    else:
        n_up, n_front, n_side = 7 - front, up, side

    dice_number = [n_up, n_front, n_side]


def move_dice():
    global dice_direction, dice_pos
    d_x, d_y = dice_pos
    nx = d_x + dx[dice_direction]
    ny = d_y + dy[dice_direction]

    # 밖을 벗어나는 경우
    if not in_range(nx, ny):
        dice_direction = (dice_direction + 2) % 4
        nx = d_x + dx[dice_direction]
        ny = d_y + dy[dice_direction]

    change_dice_number(dice_direction)
    dice_pos = [nx, ny]


def rotate_dice():
    global dice_direction
    # 아래 번호
    down_number = abs(7 - dice_number[0])

    d_x, d_y = dice_pos
    board_number = board[d_x][d_y]
    # 더 크면 90도 시계 회전
    if down_number > board_number:
        dice_direction = (dice_direction + 1) % 4
    # 더 작으면 90도 반시계 회전
    elif down_number < board_number:
        dice_direction = (dice_direction - 1) % 4
    # 같으면 그대로
    else:
        pass


# 현재 다이스의 위치의 보드 숫자에서 bfs 로 상하좌우로 같은 숫자면 점수 합
def get_score():
    global score
    d_x, d_y = dice_pos
    board_number = board[d_x][d_y]

    score += board_number
    visited = [[False] * n for _ in range(n)]
    visited[d_x][d_y] = True
    queue = deque()
    queue.append([d_x, d_y])

    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if not in_range(nx, ny): continue
            if board[nx][ny] != board_number: continue
            if visited[nx][ny]: continue
            queue.append([nx, ny])
            visited[nx][ny] = True
            score += board_number


def roll_dice():
    move_dice()
    get_score()
    rotate_dice()


if __name__ == '__main__':
    n, m = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(n)]

    for _ in range(m):
        roll_dice()

    print(score)
