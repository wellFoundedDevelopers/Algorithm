/**
 * <문제>
 * [톱니바퀴](https://www.acmicpc.net/problem/14891)
 *
 * 톱니바퀴는 8개의 톱니를 가지고 있고, 4개가 주어짐
 * - 회전에 대한 정보가 주어지면
 *   - 1 ~ 4번의 톱니 상태를 모두 확인하기
 *   - 지정된 톱니부터 하나씩 차례로 회전 시키기
 *
 *
 *
 *
 * 입/출력
 * - 1 ~ 4째 줄에 톱니바퀴의 상태가 주어짐
 *   - 상태는 8개의 정수로 주어지고 12시방향부터 시계방향임.
 *   - N극은 0, S극은 1
 * - 5째 줄에는 회전 횟수가 주어짐.
 * - 그 후 K개의 줄에는 회전시킨 방법이 주어짐.
 *   - 첫 번째 정수는 회전시킨 톱니바퀴의 번호, 두 번째 정수는 방향
 *   - 방향이 1이면 시계 방향, -1이면 반시계 방향
 */

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
        if (dir == 1) rotateClockWise()
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


val gearList = Array(4) {
    Gear(readln())
}

val affectedInfo = Array(4) {
    mutableListOf<Int>()
}

fun main() {

    val gear1 = gearList.first()
    val gear2 = gearList.second()
    val gear3 = gearList.third()
    val gear4 = gearList.fourth()

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
            affectedInfo[0].add(1)
            affectedInfo[1].add(0)
        }
        if (secondRight != thirdLeft) {
            affectedInfo[1].add(2)
            affectedInfo[2].add(1)
        }
        if (thirdRight != fourthLeft) {
            affectedInfo[2].add(3)
            affectedInfo[3].add(2)
        }

        // 지정된 기어 회전
        rotateGear(gearNum - 1, dir)

        affectedInfo.clear()
    }

    var score = 0
    repeat(4) { cnt ->
        score += gearList[cnt].getTopState() * twoToPowOfExponent(cnt)
    }

    println(score)

}

fun rotateGear(gearNum: Int, dir: Int) {
    val targetGear = gearList[gearNum]
    targetGear.rotate(dir)
    val changedDir = dir.dirChange()
    val relatedGearList = mutableListOf<Int>()

    affectedInfo[gearNum].forEach { relatedGearNum ->
        relatedGearList.add(relatedGearNum)
    }

    relatedGearList.forEach { relatedGearNum ->
        affectedInfo[gearNum].remove(relatedGearNum)
        affectedInfo[relatedGearNum].remove(gearNum)
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
fun twoToPowOfExponent(exponent: Int): Int {
    var powered = 1
    if (exponent == 0) return 1
    repeat(exponent) {
        powered *= 2
    }
    return powered
}

// 방향 바꿔주기
fun Int.dirChange() = if (this == -1) 1 else -1

fun <T> Array<T>.second(): T = this[1]
fun <T> Array<T>.third(): T = this[2]
fun <T> Array<T>.fourth(): T = this[3]