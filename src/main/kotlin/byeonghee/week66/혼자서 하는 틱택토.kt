package byeonghee.week66

class 소병희_혼자서_하는_틱택토 {
    fun solution(board: Array<String>): Int {
        var oCnt = 0
        var xCnt = 0

        for(i in 0 until 3) for(j in 0 until 3) {
            when(board[i][j]) {
                'O' -> oCnt++
                'X' -> xCnt++
            }
        }

        var oBingo = countBingo(board, 'O')
        var xBingo = countBingo(board, 'X')

        if (oBingo > 1 && xBingo > 1) return 0
        if (oBingo >= 1 && oCnt - xCnt != 1) return 0
        if (xBingo >= 1 && oCnt - xCnt != 0) return 0
        if (oCnt > xCnt + 1) return 0
        if (xCnt > oCnt) return 0

        return 1
    }

    fun countBingo(board: Array<String>, player: Char): Int {
        var answer = 0

        for(i in 0 until 3) {
            if (board[i][0] == player && board[i][0] == board[i][1] && board[i][1] == board[i][2]) answer++
            if (board[0][i] == player && board[0][i] == board[1][i] && board[1][i] == board[2][i]) answer++
        }

        if (board[1][1] == player && board[0][0] == board[1][1] && board[1][1] == board[2][2]) answer++
        if (board[1][1] == player && board[2][0] == board[1][1] && board[1][1] == board[0][2]) answer++

        return answer
    }
}

fun main() {
    val sol = 소병희_혼자서_하는_틱택토()
    val ret = sol.solution(
            arrayOf("OOO", "O..", "XXX")
    )
    println(ret)
}