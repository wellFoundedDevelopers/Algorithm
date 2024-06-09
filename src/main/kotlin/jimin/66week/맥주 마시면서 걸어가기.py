import sys
from collections import deque

t = int(sys.stdin.readline())
for i in range(t):
    n = int(sys.stdin.readline())
    home = list(map(int, sys.stdin.readline().split()))
    stores = []
    for j in range(n):
        stores.append(list(map(int, sys.stdin.readline().split())))
    festival = list(map(int, sys.stdin.readline().split()))
    visited = [False for _ in range(n)]

    queue = deque([])
    queue.append(home)
    isPossible = False
    while queue:
        nx, ny = queue.popleft()
        if abs(nx - festival[0]) + abs(ny - festival[1]) <= 20 * 50:
            isPossible = True
            break

        for i in range(n):
            if not visited[i]:
                if abs(nx - stores[i][0]) + abs(ny - stores[i][1]) <= 20 * 50:
                    visited[i] = True
                    queue.append(stores[i])

    if isPossible:
        print("happy")
    else:
        print("sad")

