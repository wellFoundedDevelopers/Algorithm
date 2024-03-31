def sort(arr):
    global real_arr
    if len(arr) >= len(arr[0]):
        real_arr = sort_row(arr)
    else:
        real_arr = sort_column(arr)

    if len(real_arr) > 100:
        real_arr = real_arr[:101]

    if len(real_arr[0]) > 100:
        for idx, row in enumerate(real_arr):
            real_arr[idx] = row[:101]


def sort_row(arr):
    new_arr = []
    for row in arr:
        new_arr.append(define_new_arr(row))

    max_len = max(map(lambda x: len(x), new_arr))
    for row in new_arr:
        row += [0 for _ in range(max_len - len(row))]

    return new_arr


def sort_column(arr):
    arr = list(zip(*arr))
    return list(zip(*sort_row(arr)))
    # tmp_arr = []
    # column_len = len(arr[0])
    #
    # for i in range(column_len):
    #     column = []
    #     for j in range(len(arr)):
    #         column.append(arr[j][i])
    #     tmp_arr.append(define_new_arr(column))
    #
    # max_len = max(map(lambda x: len(x), tmp_arr))
    # for row in tmp_arr:
    #     row += [0 for _ in range(max_len - len(row))]
    #
    # new_arr = [[0 for _ in range(len(tmp_arr))] for _ in range(len(tmp_arr[0]))]
    #
    # for i in range(len(tmp_arr[0])):
    #     for j in range(len(tmp_arr)):
    #         new_arr[i][j] = tmp_arr[j][i]
    #
    # return new_arr


def define_new_arr(arr):
    arr = list(filter(lambda x: x != 0, arr))
    new_arr = []
    count_of_num = dict()

    for num in arr:
        if num not in count_of_num:
            count_of_num[num] = 1
        else:
            count_of_num[num] += 1
    sorted_data = sorted(count_of_num.items(), key=lambda x: (x[1], x[0]))

    for num, count in sorted_data:
        new_arr.append(num)
        new_arr.append(count)

    return new_arr


if __name__ == '__main__':
    r, c, k = map(int, input().split())
    real_arr = [list(map(int, input().split())) for _ in range(3)]

    count = 0
    while count <= 100:
        if len(real_arr) > r - 1 and len(real_arr[0]) > c - 1:
            if real_arr[r - 1][c - 1] == k:
                break
        sort(real_arr)
        count += 1

    print(count if count <= 100 else count - 102)
