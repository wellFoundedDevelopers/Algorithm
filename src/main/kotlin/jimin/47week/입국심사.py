import sys
def solution(n, times):
    answer = sys.maxsize

    front = 1
    last = 1_000_000_000 * 1_000_000_000

    while front <= last:
        mid = (front + last) // 2
        num = 0
        for t in times:
            num += mid // t
        if num < n:
            front = mid + 1
        else:
            last = mid - 1
            answer = mid


    return answer