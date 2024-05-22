import sys

n = int(sys.stdin.readline())

dp = [0 for _ in range(31)]

dp[2] = 3
dp[4] = dp[2] * 3 + 2

for i in range(6, n + 1, 2):
    dp[i] += dp[i - 2] * 3
    for j in range(i - 4, 1, -2):
        dp[i] += dp[j] * 2
    dp[i] += 2

print(dp[n])