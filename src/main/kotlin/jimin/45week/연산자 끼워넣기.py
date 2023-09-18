n = int(input())
a = list(map(int, input().split()))
operators = list(map(int, input().split()))
maxi = -100000000000
mini = 100000000000

def calculate(idx, now):
    global maxi, mini
    if idx == len(a):
        maxi = max(maxi, now)
        mini = min(mini, now)
        return

    for i in range(4):
        if operators[i] != 0:
            operators[i] -= 1
            if i == 0:
                new = now + a[idx]
            elif i == 1:
                new = now - a[idx]
            elif i == 2:
                new = now * a[idx]
            else:
                if now < 0:
                    new = -(-now // a[idx])
                else:
                    new = now // a[idx]
            calculate(idx + 1, new)
            operators[i] += 1


calculate(1, a[0])
print(maxi)
print(mini)