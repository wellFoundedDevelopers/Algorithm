package hyunsoo.`43week`

/**
 *
 * <문제>
 * [회전 초밥](https://www.acmicpc.net/problem/2531)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_회전 초밥` {

    fun solution() {

        val (dishCnt, sushiCnt, sequence, coupon) = readln().split(" ").map { it.toInt() }
        val sushiBelt = IntArray(dishCnt)

        repeat(dishCnt) { index ->
            sushiBelt[index] = readln().toInt()
        }

        val currentSushiList = mutableSetOf(coupon)
        var max = 0

        for (startSushiIndex in 0 until dishCnt) {

            repeat(sequence) { nextIndex ->
                val nextSushiIndex = (startSushiIndex + nextIndex) % dishCnt
                currentSushiList.add(sushiBelt[nextSushiIndex])
            }

            if (max < currentSushiList.size) max = currentSushiList.size

            currentSushiList.clear()
            currentSushiList.add(coupon)
        }

        println(max)
    }
}

fun main() {
    `전현수_회전 초밥`().solution()
}