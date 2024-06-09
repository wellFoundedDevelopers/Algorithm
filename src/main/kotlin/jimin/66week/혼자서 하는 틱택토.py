def solution(board):
    x = 0
    o = 0
    for i in range(3):
        for j in range(3):
            if board[i][j] == 'X':
                x += 1
            elif board[i][j] == 'O':
                o += 1

    if x > o:
        return 0
    if x == 0 and o == 0:
        return 1
    if o - x > 1:
        return 0

    x_bingo = 0
    o_bingo = 0
    for i in range(3):
        # 가로
        first = board[i][0]
        if first == '.':
            continue

        isBingo = True
        for j in range(1, 3):
            if first != board[i][j]:
                isBingo = False
                break

        if isBingo:
            if first == 'X':
                x_bingo += 1
            elif first == 'O':
                o_bingo += 1

    for i in range(3):
        # 세로
        first = board[0][i]
        if first == '.':
            continue

        isBingo = True
        for j in range(1, 3):
            if first != board[j][i]:
                isBingo = False
                break

        if isBingo:
            if first == 'X':
                x_bingo += 1
            elif first == 'O':
                o_bingo += 1

    # 대각선
    if board[0][0] != '.' and board[0][0] == board[1][1] and board[1][1] == board[2][2]:
        if board[0][0] == 'X':
            x_bingo += 1
        elif board[0][0] == 'O':
            o_bingo += 1
    elif board[0][2] != '.' and board[0][2] == board[1][1] and board[1][1] == board[2][0]:
        if board[0][2] == 'X':
            x_bingo += 1
        elif board[0][2] == 'O':
            o_bingo += 1

    if o_bingo > x_bingo and o == x + 1:
        return 1
    if o_bingo < x_bingo and o == x:
        return 1
    if o_bingo == 0 and x_bingo == 0:
        return 1

    return 0

