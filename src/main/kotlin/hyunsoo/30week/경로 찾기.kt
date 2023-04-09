package hyunsoo.`30week`

import java.util.*

/**
 *
 * <문제>
 * [경로 찾기](https://www.acmicpc.net/problem/11403)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_경로_찾기` {

    private data class Path(val start: Int, val end: Int)

    private lateinit var graphData: Array<IntArray>
    private var size = 0

    fun solution() {

        size = readln().toInt()

        graphData = Array(size) {
            readln().split(" ").map { it.toInt() }.toIntArray()
        }

        val ansData = Array(size) { IntArray(size) }

        for (start in 0 until size) {
            for (end in 0 until size) {

                if (start.canGo(end))
                    ansData[start][end] = CAN
                else ansData[start][end] = CANT
            }
        }

        ansData
            .joinToString("\n") {
                it.joinToString(" ")
            }.run {
                println(this)
            }
    }

    private fun Int.canGo(target: Int): Boolean {
        val visited = Array(size) {
            BooleanArray(size)
        }
        val queue: Queue<Path> = LinkedList()

        graphData[this].forEachIndexed { index, value ->
            if (value == CAN) queue.add(Path(this, index))
        }

        while (queue.isNotEmpty()) {

            val curPath = queue.poll()

            if (visited[curPath.start][curPath.end]) continue

            visited[curPath.start][curPath.end] = true

            if (curPath.end == target) return true

            graphData[curPath.end].forEachIndexed { index, value ->
                if (value == CAN) queue.add(Path(curPath.end, index))
            }
        }

        return false
    }

    companion object {
        private const val CAN = 1
        private const val CANT = 0
    }
}

fun main() {
    전현수_경로_찾기().solution()
}