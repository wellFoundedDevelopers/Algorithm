import sys

n, m, k = map(int, sys.stdin.readline().split())

maze = []
for i in range(n):
    maze.append(list(map(int, sys.stdin.readline().split())))

people_maze = [[[] for _ in range(n)] for _ in range(n)]
people_info = []
for i in range(m):
    x, y = map(lambda x: int(x) - 1, sys.stdin.readline().split())
    people_maze[x][y].append(i)
    people_info.append([x, y])

exit = list(map(lambda x: int(x) - 1, sys.stdin.readline().split()))

SURVIVE = m
EMPTY = 0
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
result = 0


def move_person(num):
    global dx, dy, n, people_info, people_maze, maze, SURVIVE, EMPTY, result

    nx, ny = people_info[num]

    min_dst = abs(nx - exit[0]) + abs(ny - exit[1])
    drt = -1
    for i in range(4):
        if 0 <= nx + dx[i] < n and 0 <= ny + dy[i] < n and maze[nx + dx[i]][ny + dy[i]] == EMPTY:
            dst = abs(nx + dx[i] - exit[0]) + abs(ny + dy[i] - exit[1])
            if min_dst > dst:
                min_dst = dst
                drt = i

    if drt != -1:
        people_info[num][0] += dx[drt]
        people_info[num][1] += dy[drt]
        people_maze[nx][ny].remove(num)
        people_maze[nx + dx[drt]][ny + dy[drt]].append(num)
        result += 1

    if min_dst == 0:  # exit
        SURVIVE -= 1
        people_maze[nx + dx[drt]][ny + dy[drt]].remove(num)
        people_info[num] = [-1, -1]


def turn_maze():
    global m, maze, exit, people_info, people_maze, n
    # 가장 작은 정사각형 잡기
    min_dst = 100
    min_num = -1
    for i in range(m):
        if people_info[i] == [-1, -1]:
            continue

        dst = max(abs(exit[0] - people_info[i][0]), abs(exit[1] - people_info[i][1]))

        if dst < min_dst:
            min_dst = dst
            min_num = i
        elif dst == min_dst:
            if max(max(exit[0], people_info[i][0]) - dst, 0) < max(max(exit[0], people_info[min_num][0]) - min_dst, 0):
                min_dst = dst
                min_num = i
            elif max(max(exit[0], people_info[i][0]) - dst, 0) == max(max(exit[0], people_info[min_num][0]) - min_dst, 0):
                if max(max(exit[1], people_info[i][1]) - dst, 0) < max(max(exit[1], people_info[min_num][1]) - min_dst, 0):
                    min_dst = dst
                    min_num = i

    # maze 회전시키기, 이때 person_maze도 같이 회전시키기 (벽 내구도 -1) // maze, people_maze, people_info, exit 수정
    tmp_maze = [[0 for _ in range(n)] for _ in range(n)]
    tmp_people_maze = [[[] for _ in range(n)] for _ in range(n)]
    tmp_exit = [-1, -1]
    sx = max(max(exit[0], people_info[min_num][0]) - min_dst, 0)
    sy = max(max(exit[1], people_info[min_num][1]) - min_dst, 0)
    t = 0
    for i in range(0, min_dst + 1):
        for j in range(0, min_dst + 1):
            tmp_maze[sx + j][sy + min_dst - i] = max(maze[sx + i][sy + j] - 1, 0)
            tmp_people_maze[sx + j][sy + min_dst - i] = people_maze[sx + i][sy + j]

            for k in people_maze[sx + i][sy + j]:
                people_info[k] = [sx + j, sy + min_dst - i]

            if exit == [sx + i, sy + j]:
                tmp_exit = [sx + j, sy + min_dst - i]

    for i in range(sx, sx + min_dst + 1):
        for j in range(sy, sy + min_dst + 1):
            maze[i][j] = tmp_maze[i][j]
            people_maze[i][j] = tmp_people_maze[i][j]

    exit = tmp_exit


for i in range(k):
    if SURVIVE == 0:
        break

    for j in range(m):
        if people_info[j] == [-1, -1]:
            continue

        move_person(j)
    # 회전시켜라
    if SURVIVE == 0:
        break

    turn_maze()


print(result)
print(exit[0] + 1, exit[1] + 1)
