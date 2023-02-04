package hyunsoo.`21week`

/**
 *
 * <문제>
 * [부등호](https://www.acmicpc.net/problem/2529)
 *
 * - 아이디어
 * 모든 순열을 구해서 부등호에 따라 비교해보자..!!
 *
 * - 트러블 슈팅
 * 9글자일 경우 Int 범위를 초과해서 Long으로 범위 변경
 */
class `전현수_부등호` {

    private val numPickedCheck = BooleanArray(10)
    private val pickedNum = mutableListOf<Int>()
    private lateinit var inequalitySignList: List<String>

    private var minInt = "${Long.MAX_VALUE}"
    private var maxInt = "${-1}"

    private val inequalitySignCnt = readln().toInt()

    fun solution() {

        inequalitySignList = readln().split(" ")

        // 순열들 찾기
        findPermutation(0, inequalitySignCnt + 1)

        println(maxInt)
        println(minInt)

    }

    private fun findPermutation(cnt: Int, depth: Int) {

        if (cnt == depth) {

            if (isValidExpression()) {

                val pickedNumString: String = pickedNum.joinToString("")
                val curInt = pickedNumString.toLong()

                if (curInt < minInt.toLong()) minInt = pickedNumString
                if (maxInt.toLong() < curInt) maxInt = pickedNumString
            }

            return
        }

        for (num in 0..9) {

            if (numPickedCheck[num]) continue

            numPickedCheck[num] = true
            pickedNum.add(num)
            findPermutation(cnt + 1, depth)
            numPickedCheck[num] = false
            pickedNum.removeLast()
        }

    }

    private fun isValidExpression(): Boolean {
        for (index in 0 until inequalitySignCnt) {

            val sign = inequalitySignList[index]
            val leftValue = pickedNum[index]
            val rightValue = pickedNum[index + 1]

            // < 부등호 일 때
            if (sign == LESS_THAN) {
                // 오른쪽 보다 왼쪽 값이 크다면 invalid
                if (rightValue < leftValue) return false
            } else {
                if (leftValue < rightValue) return false
            }
        }
        return true
    }

    companion object {
        private const val LESS_THAN = "<"
        private const val GREATER_THAN = ">"
    }

}

fun main() {
    전현수_부등호().solution()
}