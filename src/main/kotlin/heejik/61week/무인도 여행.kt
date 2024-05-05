package heejik.`61week`

class `무인도 여행` {
    data class Pos(
        val x: Int,
        val y: Int
    )

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)
    lateinit var maps: Array<String>
    lateinit var visited: List<BooleanArray>
    val answers = mutableListOf<Int>()
    fun solution(_maps: Array<String>): IntArray {
        maps = _maps
        visited = List(size = maps.size) { BooleanArray(size = maps.first().length) }

        maps.forEachIndexed { x, row ->
            row.forEachIndexed { y, c ->
                c.digitToIntOrNull()?.let { digitC ->
                    if (visited[x][y].not()) {
                        answers.add(bfs(start = Pos(x, y) to digitC))
                    }
                }
            }
        }

        return if (answers.isNotEmpty()) answers.sorted().toIntArray() else intArrayOf(-1)
    }

    private fun bfs(start: Pair<Pos, Int>): Int {
        var sumOfDays = start.second
        val queue = ArrayDeque<Pos>()
        queue.add(start.first)
        visited[start.first.x][start.first.y] = true

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            for (i in 0..3) {
                val nx = dx[i] + x
                val ny = dy[i] + y
                if (nx !in maps.indices || ny !in 0 until maps.first().length) continue
                if (visited[nx][ny]) continue
                if (maps[nx][ny] == 'X') continue
                visited[nx][ny] = true
                queue.add(Pos(nx, ny))
                sumOfDays += maps[nx][ny].digitToInt()
            }
        }

        return sumOfDays
    }
}

fun main() {
    `무인도 여행`().solution(_maps = arrayOf("X591X", "X1X5X", "X231X", "1XXX1"))
}