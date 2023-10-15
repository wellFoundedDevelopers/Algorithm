package hyunsoo.`48week`

import java.util.Stack

/**
 *
 * <문제>
 * [탑](https://www.acmicpc.net/problem/2493)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_탑` {

    fun solution() {

        val towerCnt = readln().toInt()
        val towerHeights = listOf(0) + readln().split(" ").map { it.toInt() }
        val laserPosList = IntArray(towerCnt + 1)

        val stack = Stack<Int>()

        for (index in towerCnt downTo 1) {

            val currentHeight = towerHeights[index]
            while (stack.isNotEmpty()) {

                val laserIndex = stack.pop()

                val laserHeight = towerHeights[laserIndex]

                if (currentHeight < laserHeight) {
                    stack.push(laserIndex)
                    break
                } else {
                    laserPosList[laserIndex] = index
                }
            }

            stack.add(index)
        }

        println(
            if (laserPosList.drop(1).all { it == 0 }) 0
            else laserPosList.drop(1).joinToString(" ")
        )
    }
}

fun main() {
    전현수_탑().solution()
}