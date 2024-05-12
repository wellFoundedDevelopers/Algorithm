import sys

t = int(sys.stdin.readline())

for i in range(t):
    n = int(sys.stdin.readline())
    coin_types = list(map(int, sys.stdin.readline().split()))
    answer_coin = int(sys.stdin.readline())

    dp = [0 for _ in range(answer_coin + 1)]
    dp[0] = 1
    for i in range(len(coin_types)):
        for j in range(1, answer_coin + 1):
            if j < coin_types[i]:
                continue
            if i == 0:
                if j % coin_types[i] == 0:
                    dp[j] = 1
            else:
                dp[j] = dp[j] + dp[j - coin_types[i]]

    print(dp[-1])
