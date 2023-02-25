package hyunsoo.`24week`

import kotlin.system.exitProcess

/**
 *
 * <문제>
 * [좋은수열](https://www.acmicpc.net/problem/2661)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_좋은수열` {

    private val sequenceLength = readln().toInt()

    private var niceSequence = "1"

    fun solution() {
        makeNice()
    }

    private fun makeNice() {
        if (niceSequence.length == sequenceLength) {
            println(niceSequence)
            exitProcess(0)
        }
        for (i in 1..3) {

            val preNiceSequence = niceSequence
            val nextSequence = niceSequence + i

            if (isNice(nextSequence)) {
                niceSequence = nextSequence
                makeNice()
            }
            if (1 in listOf<Int>())
            niceSequence = preNiceSequence
        }
    }

    private fun isNice(target: String): Boolean {
        for (range in 1..(target.length / 2)) {
            if (target.substring(
                            target.length - range,
                            target.length
                    )
                    == target.substring(
                            target.length - range * 2,
                            target.length - range
                    )
            ) return false
        }
        return true
    }
}

fun main() {
    전현수_좋은수열().solution()
}