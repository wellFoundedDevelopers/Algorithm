import sys

t = int(sys.stdin.readline())
wheels = []
for i in range(t):
    wheels.append(list(map(int, list(sys.stdin.readline().strip()))))

RIGHT = 1
LEFT = -1


def turn_wheel(turn_type, num):
    global RIGHT, LEFT, wheels

    if turn_type == RIGHT:
        before = wheels[num][0]
        for i in range(0, 7):
            after = wheels[num][i + 1]
            wheels[num][i + 1] = before
            before = after
        wheels[num][0] = after
    else:
        tmp = wheels[num][0]
        for i in range(0, 7):
            wheels[num][i] = wheels[num][i + 1]
        wheels[num][-1] = tmp



def check_wheel(origin_turn_type, num):
    global wheels

    origin_1 = wheels[num][2]
    origin_2 = wheels[num][6]

    turn_wheel(origin_turn_type, num)

    turn_type = origin_turn_type
    for i in range(num + 1, t):
        next = wheels[i][6]
        if next != origin_1:
            origin_1 = wheels[i][2]
            turn_type *= -1
            turn_wheel(turn_type, i)

        else:
            break

    turn_type = origin_turn_type
    for i in range(num - 1, -1, -1):
        next = wheels[i][2]
        if next != origin_2:
            origin_2 = wheels[i][6]
            turn_type *= -1
            turn_wheel(turn_type, i)
        else:
            break


k = int(sys.stdin.readline())
for i in range(k):
    num, drt = map(int, sys.stdin.readline().split())
    num -= 1
    check_wheel(drt, num)

result = 0
for i in range(t):
    result += wheels[i][0]

print(result)
