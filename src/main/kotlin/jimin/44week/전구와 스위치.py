'''
https://astrid-dm.tistory.com/429
첫번째 전구를 누르고 시작하는 경우,
첫번째 전구를 누르지 않고 시작하는 경우
2가지를 구해서 적은 횟수로 출력!
'''

n = int(input())
before = list(map(int, list(input())))
after = list(map(int, list(input())))

# 첫번째 전구 안누름
now_1 = before[:]
num_1 = 0
for i in range(0, n - 1):
    if now_1[i] != after[i]:
        num_1 += 1
        now_1[i] = 1 - now_1[i]
        now_1[i + 1] = 1 - now_1[i + 1]
        if i + 2 < n:
            now_1[i + 2] = 1 - now_1[i + 2]

now_2 = before[:]
now_2[0] = not now_2[0]
now_2[1] = not now_2[1]
num_2 = 1
for i in range(0, n - 1):
    if now_2[i] != after[i]:
        num_2 += 1
        now_2[i] = 1 - now_2[i]
        now_2[i + 1] = 1 - now_2[i + 1]
        if i + 2 < n:
            now_2[i + 2] = 1 - now_2[i + 2]

if now_1 != after and now_2 == after:
    print(num_2)
elif now_2 != after and now_1 == after:
    print(num_1)
elif now_1 == after and now_2 == after:
    print(min(num_1, num_2))
else:
    print(-1)
