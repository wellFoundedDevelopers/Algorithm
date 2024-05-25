package heejik.`64week`


private class 치즈 {

    val paper = mutableListOf<MutableList<Int>>()
    lateinit var tmpPosForMelt: List<BooleanArray>
    lateinit var airPos: List<BooleanArray>
    var time = 0
    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)
    var n = 0
    var m = 0

    fun solve() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }
        tmpPosForMelt = List(size = n) { BooleanArray(size = m) }
        airPos = List(size = n) { BooleanArray(size = m) }

        repeat(n) {
            paper.add(readln().split(' ').map { it.toInt() }.toMutableList())
        }

        while (check().not()) {
            melt().also {
                time += 1
            }
        }

        println(time)
    }

    fun melt() {
        // 공기 파악(bfs)
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(0 to 0)
        airPos[0][0] = true
        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            repeat(4) { i ->
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (nx !in 0 until n || ny !in 0 until m) return@repeat
                if (airPos[nx][ny]) return@repeat
                if (paper[nx][ny] == 1) return@repeat
                queue.add(nx to ny)
                airPos[nx][ny] = true
            }
        }


        // 치즈 녹이기
        paper.forEachIndexed { x, row ->
            row.forEachIndexed second@{ y, value ->
                if (value != 1) return@second
                var airCount = 0
                repeat(4) { i ->
                    val nx = x + dx[i]
                    val ny = y + dy[i]
                    if (nx !in 0 until n || ny !in 0 until m) return@repeat
                    if (airPos[nx][ny]) airCount += 1
                }
                if (airCount >= 2) tmpPosForMelt[x][y] = true
            }
        }

        tmpPosForMelt.forEachIndexed { x, booleans ->
            booleans.forEachIndexed { y, b ->
                if (b) {
                    paper[x][y] = 0
                    tmpPosForMelt[x][y] = false
                }
                airPos[x][y] = false
            }
        }
    }

    fun check(): Boolean = paper.all { row -> row.all { it == 0 } }
}

fun main() {
    치즈().solve()
}