# import sys
#
# sys.stdin = open("input.txt", "r")

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

test_case = 1


def solve():
    global test_case

    n = int(input())
    number = 1
    count = n
    board = [[0] * n for _ in range(n)]
    direction = 0
    x, y = 0, -1

    while number <= n ** 2:
        for _ in range(count):
            x += dx[direction]
            y += dy[direction]
            board[x][y] = number
            number += 1
        direction = (direction + 1) % 4
        if direction % 2 != 0:
            count -= 1

    print("#" + str(test_case))
    for row in board:
        print(*row)
    test_case += 1


if __name__ == '__main__':
    t = int(input())
    for _ in range(t):
        solve()
