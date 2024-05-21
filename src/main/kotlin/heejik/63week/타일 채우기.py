if __name__ == '__main__':
    n = int(input())
    dp = [0 for _ in range(n + 1)]
    if n % 2 == 1:
        print(dp[n])
        exit(0)

    dp[2] = 3

    for x in range(4, n + 1, 2):
        dp[x] += (dp[x - 2] * dp[2])
        for i in range(2, x - 2, 2):
            dp[x] += 2 * dp[i]
        dp[x] += 2

    print(dp[n])
