import sys

n = int(sys.stdin.readline())
answer_type = list(map(int, sys.stdin.readline().split()))
shaking_type = list(map(int, sys.stdin.readline().split()))

result = [[] for _ in range(3)]
for i in range(n):
    result[answer_type[i]].append(i)

num = 0
now = [i for i in range(n)]
tmp = [0 for _ in range(n)]
origin = [i for i in range(n)]
isOver = False
while not isOver:
    isOver = True
    for i in range(0, n, 3):
        for j in range(3):
            if now[i + j] not in result[j]:
                isOver = False
                break
        if not isOver:
            break

    if isOver:
        break

    for i in range(n):
        tmp[shaking_type[i]] = now[i]

    if tmp == origin:
        num = -1
        break

    now = tmp[:]
    num += 1

print(num)