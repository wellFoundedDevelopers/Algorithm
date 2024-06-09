package hyunsoo.`66week`

import java.util.LinkedList
import java.util.Queue
import kotlin.math.absoluteValue

/**
 *
 * <문제>
 * [맥주 마시면서 걸어가기](https://www.acmicpc.net/problem/9205)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_맥주_마시면서_걸어가기` {

    private data class Position(val x: Int, val y: Int) {

        fun getDiff(other: Position): Int {
            return (this.x - other.x).absoluteValue + (this.y - other.y).absoluteValue
        }
    }

    fun solution() {
        val testCnt = readln().toInt()

        repeat(testCnt) {

            val n = readln().toInt()

            var start = Position(-1, -1)
            val con = mutableListOf<Position>()
            var end = Position(-1, -1)

            repeat(n + 2) { index ->

                val curPos = readln().split(" ").let {
                    Position(it[0].toInt(), it[1].toInt())
                }

                when (index) {
                    0 -> {
                        start = curPos
                    }

                    n + 1 -> {
                        end = curPos
                    }

                    else -> {
                        con.add(curPos)
                    }
                }
            }

            val queue: Queue<Position> = LinkedList()
            val visited = BooleanArray(con.size)
            queue.add(start)


            var canGo = false
            while (queue.isNotEmpty()) {
                val curPos = queue.poll()

                if (curPos.getDiff(end) <= 1000) {
                    canGo = true
                    break
                }

                con.forEachIndexed { index, conPos ->
                    // 방문하지 않은 편의점이라면
                    if (visited[index].not()) {
                        if (curPos.getDiff(conPos) <= 1000) {
                            queue.add(conPos)
                            visited[index] = true
                        }
                    }
                }

            }

            if (canGo) {
                println("happy")
            } else {
                println("sad")
            }
        }
    }
}

fun main() {
    전현수_맥주_마시면서_걸어가기().solution()
}