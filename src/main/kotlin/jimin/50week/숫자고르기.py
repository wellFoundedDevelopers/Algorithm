'''
처음에는 그냥 dfs로 했더니 시간초과났다

사이클을 도는 친구를 찾아야한다..
https://velog.io/@deannn/BOJ-%EB%B0%B1%EC%A4%80-2668%EB%B2%88-%EC%88%AB%EC%9E%90%EA%B3%A0%EB%A5%B4%EA%B8%B0-Python
참고
'''

import sys
n = int(sys.stdin.readline())
numbers = [0]
for i in range(n):
    numbers.append(int(sys.stdin.readline()))

answer = set()


def dfs(first, second, now):
    first.add(now)
    second.add(numbers[now])
    if numbers[now] in first:
        if first == second:
            answer.update(first)
            return
        return
    return dfs(first, second, numbers[now])


for i in range(1, n + 1):
    if i not in answer:
        dfs(set(), set(), i)

print(len(answer))
for num in sorted(list(answer)):
    print(num)

