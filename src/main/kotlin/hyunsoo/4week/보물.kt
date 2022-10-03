package hyunsoo.`4week`

/**
 * 길이가 n인 정수 배열 a, b가 있는데
 * 함수 s = a[0] * b[0] + .. a[n-1] * b[n-1] 임
 * s의 값을 가장 자게 만들기 위하여 a의 수를 재배열 하자..!
 *
 * 아이디어
 * - n은 50보다 작거나 같고 a, b의 각 원소는 100보다 작거나 같은 음이 아닌 정수
 * - 범위가 작은데? 완탐
 *      - 순열을 사용해서 a를 재배열할 모든 경우의 수를 뽑고 탐색해보자.
 *      - 50!....
 * - A의 최대 * B의 최소??
 */

fun main() {

    val n = readln().toInt()
    val arrayA = readln().split(" ").map { it.toInt() }.toMutableList()
    val arrayB = readln().split(" ").map { it.toInt() }.toMutableList()
    var ans = 0

    repeat(n) {

        // A의 최소 * B의 최대
        val minOfA = arrayA.minOf { it }
        val maxOfB = arrayB.maxOf { it }

        arrayA[arrayA.indexOf(minOfA)] = 101
        arrayB[arrayB.indexOf(maxOfB)] = -1

        ans += minOfA * maxOfB

    }
    println(ans)
}

//val arraySize = readln().toInt()
//val hyunSooArrayA = readln().split(" ").map { it.toInt() }
//val hyunSooArrayB = readln().split(" ").map { it.toInt() }
//val tempPickedNumList = mutableListOf<Int>()
//val pickedCheckArray by lazy { BooleanArray(arraySize) { false } }
//var minOfS = Int.MAX_VALUE
//fun main() {
//
//    findCases(0, arraySize)
//    println(minOfS)
//
//}
//
//// a 배열을 재배치하는 모든 경우의 수를 구하는 순열
//fun findCases(cnt: Int, depth: Int) {
//
//    if (cnt == depth) {
//        var curSumOfS = 0
//        tempPickedNumList.forEachIndexed { index, elementOfArrayA ->
//
//            curSumOfS += elementOfArrayA * hyunSooArrayB[index]
//            if (curSumOfS < minOfS) minOfS = curSumOfS
//        }
//    }
//    for (index in hyunSooArrayA.indices) {
//        if (pickedCheckArray[index]) continue
//
//        pickedCheckArray[index] = true
//        tempPickedNumList.add(hyunSooArrayA[index])
//        findCases(cnt + 1, depth)
//        pickedCheckArray[index] = false
//        tempPickedNumList.removeAt(tempPickedNumList.lastIndex)
//
//    }
//}
//
//// 커스텀 깊은 복사 확장 함수
//fun <T> MutableList<T>.deepCopy(): MutableList<T> {
//    val newList = mutableListOf<T>()
//    this.forEach {
//        newList.add(it)
//    }
//    return newList
//}