/*
<문제>
[과제](https://www.acmicpc.net/problem/13904)

<구현 방법>
dp로 구현.
마감일을 기준으로 과제를 정렬한다.
행을 마감일로, 열을 날짜로 하는 2차원 dp 테이블을 만든다. (열을 n + 1로 했는데 이러면 안될 것 같은데..?)
열이 마감일보다 작거나 같을 때,
이전 과제에서 현재로부터 전날 점수와 현재 과제 점수를 합한 것과 이전과제에서 현재 점수 중 큰 것으로 업데이트해준다.


<트러블 슈팅>
그리디인데 dp로 풀었네..?
근데 dp 테이블 범위 잘 못 짠 것 같다.

*/

n = int(input())
assignments = [[0, 0]]
for i in range(n):
    assignments.append(list(map(int, input().split())))
assignments = sorted(assignments, key = lambda  x:x[0])
dp = [[0 for _ in range(n + 1)] for _ in range(n + 1)]

for i in range(1, n + 1):
    for j in range(1, n + 1):
        if j <= assignments[i][0]:
            if dp[i - 1][j - 1] + assignments[i][1] > dp[i - 1][j]:
                dp[i][j] = dp[i - 1][j - 1] + assignments[i][1]
            else:
                dp[i][j] = dp[i - 1][j]
        else:
            dp[i][j] = dp[i - 1][j]

max_value = 0
for d in dp:
    max_value = max(max(d), max_value)
print(max_value)