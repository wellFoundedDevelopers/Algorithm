'''
roads를 처음에 n*n 행렬로 했더니 메모리 초과가 났다.
n이 10만이라 그런듯..
'''

import sys
import heapq

INF = sys.maxsize
n, m = map(int, sys.stdin.readline().split())
locations = list(map(int, sys.stdin.readline().split()))
roads = [[] for _ in range(n)]
distance = [INF for _ in range(n)]
visited = [0 for _ in range(n)]

for i in range(m):
    a, b, t = map(int, sys.stdin.readline().split())
    roads[a].append([b, t])
    roads[b].append([a, t])

start = 0
end = n - 1
distance[start] = 0

pq = []
heapq.heappush(pq, [0, start])

while pq:
    dis, start = heapq.heappop(pq)

    if visited[start]:
        continue
    visited[start] = 1

    for nxt, time in roads[start]:
        if nxt != n - 1 and locations[nxt]:
            continue
        if distance[nxt] >= dis + time:
            distance[nxt] = dis + time
            heapq.heappush(pq, [dis + time, nxt])

if distance[-1] == INF:
    print(-1)
else:
    print(distance[-1])
