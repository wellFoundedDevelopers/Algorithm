def rotate(number, direction):
    global gears
    directions[number] = direction

    # 오른쪽
    for i in range(number+1, len(gears)):
        leftGear = gears[i-1]
        if leftGear[2] != gears[i][6]:
            directions[i] = directions[i-1] * -1
        else:
            break

    # 왼쪽
    for i in range(number-1, -1, -1):
        rightGear = gears[i+1]
        if rightGear[6] != gears[i][2]:
            directions[i] = directions[i+1] * -1
        else:
            break

    # 회전
    for i in range(n):
        # 반 시계
        if directions[i] == -1:
            gears[i] = gears[i][1:] + [gears[i][0]]
        # 시계
        elif directions[i] == 1:
            gears[i] = [gears[i][-1]] + gears[i][:-1]
    

n = int(input())
gears = [list(map(int, list(input()))) for _ in range(n)]
directions = [0 for _ in range(n)]

k = int(input())
for _ in range(k):
    number, direction = map(lambda x: int(x), input().split())
    rotate(number -1, direction)
    directions = list(map(lambda x: 0, directions))

print(len(list(filter(lambda x: x[0] == 1, gears))))