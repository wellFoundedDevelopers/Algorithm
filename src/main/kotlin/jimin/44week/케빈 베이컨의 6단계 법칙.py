from collections import defaultdict
from collections import deque

def getBaconNum(n):
    global bacons
    q = deque([n])
    visited = [0 for _ in range(len(bacons) + 1)]
    num = 1
    while q:
        now = q.popleft()
        for i in bacons[now]:
            if visited[i] == 0 and i != n:
                visited[i] = visited[now] + 1
                q.append(i)
        num += 1
    return sum(visited)


n, m = map(int, input().split())
bacons = defaultdict(list)

for i in range(m):
    a, b = map(int, input().split())
    bacons[a].append(b)
    bacons[b].append(a)

mini = 500001
result = 0
for i in range(1, n + 1):
    num = getBaconNum(i)
    if mini > num:
        result = i
        mini = num
print(result)