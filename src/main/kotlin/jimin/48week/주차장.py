'''
그냥 구현, deque를 이용
'''

import sys
from collections import deque

n, m = map(int, sys.stdin.readline().split())
parking_fee = [int(sys.stdin.readline()) for _ in range(n)]
car_weight = [0] + [int(sys.stdin.readline()) for _ in range(m)]
car_parking_info = [-1] + [-1 for _ in range(m)]
visited = [0 for _ in range(n)]

result = 0
possible_parking = 0
queue = deque([])
for i in range(2 * m):
    car = int(sys.stdin.readline())
    if car > 0:
        if not all(visited):
            possible_parking = visited.index(0)
            visited[possible_parking] = 1
            car_parking_info[car] = possible_parking
        else:
            queue.append(car)
    else:
        parking_info = car_parking_info[car * -1]
        result += parking_fee[parking_info] * car_weight[car * -1]
        visited[parking_info] = 0
        car_parking_info[car * -1] = -1

        if queue:
            new = queue.popleft()
            visited[parking_info] = 1
            car_parking_info[new] = parking_info

print(result)


