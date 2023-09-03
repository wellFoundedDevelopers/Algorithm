'''
재귀로 했더니 시간초과가 났다.
재귀의 경우 2^(n + 1) - 2의 시간이 걸린다.
하지만 dp 테이블을 만들경우 n * m 으로 50,000만 걸린다!
https://jshong1125.tistory.com/56
'''


n, s, m = map(int, input().split())
v = list(map(int, input().split()))
dp = [[0 for _ in range(m + 1)] for _ in range(n + 1)]
dp[0][s] = 1
for i in range(0, n):
    for j in range(m + 1):
        if dp[i][j] == 1:
            plus = j + v[i]
            minus = j - v[i]
            if 0 <= plus <= m:
                dp[i + 1][plus] = 1
            if 0 <= minus <= m:
                dp[i + 1][minus] = 1

maxi = -1
for i in range(m + 1):
    if dp[-1][i] == 1:
        maxi = i
print(maxi)