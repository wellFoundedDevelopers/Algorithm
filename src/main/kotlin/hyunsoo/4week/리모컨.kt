package hyunsoo.`4week`

import kotlin.math.absoluteValue
import kotlin.system.exitProcess

/**
 * 리모컨에는 버튼이 0부터 9까지 / +, - 가 있음
 * +를 누르면 현재 채널에서 +1, -를 누르면 -1 된 채널로 이동
 * 0에서 -를 누른 경우에는 채널이 변하지 않음.
 *
 * 입/출력
 * - 첫 번째 줄에 목표 채널
 * - 두 번째 줄에 고장난 버튼의 개수
 * - 세 번째 줄에 고장난 버튼
 *
 * 아이디어
 * - 초기의 최솟값은 목표 채널 - 100(기본 채널)
 * - 완전 탐색
 *      - 100 ~ 타겟 채널까지 +- 1씩 하기 => 초기값
 *      - 각 자릿수별 가장 가까운 숫자 배치 후 +- 1 =>
 *      - 아니면 DFS로 모든 경우를 탐색 후 차이구하기
 */

val targetChannel = readln().toInt()
val targetChannelLength = targetChannel.toString().length
val brokenButtonCnt = readln().toInt().apply {
    if (this == 0) {
        var minCnt = (targetChannel - 100).absoluteValue.let {
            if (it > targetChannelLength) {
                targetChannelLength
            } else it
        }
        if (minCnt > targetChannelLength) minCnt = targetChannelLength
        println(minCnt)
        exitProcess(0)
    } else if (this == 10) {
        println((targetChannel - 100).absoluteValue)
        exitProcess(0)
    }
}
val brokenButtonList = readln().split(" ").map { it.toInt() }
var minCnt = (targetChannel - 100).absoluteValue
val remoteControllerButtonList = (0..9).filter { brokenButtonList.contains(it).not() }
val pickedButtonList = mutableListOf<Int>()

fun main() {
    for (depth in 1..targetChannelLength + 1) {
        pickAllCases(0, depth)
    }
    println(minCnt)
}

fun pickAllCases(cnt: Int, depth: Int) {

    if (cnt == depth) {

        var clickCnt = pickedButtonList.size
        val madeNum = pickedButtonList.convert()
        clickCnt += (targetChannel - madeNum).absoluteValue
        if (clickCnt < minCnt) {
            minCnt = clickCnt
        }

        return
    }
    for (index in remoteControllerButtonList.indices) {

        pickedButtonList.add(remoteControllerButtonList[index])
        pickAllCases(cnt + 1, depth)
        pickedButtonList.removeAt(pickedButtonList.lastIndex)

    }
}

fun <T> List<T>.convert(): Int {
    val stringBuilder = StringBuilder()

    this.forEach {
        stringBuilder.append(it.toString())
    }

    return stringBuilder.toString().toInt()
}