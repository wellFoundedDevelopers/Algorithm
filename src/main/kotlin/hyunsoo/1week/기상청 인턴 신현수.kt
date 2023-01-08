package hyunsoo.`1week`

/**
 * 단순히 수열을 입력받은 후 연속된 k일의 온도의 합이 최대가 되는 값을 출력하기
 * 완탐을 하자
 */

fun main() {

    val (n, k) = readln().trim().split(" ").map { it.toInt() }
    val numList = readln().trim().split(" ").map { it.toInt() }
    var maxSum = -100000

    for (index in 0 .. n - k) {
        val curSum = numList.subList(index, index + k).sum()
        if (curSum > maxSum) maxSum = curSum
    }

    println(maxSum)

}