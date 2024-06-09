package heejik.`66week`

private class `혼자서 하는 틱택토` {
    val bingoPos = listOf(
        // 가로
        listOf(0 to 0, 0 to 1, 0 to 2),
        listOf(1 to 0, 1 to 1, 1 to 2),
        listOf(2 to 0, 2 to 1, 2 to 2),
        // 세로
        listOf(0 to 0, 1 to 0, 2 to 0),
        listOf(0 to 1, 1 to 1, 2 to 1),
        listOf(0 to 2, 1 to 2, 2 to 2),
        // 대각
        listOf(0 to 0, 1 to 1, 2 to 2),
        listOf(2 to 0, 1 to 1, 0 to 2),
    )

    fun solution(board: Array<String>): Int {
        var isCorrectGame = true
        var isOWin = false
        var isXWin = false
        var OCount = 0
        var XCount = 0

        board.forEach { row ->
            OCount += row.count { it == 'O' }
            XCount += row.count { it == 'X' }
        }
        if (OCount - XCount !in 0..1) isCorrectGame = false

        bingoPos.forEach { posList ->
            val bingo = posList.map { pos ->
                board[pos.first][pos.second]
            }
            when (bingo.joinToString("")) {
                "OOO" -> isOWin = true
                "XXX" -> isXWin = true
            }
        }

        if (isOWin && isXWin) isCorrectGame = false
        if (isOWin && OCount - XCount != 1) isCorrectGame = false
        if (isXWin && OCount - XCount != 0) isCorrectGame = false

        return if (isCorrectGame) 1 else 0
    }
}