package hyunsoo.`21week`

import kotlin.math.max

/**
 *
 * <문제>
 * [스티커](https://www.acmicpc.net/problem/9465)
 *
 * - 아이디어
 * dp 문제 (0,0)칸부터 (1, n-1)칸까지 확인해보기?...
 * - 트러블 슈팅
 * 열이 1개일 때의 예외 처리
 */
class `전현수_스티커` {

    fun solution() {

        val testcaseCnt = readln().toInt()

        repeat(testcaseCnt) main@{

            val columnCnt = readln().toInt()
            val stickerInfo = mutableListOf<List<Int>>()

            repeat(2) {
                val columnStickerInfo = readln().split(" ").map { it.toInt() }
                stickerInfo.add(columnStickerInfo)
            }

            if (columnCnt == 1) {
                println(max(stickerInfo[0][0], stickerInfo[1][0]))
                return@main
            }

            val firstRowDp = IntArray(columnCnt).apply {
                this[0] = stickerInfo[0][0]
                this[1] = stickerInfo[0][1] + stickerInfo[1][0]
            }

            val secondRowDp = IntArray(columnCnt).apply {
                this[0] = stickerInfo[1][0]
                this[1] = stickerInfo[1][1] + stickerInfo[0][0]
            }


            for (index in 2 until columnCnt) {
                firstRowDp[index] =
                    stickerInfo[0][index] + max(secondRowDp[index - 1], secondRowDp[index - 2])

                secondRowDp[index] =
                    stickerInfo[1][index] + max(firstRowDp[index - 1], firstRowDp[index - 2])
            }

            println(max(firstRowDp[columnCnt - 1], secondRowDp[columnCnt - 1]))

        }
    }
}

fun main() {
    전현수_스티커().solution()
}