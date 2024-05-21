import heapq
import sys

input = sys.stdin.readline


def solve():
    while people:
        start, end = heapq.heappop(people)

        # 시간이 다 된 자리는 뺀다.
        while seat and seat[0][0] <= start:
            end_time, idx = heapq.heappop(seat)
            heapq.heappush(possible_index, idx)

        # 사람을 자리에 넣는 부분
        if possible_index:
            idx = heapq.heappop(possible_index)
        else:
            idx = len(seat)

        heapq.heappush(seat, [end, idx])
        answer[idx] += 1

    count_seat = answer.index(0)
    print(count_seat)
    print(*answer[:count_seat])


if __name__ == '__main__':
    n = int(input())
    people = []
    seat = []
    possible_index = []
    answer = [0 for _ in range(n + 1)]

    for _ in range(n):
        start, end = map(int, input().split())
        heapq.heappush(people, [start, end])

    solve()
