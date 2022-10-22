package heejik.`6week`

// 답보풀


class 아기상어 {


    val river = mutableListOf<MutableList<Int>>()
    var n = 0
    var m = 0
    val dx = listOf(0, 0, -1, 1, 1, 1, -1, -1)
    val dy = listOf(1, -1, 0, 0, 1, -1, 1, -1)
    val queue = ArrayDeque<Pair<Int,Int>>()

    fun solve() {

        readln().split(' ').map { it.toInt() }.toMutableList().apply {
            n = first()
            m = last()
        }


        repeat(n) {
            river.add(readln().split(' ').map { it.toInt() }.toMutableList())
        }

        river.forEachIndexed { i, ints ->
            ints.forEachIndexed { j, int ->
                if (int == 1) {
                    queue.add(Pair(i, j))
                }
            }
        }
        checkShark()
        println(river.maxOf { it.maxOrNull()!! } -1)
    }

    private fun checkShark() {
        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            for (i in dx.indices) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (nx in 0 until n && ny in 0 until m) {
                    if (river[nx][ny] == 0) {
                        river[nx][ny] = river[x][y] + 1
                        queue.add(Pair(nx,ny))
                    }
                }
            }
        }
    }
}

fun main() {
    아기상어().solve()
}