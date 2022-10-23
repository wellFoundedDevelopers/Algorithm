package hyunsoo.`6week`


/**
 * <문제>
 * [근손실](https://www.acmicpc.net/problem/18429)
 *
 * 하루가 지나면 중량이 K만큼 감소
 * N개의 서로 다른 운동 키트를 가지고 있음.
 * 하루에 1개씩 키트를 사용. 사용하면 중량이 즉시 증가
 * 운동 키트를 사용하면서 항상 중량이 500이상이 되도록 해야함.
 *
 * 아이디어
 * - 완탐해서 모든 조합을 구하고
 * - 각 조합들로 운동할 경우 500이상이 되는지를 판정하자!
 *
 */

val exerciseKitList = mutableListOf<Int>()
var possibleCaseCnt = 0
val exerciseCase = mutableListOf<Int>()
val visited = BooleanArray(8) { false }

fun main() {

    val (exerciseCnt, downWeight) = readln().split(" ").map { it.toInt() }
    val exerciseData = readln().split(" ").map { it.toInt() }
    exerciseKitList.addAll(exerciseData)

    checkAllCases(0, exerciseCnt, downWeight)

    println(possibleCaseCnt)
}

fun checkAllCases(cnt: Int, depth: Int, downWeight: Int) {

    if (cnt == depth) {

        var curWeight = 500
        exerciseCase.forEach { upWeight ->
            curWeight += upWeight - downWeight
            if (curWeight < 500) return
        }
        possibleCaseCnt++
        return
    }
    for (index in 0 until exerciseKitList.size) {
        if (visited[index]) continue

        visited[index] = true
        exerciseCase.add(exerciseKitList[index])
        checkAllCases(cnt + 1, depth, downWeight)
        visited[index] = false
        exerciseCase.removeAt(exerciseCase.lastIndex)
    }
}