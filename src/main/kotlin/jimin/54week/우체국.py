'''
[우체국](https://www.acmicpc.net/problem/2141)

https://tussle.tistory.com/1050 블로그 참고
왼쪽 오른쪽 사람 수가 균일한 위치를 찾는다.
이를 위해서 입력받을때 전체 사람수 total을 만들고,
마을을 순차탐색하면서 왼쪽과 오른쪽 사람의 차이가 가장 적은 위치를 구한다.

'''

import sys

n = int(sys.stdin.readline())
town = []
total = 0
for i in range(n):
    x, a = map(int, sys.stdin.readline().split())
    total += a
    town.append([x, a])

town.sort(key= lambda x: x[0])

people = 0
mini = 1_000_000_000 * 100_000
result = -1
for i in range(n):
    if abs(people - (total - people - town[i][1])) < mini:
        mini = abs(people - (total - people - town[i][1]))
        result = town[i][0]

    people += town[i][1]

print(result)