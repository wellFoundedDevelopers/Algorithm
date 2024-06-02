package hyunsoo.`65week`

import java.util.LinkedList
import java.util.Queue

/**
 *
 * <문제>
 * [캐슬 디펜스](https://www.acmicpc.net/problem/17135)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_캐슬_디펜스` {

    private data class Position(val x: Int, val y: Int)

    private data class Bundle(val pos: Position, val range: Int)

    private var n = 0
    private var m = 0
    private var range = 0

    private val board = mutableListOf<MutableList<Int>>()

    private val pickedNumList = mutableListOf<Int>()

    var answer = 0

    // 좌 상 우
    private val dirs = listOf(
        Position(0, -1),
        Position(-1, 0),
        Position(0, 1),
    )

    fun solution() {

        readln().split(" ").map { it.toInt() }.apply {
            n = this[0]
            m = this[1]
            range = this[2]
        }

        repeat(n) { rowIndex ->
            val row = readln().split(" ").map { it.toInt() } as MutableList
            board.add(row)
        }

        for (i in 0..m - 3) {
            getCombinations(3, 0, i)
        }

        println(answer)
    }

    private fun getCombinations(depth: Int, cnt: Int, startIndex: Int) {

        if (depth == cnt) {
            check(pickedNumList)
        }

        for (index in startIndex until m) {
            pickedNumList.add(index)
            getCombinations(depth, cnt + 1, index + 1)
            pickedNumList.removeLast()
        }

    }

    private fun check(archersYList: List<Int>) {

        val curBoard = board.deepCopy()

        var curEliminatedCnt = 0

        for (archerX in n - 1 downTo 0) {

            val targetSet = mutableSetOf<Position>()

            archersYList.forEach { curArcherY ->

                val queue: Queue<Bundle> = LinkedList()

                queue.add(Bundle(Position(archerX, curArcherY), 1))

                while (queue.isNotEmpty()) {

                    val (curPos, curRange) = queue.poll()

                    if (curBoard[curPos.x][curPos.y] == 1) {
                        targetSet.add(Position(curPos.x, curPos.y))
                        return@forEach
                    }

                    if (curRange < range) {
                        dirs.forEach dir@{
                            val nx = curPos.x + it.x
                            val ny = curPos.y + it.y


                            if (nx !in 0 until n ||
                                ny !in 0 until m
                            ) return@dir

                            queue.add(Bundle(Position(nx, ny), curRange + 1))
                        }
                    }
                }
            }

            targetSet.forEach {
                curBoard[it.x][it.y] = 0
                curEliminatedCnt++
            }
        }

        if (answer < curEliminatedCnt) answer = curEliminatedCnt

    }

    private fun MutableList<MutableList<Int>>.deepCopy() =
        this.map {
            it.toMutableList()
        }.toMutableList()
}

fun main() {
    전현수_캐슬_디펜스().solution()
}