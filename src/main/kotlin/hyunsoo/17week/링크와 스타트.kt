package hyunsoo.`17week`

import kotlin.math.absoluteValue

/**
 *
 * <문제>
 * [링크와 스타트](https://www.acmicpc.net/problem/15661)
 *
 * - 아이디어
 * 사람의 인원수를 BooleanArray로 두고,
 * true라면 스타트 팀 false라면 링크팀
 *
 * - 트러블 슈팅
 *
 */

val peopleCnt = readln().toInt()

val powerInfo = Array(peopleCnt) {
    readln().split(" ").map { it.toInt() }
}

val pickedByStart = BooleanArray(peopleCnt)

var minDiffPower = Int.MAX_VALUE

fun main() {
    for (startTeamCnt in 1 until peopleCnt) {
        checkStartTeamCases(0, startTeamCnt, 0)
    }
    println(minDiffPower)
}

fun checkStartTeamCases(cnt: Int, depth: Int, startWith: Int) {
    if (cnt == depth) {

        val startTeamList = mutableListOf<Int>()
        val linkTeamList = mutableListOf<Int>()

        pickedByStart.forEachIndexed { index, isStartTeam ->
            if (isStartTeam) startTeamList.add(index) else linkTeamList.add(index)
        }

        val diffOfPower = (calculatePower(startTeamList) - calculatePower(linkTeamList)).absoluteValue
        if (diffOfPower < minDiffPower) minDiffPower = diffOfPower

        return
    }
    for (index in startWith until peopleCnt) {

        if (pickedByStart[index]) continue

        pickedByStart[index] = true
        checkStartTeamCases(cnt + 1, depth, index + 1)
        pickedByStart[index] = false
    }
}


fun calculatePower(teamList: List<Int>): Int {
    var power = 0
    for (i in teamList.indices) {
        for (j in i + 1 until teamList.size) {
            val x = teamList[i]
            val y = teamList[j]
            power += powerInfo[x][y]
            power += powerInfo[y][x]
        }
    }
    return power
}
