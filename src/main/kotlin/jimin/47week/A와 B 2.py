'''
A와 B 문제를 풀고 아이디어를 얻음
(그냥 완탐시 시간초과)
S -> T 이니까 반대로 T를 S로 만들도록 함
뒤가 'A'면 그냥 맨 끝을 자르고
앞이 'B'이면 앞을 하나 자르고 뒤집는다!
'''

import sys
sys.setrecursionlimit(10**6)
a = list(sys.stdin.readline().rstrip())
b = list(sys.stdin.readline().rstrip())
result = 0


def set_recursion(b):
    global result, a
    if len(a) == len(b):
        if a == b:
            result = 1
        return

    if b[-1] == 'A':
        set_recursion(b[:-1])

    if b[0] == 'B':
        set_recursion(b[:0:-1])


set_recursion(b)
print(result)
