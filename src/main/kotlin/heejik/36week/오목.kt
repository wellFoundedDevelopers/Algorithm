package heejik.`36week`

class 오목 {

    private val rightX = listOf(-1, 0, 1, 1)     // 상우, 우, 하우, 하
    private val rightY = listOf(1, 1, 1, 0)

    private val leftX = listOf(1, 0, -1, -1)     // 하좌, 좌, 상좌, 상
    private val leftY = listOf(-1, -1, -1, 0)

    data class Pos(
        val x: Int,
        val y: Int
    )

    private val table = MutableList(19) { MutableList(19) { 0 } }

    fun solve() {
        setTable()

        kotlin.runCatching {
            getWinnerPosition()
        }.onSuccess {
            println(table[it.x][it.y])
            println("${it.x + 1} ${it.y + 1}")
        }.onFailure {
            println(0)
        }
    }

    private fun setTable() {
        repeat(19) { x ->
            readln().split(' ').map { it.toInt() }.forEachIndexed { y, value ->
                table[x][y] = value
            }
        }
    }

    private fun getWinnerPosition(): Pos {
        repeat(19) { x ->
            repeat(19) { y ->
                if (table[x][y] != 0) {
                    if (canWinner(Pos(x, y))) return Pos(x, y)
                }
            }
        }

        throw Exception("can't find winner")
    }

    private fun canWinner(pos: Pos): Boolean {
        val color = table[pos.x][pos.y]

        rightX.indices.forEach {
            var (x, y) = pos
            var count = 1

            while (true) {
                x += rightX[it]
                y += rightY[it]
                if (x !in 0 until 19 || y !in 0 until 19) break
                if (table[x][y] != color) break
                count++
            }

            if (count != 5) return@forEach


            x = pos.x
            y = pos.y
            while (true) {
                x += leftX[it]
                y += leftY[it]
                if (x !in 0 until 19 || y !in 0 until 19) break
                if (table[x][y] != color) break
                count++
            }

            if (count == 5) return true
        }

        return false
    }
}

fun main() {
    오목().solve()
}