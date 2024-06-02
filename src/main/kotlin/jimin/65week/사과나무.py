import sys

n = int(sys.stdin.readline())
numbers = list(map(int, sys.stdin.readline().split()))

if sum(numbers) % 3 != 0:
    print("NO")
    exit()

share = 0
rest = 0

for i in range(n):
    share += numbers[i] // 2
    rest += numbers[i] % 2

if share < rest:
    print("NO")
else:
    print("YES")