from collections import deque

n = 0
total_score = 0
board = []

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

group_count = [0] * ((29 * 29) + 1)


# 그룹을 만든다
# 각 그룹 내부 칸의 숫자를 센다
# 해당 그룹으로 상하좌우를 비교한다.

def solve():
    get_score()

    for _ in range(3):
        rotate()
        get_score()

    print(total_score)


def rotate():
    middle = n // 2

    # 세로 뽑아내기
    vertical = [board[x][middle] for x in range(n)]
    # 가로 뽑아내기
    horizontal = board[middle]

    horizontal = reverse_line(horizontal)

    # 가로로 넣기
    board[middle] = vertical
    # 세로로 넣기
    for x in range(n):
        board[x][middle] = horizontal[x]

    for x in range(0, n, middle + 1):
        for y in range(0, n, middle + 1):
            rotate_rect(x, y, x + middle - 1, y + middle - 1)


# 1: 왼쪽 위, 2: 오른쪽 아래
def rotate_rect(x1, y1, x2, y2):
    rect_size = n // 2
    slice_rect = [[0] * rect_size for _ in range(rect_size)]

    # 잘라진 사각형
    for x in range(x1, x2 + 1):
        for y in range(y1, y2 + 1):
            slice_rect[x - x1][y - y1] = board[x][y]

    rotated_rect = [[0] * rect_size for _ in range(rect_size)]
    # 회전
    for x in range(rect_size):
        for y in range(rect_size):
            rotated_rect[y][rect_size - x - 1] = slice_rect[x][y]

    # 기존 보드에 합치기
    for x in range(x1, x2 + 1):
        for y in range(y1, y2 + 1):
            board[x][y] = rotated_rect[x - x1][y - y1]


def reverse_line(line):
    return line[::-1]


def get_score():
    score = 0
    global total_score, group_count

    group_count = list(map(lambda x: 0, group_count))
    make_group()

    for x in range(n):
        for y in range(n):
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                if nx not in range(n) or ny not in range(n): continue
                if board[x][y] == board[nx][ny]: continue
                score += (group_count[group[x][y]] + group_count[group[nx][ny]]) * board[x][y] * board[nx][ny]
    total_score += score // 2


def make_group():
    visited = [[False] * n for _ in range(n)]
    queue = deque()
    group_number = 0

    for i in range(n):
        for j in range(n):
            if visited[i][j]: continue
            group_number += 1
            queue.append([i, j])
            visited[i][j] = True
            group[i][j] = group_number
            group_count[group_number] += 1

            while queue:
                x, y = queue.popleft()
                for d in range(4):
                    nx = x + dx[d]
                    ny = y + dy[d]
                    if nx not in range(n) or ny not in range(n):
                        continue
                    if visited[nx][ny]:
                        continue
                    if board[x][y] == board[nx][ny]:
                        queue.append([nx, ny])
                        visited[nx][ny] = True
                        group[nx][ny] = group_number
                        group_count[group_number] += 1


if __name__ == '__main__':
    n = int(input())
    for _ in range(n):
        board.append(list(map(int, input().split())))

    group = [[0] * n for _ in range(n)]
    solve()
