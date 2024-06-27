def solution(data, col, row_begin, row_end):
    answer = 0
    data.sort(key=lambda x: (x[col - 1], -x[0]))

    for i in range(len(data[0])):
        answer += data[row_begin - 1][i] % row_begin

    for i in range(row_begin, row_end):
        sumi = 0
        for j in range(len(data[0])):
            sumi += data[i][j] % (i + 1)
        answer = answer ^ sumi

    return answer