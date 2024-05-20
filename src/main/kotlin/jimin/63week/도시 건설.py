import sys

n, m = map(int, sys.stdin.readline().split())

roads = []
parents = [i for i in range(n + 1)]
origin = 0

for i in range(m):
    a, b, c = map(int, sys.stdin.readline().split())
    roads.append([a, b, c])
    origin += c


def find_root(x):
    if parents[x] == x:
        return x
    parents[x] = find_root(parents[x])
    return parents[x]


roads.sort(key=lambda x: x[2])
result = []

for i in range(m):
    a = find_root(roads[i][0])
    b = find_root(roads[i][1])

    if a == b:
        continue
    else:
        if a < b:
            parents[b] = a
        else:
            parents[a] = b

    result.append(roads[i][2])

    if len(result) == n - 1:
        print(origin - sum(result))
        exit()

print(-1)