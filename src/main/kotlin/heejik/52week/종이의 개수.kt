package heejik.`52week`

import kotlin.properties.Delegates

class `종이의 개수` {
    private var n by Delegates.notNull<Int>()
    private val paper = mutableListOf<List<Int>>()
    private val answerCounts = IntArray(size = 3) { 0 }

    fun solve() {
        input()
        cutAndCheck(flattenCutList = paper.flatten(), rowCnt = n)
        answerCounts.forEach {
            println(it)
        }
    }

    private fun input() {
        n = readln().toInt()
        repeat(n) {
            paper.add(readln().split(' ').map { it.toInt() })
        }
    }

    private fun cutAndCheck(flattenCutList: List<Int>, rowCnt: Int) {
        if (flattenCutList.all { it == flattenCutList.first() }) {
            answerCounts[flattenCutList.first() + 1]++
            return
        }

        val offset = rowCnt / 3
        for (x in 0 until rowCnt step offset) {
            for (y in 0 until rowCnt step offset) {
                val newFlattenCutList = mutableListOf<Int>()
                for (i in x until x + offset) {
                    val fromIdx = (i * rowCnt) + y
                    newFlattenCutList += flattenCutList.subList(fromIndex = fromIdx, toIndex = fromIdx + offset)
                }
                cutAndCheck(flattenCutList = newFlattenCutList, rowCnt = rowCnt / 3)
            }
        }
    }
}

fun main() {
    `종이의 개수`().solve()
}