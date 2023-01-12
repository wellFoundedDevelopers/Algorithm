package hyunsoo.`18week`

/**
 *
 * <문제>
 * [부분수열의 합](https://www.acmicpc.net/problem/1182)
 *
 * n개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분 수열 중에서 원소의 합이 s가 되도록 경우의 수를 구해라
 *
 * - 아이디어
 * 조합~
 *
 * - 트러블 슈팅
 * S가 양수일 경우만 생각해서 백트래킹을 했었음...
 */

var intCnt = 0
var target = 0
var ans = 0
val numList = mutableListOf<Int>()
val pickedNumList = mutableListOf<Int>()

fun main() {

    readln().split(" ").map { it.toInt() }.apply {
        intCnt = this.first()
        target = this[1]
    }

    numList.addAll(readln().split(" ").map { it.toInt() })

    for (pickCnt in 1..intCnt) {
        makeSubSequence(0, pickCnt, 0)
    }

    println(ans)
}

fun makeSubSequence(cnt: Int, depth: Int, startIndex: Int) {
    if (cnt == depth) {
        if (pickedNumList.sum() == target) ans++
    }
    for (index in startIndex until intCnt) {
        pickedNumList.add(numList[index])
        makeSubSequence(cnt + 1, depth, index + 1)
        pickedNumList.removeLast()
    }
}