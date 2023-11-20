from collections import defaultdict

def solution(want, number, discount):
    answer = 0

    d_info = dict()
    w_info = dict()
    for product in set(discount):
        d_info[product] = 0
        w_info[product] = 0

    for i in range(10):
        d_info[discount[i]] += 1

    for idx, val in enumerate(want):
        w_info[val] = number[idx]

    for i in range(10, len(discount)):
        if w_info == d_info:
            answer += 1

        d_info[discount[i - 10]] -= 1
        d_info[discount[i]] += 1

    if w_info == d_info:
        answer += 1

    return answer