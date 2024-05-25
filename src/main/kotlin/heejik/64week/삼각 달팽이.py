def solution(n):
    dx = [0, 1, -1]
    dy = [1, 0, -1]
    b = [[0] * i for i in range(1, n + 1)]
    x, y = 0, 0
    num = 1
    d = 0
    while num <= (n + 1) * n // 2:
        b[y][x] = num
        ny = y + dy[d]
        nx = x + dx[d]
        num += 1
        if 0 <= ny < n and 0 <= nx <= ny and b[ny][nx] == 0:
            y, x = ny, nx
        else:
            d = (d + 1) % 3
            y += dy[d]
            x += dx[d]
    return sum(b, [])


answer_list = []
input_number = 1


# # nest: 중첩 수
# def fill(start: int, cnt: int, nest: int):
#     global input_number
#     # /
#     input_idx = start
#     for i in range(cnt):
#         if i != 0: input_idx += i + (nest + nest)
#         answer_list[input_idx] = input_number
#         input_number += 1
#     # _
#     for i in range(cnt - 1):
#         input_idx += 1
#         answer_list[input_idx] = input_number
#         input_number += 1
#     # \
#     for i in range(cnt - 2):
#         input_idx -= cnt - i + (nest + nest)
#         answer_list[input_idx] = input_number
#         input_number += 1
#
#
# def solution(n):
#     global answer_list
#     answer_list = [0 for _ in range(((n + 1) * n) // 2)]
#     start = 0
#     nest = 0
#     tmp_for_start = 4
#     for k in range(n, 0, -3):
#         fill(start=start, cnt=k, nest=nest)
#         start += tmp_for_start
#         tmp_for_start += 4
#         nest += 1
#
#     return answer_list
