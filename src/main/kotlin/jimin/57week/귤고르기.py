from collections import defaultdict


def solution(k, tangerine):
    answer = 0
    sumi = 0

    tanger_info = defaultdict(int)
    for i in range(len(tangerine)):
        tanger_info[tangerine[i]] += 1

    tanger_info = sorted(tanger_info.values(), reverse=True)
    for i in range(len(tanger_info)):
        answer += 1
        sumi += tanger_info[i]

        if sumi >= k:
            break
    return answer