package hyunsoo.`25week`

/**
 *
 * <문제>
 * [톱니바퀴](https://www.acmicpc.net/problem/14891)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_톱니바퀴` {

    data class RotateInfo(val gearNum: Int, val dir: Int)

    class Gear(stateData: String) {

        private val state = IntArray(8)

        init {
            stateData.forEachIndexed { index, charNum ->
                state[index] = charNum.digitToInt()
            }
        }

        // 시계 방향으로 회전
        private fun rotateClockWise() {
            val tempState = state.copyOf(state.size)
            state[0] = state[state.lastIndex]
            tempState.dropLast(1).forEachIndexed { index, num ->
                state[index + 1] = num
            }
        }

        // 반시계 방향으로 회전
        private fun rotateCounterClockWise() {
            val tempState = state.copyOf(state.size)
            state[state.lastIndex] = state[0]
            tempState.drop(1).forEachIndexed { index, num ->
                state[index] = num
            }
        }

        fun rotate(dir: Int) {
            if (dir == CLOCK_WISE) rotateClockWise()
            else rotateCounterClockWise()
        }

        private fun getLeftPole() = state[6]

        private fun getRightPole() = state[2]

        fun getPole() = listOf(
            getLeftPole(),
            getRightPole()
        )

        fun getTopState() = state.first()

        override fun toString(): String {
            return this.state.joinToString("")
        }
    }


    private val gearList = Array(4) {
        Gear(readln())
    }

    private val relatedInfo = Array(4) {
        mutableListOf<Int>()
    }

    fun solution() {

        val (gear1, gear2, gear3, gear4) = gearList

        val rotateCnt = readln().toInt()

        val rotateInfoList = Array(rotateCnt) {
            val (gearNum, dir) = readln().split(" ").map { it.toInt() }
            RotateInfo(gearNum, dir)
        }

        rotateInfoList.forEach { rotateInfo ->

            val (gearNum, dir) = rotateInfo

            val (firstLeft, firstRight) = gear1.getPole()
            val (secondLeft, secondRight) = gear2.getPole()
            val (thirdLeft, thirdRight) = gear3.getPole()
            val (fourthLeft, fourthRight) = gear4.getPole()

            // 회전에 영향을 받을 기어 판정
            if (firstRight != secondLeft) {
                relatedInfo[0].add(1)
                relatedInfo[1].add(0)
            }
            if (secondRight != thirdLeft) {
                relatedInfo[1].add(2)
                relatedInfo[2].add(1)
            }
            if (thirdRight != fourthLeft) {
                relatedInfo[2].add(3)
                relatedInfo[3].add(2)
            }

            // 지정된 기어 회전
            rotateGear(gearNum - 1, dir)

            relatedInfo.clear()
        }

        var score = 0
        repeat(4) { cnt ->
            score += gearList[cnt].getTopState() * twoToPowOfExponent(cnt)
        }

        println(score)
    }

    private fun rotateGear(gearNum: Int, dir: Int) {

        val targetGear = gearList[gearNum]

        targetGear.rotate(dir)
        val changedDir = dir.changed()
        val relatedGearList = mutableListOf<Int>()

        relatedInfo[gearNum].forEach { relatedGearNum ->
            relatedGearList.add(relatedGearNum)
        }

        relatedGearList.forEach { relatedGearNum ->
            relatedInfo[gearNum].remove(relatedGearNum)
            relatedInfo[relatedGearNum].remove(gearNum)
        }

        relatedGearList.forEach {
            rotateGear(it, changedDir)
        }
    }

    // 2차원 배열 안의 리스트들을 초기화
    fun <T> Array<MutableList<T>>.clear() {
        this.forEach {
            it.clear()
        }
    }

    // 2의 거듭제곱
    private fun twoToPowOfExponent(exponent: Int): Int {
        var powered = 1
        if (exponent == 0) return 1
        repeat(exponent) {
            powered *= 2
        }
        return powered
    }

    // 방향 바꿔주기
    private fun Int.changed() = if (this == COUNTER_CLOCK_WISE) CLOCK_WISE else COUNTER_CLOCK_WISE

    companion object {
        private const val CLOCK_WISE = 1
        private const val COUNTER_CLOCK_WISE = -1
    }
}

fun main() {
    전현수_톱니바퀴().solution()
}