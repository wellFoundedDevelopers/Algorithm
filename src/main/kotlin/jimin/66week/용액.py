import sys

n = int(sys.stdin.readline())
feats = list(map(int, sys.stdin.readline().split()))

start = 0
end = n - 1
mini = 2_000_000_000
ms, me = feats[0], feats[n - 1]
while start < end:
    if mini > abs(feats[start] + feats[end]):
        mini = abs(feats[start] + feats[end])
        ms, me = feats[start], feats[end]

    if feats[start] + feats[end] < 0:
        start += 1
    elif feats[start] + feats[end] > 0:
        end -= 1
    else:
        break

print(ms, me)