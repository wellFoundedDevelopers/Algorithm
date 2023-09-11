package hyunsoo.`44week`

import kotlin.math.min

/**
 *
 * <문제>
 * [전구와 스위치](https://www.acmicpc.net/problem/2138)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_전구와_스위치` {

    private var bulbCnt = 0

    fun solution() {

        bulbCnt = readln().toInt()

        val currentState = readln().chunked(1)
            .toTypedArray()

        val targetState = readln().chunked(1)
            .toTypedArray()


        var firstCaseCnt = 0
        val firstCase = currentState.toList().toTypedArray()
        var secondCaseCnt = 1
        val secondCase = currentState.toList().toTypedArray().apply {
            this.singleSwitch(0)
            this.singleSwitch(1)
        }

        for (index in 1 until bulbCnt) {
            if (firstCase[index - 1] != targetState[index - 1]) {
                firstCase.switch(index)
                firstCaseCnt++
            }
            if (secondCase[index - 1] != targetState[index - 1]) {
                secondCase.switch(index)
                secondCaseCnt++
            }
        }

        when {
            firstCase.contentEquals(targetState) -> {
                if (firstCase.contentEquals(secondCase)) {
                    println(min(firstCaseCnt, secondCaseCnt))
                } else {
                    println(firstCaseCnt)
                }
            }

            secondCase.contentEquals(targetState) -> {
                if (firstCase.contentEquals(secondCase)) {
                    println(min(firstCaseCnt, secondCaseCnt))
                } else {
                    println(secondCaseCnt)
                }
            }

            else -> println(-1)
        }


    }

    private fun Array<String>.switch(pos: Int) {
        when (pos) {
            bulbCnt - 1 -> {
                this.singleSwitch(pos - 1)
                this.singleSwitch(pos)
            }

            else -> {
                this.singleSwitch(pos - 1)
                this.singleSwitch(pos)
                this.singleSwitch(pos + 1)
            }
        }
    }

    private fun Array<String>.singleSwitch(pos: Int) {
        if (this[pos] == "0") this[pos] = "1" else this[pos] = "0"
    }
}

fun main() {
    전현수_전구와_스위치().solution()
}