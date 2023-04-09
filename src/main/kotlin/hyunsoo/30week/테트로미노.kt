package hyunsoo.`30week`

/**
 *
 * <문제>
 * [테트로미노](https://www.acmicpc.net/problem/14500)
 *
 * - 아이디어
 *
 *
 * - 트러블 슈팅
 *
 */

class `전현수_테트로미노` {

    private inner class Position(val x: Int, val y: Int) {

        fun up() = Position(this.x - 1, this.y)
        fun down() = Position(this.x + 1, this.y)
        fun left() = Position(this.x, this.y - 1)
        fun right() = Position(this.x, this.y + 1)

        fun isInBoard() = this.x in 0 until row && this.y in 0 until column
    }

    // 동 서 남 북
    private val dirs = listOf(
        Position(0, 1),
        Position(0, -1),
        Position(1, 0),
        Position(-1, 0),
    )


    private var row = 0
    private var column = 0
    private var maxScore = 0

    private val board = mutableListOf<List<Int>>()
    private lateinit var visited: Array<BooleanArray>

    fun solution() {

        readln().split(" ").map { it.toInt() }.apply {
            row = this.first()
            column = this.last()
            visited = Array(row) {
                BooleanArray(column)
            }
        }

        repeat(row) {
            val columnData = readln().split(" ").map { it.toInt() }
            board.add(columnData)
        }

        for (i in 0 until row) {
            for (j in 0 until column) {

                val curPos = Position(i, j)

                visited[i][j] = true
                checkTetromino(curPos, board[i][j])
                visited[i][j] = false
                checkLastTetromino(curPos)

            }
        }

        println(maxScore)
    }

    private fun checkTetromino(curPos: Position, curScore: Int, cnt: Int = 1) {

        if (cnt == 4) {
            if (maxScore < curScore) maxScore = curScore
            return
        }
        for (index in 0 until 4) {

            val dir = dirs[index]
            val nx = curPos.x + dir.x
            val ny = curPos.y + dir.y

            if (nx !in 0 until row || ny !in 0 until column) continue
            if (visited[nx][ny]) continue

            visited[nx][ny] = true
            checkTetromino(
                Position(nx, ny), curScore + board[nx][ny], cnt + 1
            )
            visited[nx][ny] = false


        }
    }

    private fun checkLastTetromino(curPos: Position) {
        checkFirst(curPos)
        checkSecond(curPos)
        checkThird(curPos)
        checkFourth(curPos)
    }

    private fun checkFirst(curPos: Position) {

        val curShapePosList = mutableListOf<Position>()
        val first = curPos.apply {
            curShapePosList.add(this)
        }
        val second = curPos.right().apply {
            curShapePosList.add(this)
        }
        val third = second.right().apply {
            curShapePosList.add(this)
        }
        val fourth = second.down().apply {
            curShapePosList.add(this)
        }

        var curScore = 0
        curShapePosList.forEach { pos ->
            if (pos.isInBoard()) {
                curScore += board[pos.x][pos.y]
            } else return
        }
        if (maxScore < curScore) maxScore = curScore
    }

    private fun checkSecond(curPos: Position) {

        val curShapePosList = mutableListOf<Position>()
        val first = curPos.apply {
            curShapePosList.add(this)
        }
        val second = curPos.right().apply {
            curShapePosList.add(this)
        }
        val third = second.right().apply {
            curShapePosList.add(this)
        }
        val fourth = second.up().apply {
            curShapePosList.add(this)
        }

        var curScore = 0
        curShapePosList.forEach { pos ->
            if (pos.isInBoard()) {
                curScore += board[pos.x][pos.y]
            } else return
        }
        if (maxScore < curScore) maxScore = curScore
    }

    private fun checkThird(curPos: Position) {

        val curShapePosList = mutableListOf<Position>()
        val first = curPos.apply {
            curShapePosList.add(this)
        }
        val second = curPos.up().apply {
            curShapePosList.add(this)
        }
        val third = second.up().apply {
            curShapePosList.add(this)
        }
        val fourth = second.right().apply {
            curShapePosList.add(this)
        }

        var curScore = 0
        curShapePosList.forEach { pos ->
            if (pos.isInBoard()) {
                curScore += board[pos.x][pos.y]
            } else return
        }
        if (maxScore < curScore) maxScore = curScore
    }

    private fun checkFourth(curPos: Position) {

        val curShapePosList = mutableListOf<Position>()
        val first = curPos.apply {
            curShapePosList.add(this)
        }
        val second = curPos.up().apply {
            curShapePosList.add(this)
        }
        val third = second.up().apply {
            curShapePosList.add(this)
        }
        val fourth = second.left().apply {
            curShapePosList.add(this)
        }

        var curScore = 0
        curShapePosList.forEach { pos ->
            if (pos.isInBoard()) {
                curScore += board[pos.x][pos.y]
            } else return
        }
        if (maxScore < curScore) maxScore = curScore
    }
}

fun main() {
    전현수_테트로미노().solution()
}