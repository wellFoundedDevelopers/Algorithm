'''
[문자열 게임 2](https://www.acmicpc.net/problem/20437)
알파벳 배열을 만들어서 문자열 W의 각 문자 위치를 담았다.
이를 이용해서 해당 문자를 K개 포함할 때 최소와 최대를 업데이트 해주었다.

'''


import sys

t = int(sys.stdin.readline())

for i in range(t):
    w = sys.stdin.readline()
    k = int(sys.stdin.readline())

    alphabet = [[] for _ in range(26)]

    for i in range(len(w) - 1):
        alphabet[ord(w[i]) - ord('a')].append(i)

    mini = 10_001
    maxi = -1

    for i in range(26):
        if len(alphabet[i]) >= k:
            for j in range(0, len(alphabet[i]) - k + 1):
                mini = min(mini, alphabet[i][j + k - 1] - alphabet[i][j] + 1)
                maxi = max(maxi, alphabet[i][j + k - 1] - alphabet[i][j] + 1)

    if maxi == -1:
        print(-1)
    else:
        print(mini, maxi)

