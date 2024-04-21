import sys

c, n = map(int, sys.stdin.readline().split())
prices = []

for i in range(n):
    prices.append(list(map(int, sys.stdin.readline().split())))

prices.sort(key=lambda x: x[0] / x[1])

dp = [100_001 for _ in range(c + 1)]
dp[0] = 0
for i in range(n):
    dp[1] = min(dp[1], prices[i][0])
    for j in range(2, c + 1):
        dp[j] = min(dp[j], dp[max(j - prices[i][1], 0)] + prices[i][0])

print(dp[-1])