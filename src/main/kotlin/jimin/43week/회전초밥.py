'''
sushi[:3000] 까지만 더 해야 시간초과 안남!
'''

n, d, k, c = map(int, input().split())
sushi = []
for i in range(n):
    sushi.append(int(input()))

maxi = 0
sushi += sushi[:3000]
for i in range(n):
    eaten_sushi = sushi[i : i + k]
    eaten_sushi.append(c)
    eaten_sushi_length = len(list(set(eaten_sushi)))
    maxi = max(maxi, eaten_sushi_length)

print(maxi)