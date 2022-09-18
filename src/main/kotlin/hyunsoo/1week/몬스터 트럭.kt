package hyunsoo.`1week`

/**
 * 입/출력 관련
 * - 지도는 R행 C열
 * - `#`은 빌딩 `X`는 주차된 차, `.`은 빈 주차공간
 * - 혜빈이의 차는 2행 2열의 칸을 차지해야함.
 *
 * 요구사항
 * - 가능한 주차 공간을 해빈이가 부숴야하는 차의 수대로 모아서 보여주자.
 * - 주차하기 위해 부숴야하는 차만 고려.(주차하러 가는 길에 부수는 차는 신경 X)
 * - 빌딩이 있는 곳은 주차를 할 수 없음.
 *
 * 아이디어
 * - 간단한 브루트 포스인듯.
 * - 모든 행,열을 2x2로 조사하여 빌딩이 있다면 넘어가고
 *   그렇지 않다면 주차하는 경우를 부숴야하는 수의 경우에 따라 판단.
 */

var dontNeedToBreak = 0
var needToBreakOne = 0
var needToBreakTwo = 0
var needToBreakThree = 0
var needToBreakFour = 0

fun main() {

    val (row, col) = readln().split(" ").map { it.toInt() }
    val parkingLot = mutableListOf<String>()

    repeat(row) {
        val c = readln()
        parkingLot.add(c)
    }

    for (r in 0..row - 2) {
        for (c in 0..col - 2) {
            val curPos = parkingLot[r][c]
            val rightPos = parkingLot[r + 1][c]
            val downPos = parkingLot[r][c + 1]
            val rightAndDownPos = parkingLot[r + 1][c + 1]
            checkParkingLot(curPos, rightPos, downPos, rightAndDownPos)
        }
    }

    println(dontNeedToBreak)
    println(needToBreakOne)
    println(needToBreakTwo)
    println(needToBreakThree)
    println(needToBreakFour)

}

fun checkParkingLot(vararg posList: Char) {

    posList.find { it == '#' }?.let { return }

    when (posList.count { it == 'X' }) {
        0 -> dontNeedToBreak++
        1 -> needToBreakOne++
        2 -> needToBreakTwo++
        3 -> needToBreakThree++
        4 -> needToBreakFour++
    }
}