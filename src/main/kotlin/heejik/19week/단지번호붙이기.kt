package heejik.`19week`

class 단지번호붙이기 {

    val dx = listOf(-1, 1, 0, 0)
    val dy = listOf(0, 0, 1, -1)


    private val square = mutableListOf<MutableList<Int>>()
    fun solve() {

        val answers = mutableListOf<Int>()
        val n = readln().toInt()
        repeat(n) {
            square.add(readln().toList().map { it.digitToInt() }.toMutableList())
        }

        square.forEachIndexed { x, ints ->
            ints.forEachIndexed { y, i ->
                if (i == 1) {
                    answers.add(bfs(x, y))
                }
            }
        }
        println(answers.size)
        answers.sorted().forEach {
            println(it)
        }
    }

    fun bfs(firstX: Int, firstY: Int): Int {
        var cnt = 1
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.addFirst(Pair(firstX, firstY))
        square[firstX][firstY] = 0

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            for (i in dx.indices) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (nx !in 0 until square.size || ny !in 0 until square.size) continue
                if (square[nx][ny] == 1) {
                    square[nx][ny] = 0
                    queue.add(Pair(nx, ny))
                    cnt++
                }
            }
        }
        return cnt
    }
}

fun main() {
    단지번호붙이기().solve()
}