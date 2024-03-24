import sys

n = int(sys.stdin.readline())
m = int(sys.stdin.readline())
cities = []

for i in range(n):
    cities.append(list(map(lambda x: int(x) == 1, sys.stdin.readline().split())))

travel = list(map(lambda x: int(x) - 1, sys.stdin.readline().split()))

for k in range(n):
    for i in range(n):
        for j in range(n):
            cities[i][j] = (cities[i][k] and cities[k][j]) or cities[i][j]

            if i == j:
                cities[i][j] = True

for i in range(m - 1):
    if not cities[travel[i]][travel[i + 1]]:
        print("NO")
        sys.exit()

print("YES")