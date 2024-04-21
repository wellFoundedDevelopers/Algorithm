import sys
import heapq

# 최소한 소 만나기.
# N개 헛간. M개 양방향 소들의 길. 각각의 길에는 C_i마리의 소 존재. 소는 A_i, B_i를 잇는다.
# 두 개의 헛간은 하나 이상의 길로 연결되어 있을 수 있음.
# 농부 현서는 1번 헛간. 농부 찬홍이는 헛간 N (1 -> N)

n, m = map(int, sys.stdin.readline().split())
roads = [[] for _ in range(n)]
distances = [1000 * 50000 for _ in range(n)]
distances[0] = 0
for i in range(m):
    a, b, c = map(int, sys.stdin.readline().split())
    a -= 1
    b -= 1
    roads[a].append([b, c])
    roads[b].append([a, c])

pq = []
heapq.heappush(pq, [0, 0])

while pq:
    dis, node = heapq.heappop(pq)

    for n, d in roads[node]:
        if distances[n] > dis + d:
            heapq.heappush(pq, [dis + d, n])
            distances[n] = dis + d

print(distances[-1])