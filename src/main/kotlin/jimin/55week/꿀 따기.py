'''
https://david0506.tistory.com/68
블로그 참고
'''

import sys

n = int(sys.stdin.readline())
honey = list(map(int, sys.stdin.readline().split()))

honey_sum1 = [honey[0]]
honey_sum2 = [0 for _ in range(n)]

for i in range(1, n):
    honey_sum1.append(honey_sum1[-1] + honey[i])

honey_sum2[-1] = honey[-1]
for i in range(n - 2, -1, -1):
    honey_sum2[i] = honey_sum2[i + 1] + honey[i]

result = 0
# 벌 벌 꿀
bee1 = honey_sum1[-1] - honey[0]
for i in range(1, n):
    bee2 = honey_sum1[-1] - honey_sum1[i]
    result = max(result, bee1 - honey[i] + bee2)

# 꿀 벌 벌
bee1 = honey_sum2[0] - honey[-1]
for i in range(n - 2, 0, -1):
    bee2 = honey_sum2[0] - honey_sum2[i]
    result = max(result, bee1 - honey[i] + bee2)

# 벌 꿀 벌
for i in range(1, n - 1):
    bee1 = honey_sum1[i] - honey[0]
    bee2 = honey_sum2[i] - honey[-1]
    result = max(result, bee1 + bee2)

print(result)
