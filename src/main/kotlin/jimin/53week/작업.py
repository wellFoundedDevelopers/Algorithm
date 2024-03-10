import sys
from collections import deque

n, m = map(int, sys.stdin.readline().split())
jobs = [[] for _ in range(n + 1)]

for i in range(m):
    a, b = map(int, sys.stdin.readline().split())
    jobs[b].append(a)

x = int(sys.stdin.readline())

queue = deque(jobs[x])
visited = [False for _ in range(n + 1)]
visited[x] = True

num = 0
while queue:
   nxt = queue.popleft()
   if not visited[nxt]:
       num += 1
       visited[nxt] = True

       for t in jobs[nxt]:
           if not visited[t]:
               queue.append(t)

print(num)