package heejik.`52week`

class `222-폴링` {

    var answer = 0

    fun solve() {
        val n = readln().toInt()
        val board = mutableListOf<List<Int>>()
        repeat(n) {
            board.add(readln().split(' ').map { it.toInt() })
        }
        polling(board = board, width = n, height = n).also {
            println(answer)
        }
    }

    private fun polling(board: List<List<Int>>, width: Int, height: Int) {
        if (width * height == 1) {
            answer = board[0][0]
            return
        }

        val newBoard = mutableListOf<List<Int>>()

        for (x in 0 until height step 2) {
            val row = mutableListOf<Int>()
            for (y in 0 until width step 2) {
                val tmp = mutableListOf<Int>()
                for (nx in x until x + 2) {
                    for (ny in y until y + 2) {
                        tmp.add(board[nx][ny])
                    }
                }
                tmp.sortDescending()
                row.add(tmp[1])
                tmp.clear()
            }
            newBoard.add(row)
        }

        polling(newBoard, width / 2, height / 2)
    }

}

fun main() {
    `222-폴링`().solve()
}