'''
https://magentino.tistory.com/234
이해안감
'''

n, m, h = map(int, input().split())
students = []
for i in range(n):
    students.append([0] + list(map(int, input().split())))

dp = [[0 for _ in range(h + 1)] for _ in range(n + 1)]
dp[0][0] = 1

for i in range(n):
    for j in range(h + 1):
        if dp[i][j]:
            for s in students[i]:
                if j + s <= h:
                    dp[i + 1][j + s] = (dp[i + 1][j + s] + dp[i][j]) % 10007

print(dp[-1][-1])