package hyunsoo.`7week`

import kotlin.system.exitProcess

/**
 * <문제>
 * [한 줄로 서기](https://www.acmicpc.net/problem/1138)
 *
 * N명의 사람들은 매일 아침 한 줄로 서는데 오민식의 지시대로 섬.
 * 사람들은 자기보다 큰 사람이 왼쪽에 몇 명 있었는지만을 기억함.
 * N명의 사람이 있고 키는 1부터 N까지 모두 다름
 *
 * 입/출력
 * - 첫째 줄에 사람의 수 N이 주어짐.
 * - 둘째 줄에는 키가 1인 사람부터 차례대로 자기보다 키가 큰 사람이 왼쪽에 몇 명이 있었는지 주어짐.
 *
 * 아이디어
 * - 에잇 화나는데 dfs를 사용해보자~~
 */

val peopleCnt = readln().toInt()
val peopleList = (1..peopleCnt).toList()
val infoList = readln().split(" ").map { it.toInt() }
val tempLineData = mutableListOf<Int>()
val pickedCheck = BooleanArray(peopleCnt) { false }

fun main() {
    makeCorrectLine(0, peopleCnt)
}

fun makeCorrectLine(cnt: Int, depth: Int) {
    if (cnt == depth) {
        if (checkCondition()) {
            tempLineData.forEach {
                print("$it ")
            }
            exitProcess(0)
        }
        return
    }
    for (index in 0 until peopleCnt) {
        if (pickedCheck[index]) continue

        pickedCheck[index] = true
        tempLineData.add(peopleList[index])
        makeCorrectLine(cnt + 1, depth)
        tempLineData.removeAt(tempLineData.lastIndex)
        pickedCheck[index] = false

    }
}

fun checkCondition(): Boolean {

    infoList.forEachIndexed { index, tallerCnt ->

        val me = index + 1
        var tallerCnt = tallerCnt

        for (index in 0 until tempLineData.size) {
            val otherPerson = tempLineData[index]
            if (me == otherPerson) {
                break
            } else if (me < otherPerson) tallerCnt--
        }

        if ((tallerCnt == 0).not()) {
            return false
        }

    }

    return true

}