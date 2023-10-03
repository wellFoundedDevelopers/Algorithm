'''
0-1 너비 우선 탐색이라는 알고리즘 분류를 보고 풀었다. 한 눈에 보고 0-1 인지 몰랐음

처음에는 * + - 순으로 했는데,
질문 게시판을 보고 * - + 이 우선 순위라는 것을 깨달았다!

visited 처리를 안하고 dots 초기값을 max로 해서 작은 경우만 탐색하면 우선 순위 고려 안해도 된다!

'''

import sys
from collections import deque

n, k = map(int, sys.stdin.readline().split())
dots = [100001 for _ in range(100001)]

queue = deque([n])
dots[n] = 0

while queue:
    now = queue.popleft()
    if now == k:
        break

    if 0 <= now * 2 <= 100000 and dots[now * 2] > dots[now]:
        queue.appendleft(now * 2)
        dots[now * 2] = dots[now]

    if 0 <= now + 1 <= 100000 and dots[now + 1] > dots[now]:
        queue.append(now + 1)
        dots[now + 1] = dots[now] + 1

    if 0 <= now - 1 <= 100000 and dots[now - 1] > dots[now]:
        queue.append(now - 1)
        dots[now - 1] = dots[now] + 1

print(dots[k])
