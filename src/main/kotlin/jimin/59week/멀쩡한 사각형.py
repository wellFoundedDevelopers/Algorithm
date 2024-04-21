def solution(w, h):
    answer = 1

    gcd = 0
    for i in range(min(w, h), 0, -1):
        if w % i == 0 and h % i == 0:
            gcd = i
            break

    answer = w * h - (w // gcd + h // gcd - 1) * gcd

    return answer