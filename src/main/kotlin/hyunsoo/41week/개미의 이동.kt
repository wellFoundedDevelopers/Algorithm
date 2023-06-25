package hyunsoo.`41week`

import kotlin.math.absoluteValue

/**
 *
 * <문제>
 * [개미의 이동](https://www.acmicpc.net/problem/3221)
 *
 * - 아이디어
 *
 * 개미는 줄 끝이나 다른 개미를 만나면 즉시 방향을 바꿈
 *
 *
 * - 트러블 슈팅
 *
 */
class `전현수_개미의_이동` {

    private data class AntInfo(
        val position: Int,
        val direction: String,
    )

    fun solution() {

        val (length, time) = readln().split(" ").map { it.toInt() }
        val antCnt = readln().toInt()

        val ants = mutableListOf<AntInfo>()

        repeat(antCnt) {

            val antInfo = readln()
                .split(" ")
                .run { AntInfo(first().toInt(), last()) }
            ants.add(antInfo)

        }

        val finalAntInfoList = mutableListOf<Int>()

        ants.forEach { antInfo ->

            val (position, direction) = antInfo
            val moveCnt = time % (length * 2)

            when (direction) {
                LEFT -> {

                    val new = when {
                        moveCnt <= position -> {
                            position - moveCnt
                        }
                        // 한 번 팅길 경우
                        moveCnt in position + 1..position + length -> {
                            moveCnt - position
                        }
                        // 두 번 팅길 경우
                        else -> {
                            length - (moveCnt - (position + length))
                        }
                    }

                    finalAntInfoList.add(new)
                }

                RIGHT -> {

                    val new = when (val distance = position + moveCnt) {
                        in 0..length -> {
                            distance
                        }
                        // 한 번 팅길 경우
                        in length + 1 until length * 2 -> {
                            length - (moveCnt - (length - position))
                        }
                        // 두 번 팅길 경우
                        else -> {
                            moveCnt - (length - position) - length
                        }
                    }

                    finalAntInfoList.add(new)
                }
            }
        }

        finalAntInfoList.sorted().apply {
            println(this.joinToString(" "))
        }


    }

    companion object {
        private const val LEFT = "L"
        private const val RIGHT = "D"
    }
}

fun main() {
    전현수_개미의_이동().solution()
}