package hyunsoo.`19week`

/**
 *
 * <문제>
 * [피보나치 함수](https://www.acmicpc.net/problem/1003)
 *
 * - 아이디어
 * 0이 호출된 횟수를 저장할 dp / 1이 호출된 횟수를 저장할 dp를 만들어줌.
 * - 트러블 슈팅
 *
 */
class `전현수_피보나치_함수` {

    private lateinit var dpZeroCnt: IntArray
    private lateinit var dpOneCnt: IntArray

    fun solution() {

        val testCnt = readln().toInt()
        val targetList = mutableListOf<Int>()

        repeat(testCnt) {
            targetList.add(readln().toInt())
        }

        val maxTarget = targetList.maxOf { it }

        dpZeroCnt = IntArray(maxTarget + 1)
        dpOneCnt = IntArray(maxTarget + 1)

        dpZeroCnt[0] = 1
        dpOneCnt[0] = 0

        if (1 <= maxTarget) {
            dpZeroCnt[1] = 0
            dpOneCnt[1] = 1
        }

        if (2 <= maxTarget) {
            dpZeroCnt[2] = dpZeroCnt[1] + dpZeroCnt[0]
            dpOneCnt[2] = dpOneCnt[1] + dpOneCnt[0]
        }

        for (num in 3..maxTarget) {
            dpZeroCnt[num] = dpZeroCnt[num - 1] + dpZeroCnt[num - 2]
            dpOneCnt[num] = dpOneCnt[num - 1] + dpOneCnt[num - 2]
        }

        targetList.forEach { target ->
            println("${dpZeroCnt[target]} ${dpOneCnt[target]}")
        }
    }
}

fun main() {
    전현수_피보나치_함수().solution()
}