package hyunsoo.`7week`

import kotlin.math.absoluteValue

/**
 * <문제>
 * [차이를 최대로](https://www.acmicpc.net/problem/10819)
 *
 * n개의 정수로 이루어진 배열 a가 존재하는데 주어진 식의 최대값을 구하기
 *
 */


val listSize = readln().toInt()
val numList = readln().split(" ").map { it.toInt() }
val selectedNumList = mutableListOf<Int>()
val checkSelected = BooleanArray(listSize) { false }
var ans = Int.MIN_VALUE
fun main() {
    permutation(0, listSize)
    println(ans)
}

fun permutation(cnt: Int, depth: Int) {
    if (cnt == depth) {

        val curAns = calculateExpression()
        if (curAns > ans) ans = curAns

        return
    }
    for (index in 0 until listSize) {
        if (checkSelected[index]) continue

        checkSelected[index] = true
        selectedNumList.add(numList[index])
        permutation(cnt + 1, depth)
        checkSelected[index] = false
        selectedNumList.removeAt(selectedNumList.lastIndex)
    }
}

fun calculateExpression(): Int {
    var sum = 0
    for (n in 0..listSize - 2) {
        sum += (selectedNumList[n] - selectedNumList[n + 1]).absoluteValue
    }
    return sum
}
