import sys

n = int(sys.stdin.readline())
classes = []
maxi = 0
for i in range(n):
    classes.append(list(map(int, sys.stdin.readline().split())))
    maxi = max(maxi, classes[-1][1])

classes.sort(reverse=True)
visited = [0 for _ in range(maxi + 1)]
for i in range(n):
    p, d = classes[i]

    for j in range(d, 0, -1):
        if visited[j] == 0:
            visited[j] = p
            break

print(sum(visited))