package hyunsoo.`45week`

/**
 *
 * <문제>
 * [연산자 끼워넣기](https://www.acmicpc.net/problem/14888)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_연산자_끼워넣기` {

    private var max = Int.MIN_VALUE
    private var min = Int.MAX_VALUE

    private val operatorList = mutableListOf<String>()
    private val pickedOperators = mutableListOf<String>()
    private lateinit var pickedChecker: BooleanArray

    private lateinit var numList: List<Int>

    fun solution() {

        val numCnt = readln().toInt()
        numList = readln().split(" ").map { it.toInt() }
        pickedChecker = BooleanArray(numCnt)

        readln().split(" ")
            .map { it.toInt() }
            .forEachIndexed { index, cnt ->
                when (index) {
                    0 -> repeat(cnt) { operatorList.add(PLUS) }
                    1 -> repeat(cnt) { operatorList.add(MINUS) }
                    2 -> repeat(cnt) { operatorList.add(TIMES) }
                    3 -> repeat(cnt) { operatorList.add(DIVIDED) }
                }
            }

        execute()

        println(max)
        println(min)

    }

    private fun execute() {
        if (pickedOperators.size == operatorList.size) {
            var currentResult = 0
            pickedOperators.forEachIndexed { operatorIndex, operator ->

                if (operatorIndex == 0) {
                    operator.whenOperator(
                        { currentResult += (numList.first() + numList[1]) },
                        { currentResult += (numList.first() - numList[1]) },
                        { currentResult += (numList.first() * numList[1]) },
                        { currentResult += (numList.first() / numList[1]) }
                    )
                    return@forEachIndexed
                }

                operator.whenOperator(
                    { currentResult += numList[operatorIndex + 1] },
                    { currentResult -= numList[operatorIndex + 1] },
                    { currentResult *= numList[operatorIndex + 1] },
                    { currentResult /= numList[operatorIndex + 1] },
                )
            }

            if (currentResult < min) min = currentResult
            if (max < currentResult) max = currentResult

            return
        }
        for (index in 0 until operatorList.size) {
            if (pickedChecker[index]) continue

            pickedOperators.add(operatorList[index])
            pickedChecker[index] = true
            execute()
            pickedOperators.remove(operatorList[index])
            pickedChecker[index] = false
        }
    }

    private fun String.whenOperator(
        whenPlus: () -> Unit = {},
        whenMinus: () -> Unit = {},
        whenTimes: () -> Unit = {},
        whenDivided: () -> Unit = {},
    ) {
        when (this) {
            PLUS -> whenPlus()
            MINUS -> whenMinus()
            TIMES -> whenTimes()
            DIVIDED -> whenDivided()
        }
    }

    companion object {
        const val PLUS = "+"
        const val MINUS = "-"
        const val TIMES = "*"
        const val DIVIDED = "/"
    }
}

fun main() {
    전현수_연산자_끼워넣기().solution()
}