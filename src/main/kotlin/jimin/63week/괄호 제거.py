import sys
from collections import deque
from itertools import combinations

s = sys.stdin.readline()
couples = []

queue = deque([])
for i in range(len(s)):
    if s[i] == '(':
        queue.append(i)
    elif s[i] == ')':
        start = queue.pop()
        couples.append([start, i])


def get_combi(n):
    combis = []

    lst = [j for j in range(n)]
    for i in range(1, n + 1):
        combis.append(list(combinations(lst, i)))

    return combis


combis = get_combi(len(couples))
result = set()
for c in combis:
    for t in c:
        ss = list(s[:])
        for j in t:
            ss[couples[j][0]] = ''
            ss[couples[j][1]] = ''
        result.add(''.join(ss))


result = list(result)
result.sort()
print(''.join(result))