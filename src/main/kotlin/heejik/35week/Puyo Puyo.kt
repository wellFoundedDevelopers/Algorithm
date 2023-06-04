package heejik.`35week`

class `Puyo Puyo` {

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    private val columnSize = 12
    private val rowSize = 6
    private val field = MutableList(6) { CharArray(12).toMutableList() }

    fun solve() {
        setting()
        startGame()
    }

    private fun setting() {
        repeat(columnSize) { x ->
            readln().forEachIndexed { y, c ->
                field[y][x] = c
            }
        }
    }

    private fun startGame() {
        var bombCount = 0

        while (true) {
            if (bomb().not()) break
            bombCount++
            fillEmpty()
        }

        println(bombCount)
    }

    private fun bomb(): Boolean {
        var isBombed = false
        repeat(rowSize) first@{ x ->
            repeat(columnSize) second@{ y ->
                if (field[x][y] == '.') return@second
                if (bomb(x, y, field[x][y])) isBombed = true
            }
        }
        return isBombed
    }

    private fun bomb(_x: Int, _y: Int, color: Char): Boolean {
        var cnt = 1
        val bombedIdx = mutableListOf<Pair<Int, Int>>(Pair(_x, _y))
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(Pair(_x, _y))

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()

            dx.indices.forEach { idx ->
                val nx = x + dx[idx]
                val ny = y + dy[idx]
                if (Pair(nx, ny) in bombedIdx) return@forEach
                if (nx !in 0 until rowSize || ny !in 0 until columnSize) return@forEach
                if (field[nx][ny] != color) return@forEach
                queue.add(Pair(nx, ny))
                bombedIdx.add(Pair(nx, ny))
                cnt++
            }
        }
        if (cnt < 4) return false
        bombedIdx.forEach { (x, y) ->
            field[x][y] = '.'
        }

        return true
    }

    private fun fillEmpty() {
        repeat(rowSize) { x ->
            val newRow = field[x].filter { it != '.' }.toMutableList()
            repeat(columnSize - newRow.size) {
                newRow.add(0, '.')
            }
            repeat(columnSize) {
                field[x][it] = newRow[it]
            }
        }
    }
}


fun main() {
    `Puyo Puyo`().solve()
}