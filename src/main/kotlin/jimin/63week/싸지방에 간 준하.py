'''
https://deok2kim.tistory.com/326
'''


import heapq
import sys

n = int(sys.stdin.readline())
times = []
for i in range(n):
    times.append(list(map(int, sys.stdin.readline().split())))

times.sort()

computers = [0 for _ in range(n)]
computers[0] = 1

pq = []
heapq.heappush(pq, [times[0][1], 0])
size = 0
computer_pq = [i for i in range(1, n)]

for idx in range(1, n):
    if pq[0][0] <= times[idx][0]:
        while True:
            prev_end, prev_num = heapq.heappop(pq)
            if pq and pq[0][0] <= times[idx][0]:
                heapq.heappush(computer_pq, prev_num)
                continue
            else:
                num = heapq.heappushpop(computer_pq, prev_num)
                computers[num] += 1
                heapq.heappush(pq, [times[idx][1], num])
                break
    else:
        num = heapq.heappop(computer_pq)
        computers[num] += 1
        heapq.heappush(pq, [times[idx][1], num])
        size = max(size, num)

size += 1
print(size)
print(' '.join(map(str, computers[:size])))
'''
7
0 20
3 10
5 17
7 13
8 15
14 25
16 30
'''