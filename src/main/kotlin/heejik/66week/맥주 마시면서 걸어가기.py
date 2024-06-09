from collections import deque


def get_diff(pos1, pos2):
    return abs(pos1[0] - pos2[0]) + abs(pos1[1] - pos2[1])


def solve():
    can_go = False
    store_pos = []
    n = int(input())
    visited_store_idx = [False] * (n + 1)
    start_pos = list(map(int, input().split()))
    for _ in range(n):
        pos = list(map(int, input().split()))
        store_pos.append(pos)
    end_pos = list(map(int, input().split()))
    store_pos.append(end_pos)

    queue = deque()
    queue.append(start_pos)

    while queue:
        s_pos = queue.popleft()
        if s_pos == end_pos:
            can_go = True
            break
        for (idx, store) in enumerate(store_pos):
            if visited_store_idx[idx]: continue
            diff = get_diff(s_pos, store)
            if diff > 1000: continue

            queue.append(store)
            visited_store_idx[idx] = True

    print("happy" if can_go else "sad")


if __name__ == '__main__':
    t = int(input())
    for _ in range(t):
        solve()
