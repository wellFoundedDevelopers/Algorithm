package hyunsoo.`11week`

/**
 * <문제>
 * [예산](https://www.acmicpc.net/problem/2512)
 *
 * 정해진 총액 이하에서 가능한 한 최대의 총 예산을 아래와 같은 방법으로 배정
 * 1. 모든 요청이 배정될 수 있으면 요청한 금액을 그대로 배정
 * 2. 모든 요청이 배정될 수 없으면, 특정한 정수 상한액을 계산하여 그 이상인 예산요청에는
 * 모두 상한액을 배정함. 상한액 이하의 예산 요청에 대해서는 요청한 금액을 그대로 배정
 */

val cityCnt = readln().toInt()
val sortedBudgetRequestList = readln().split(" ")
    .map { it.toInt() }
    .sortedBy { it }
val sumOfBudgetRequest = sortedBudgetRequestList.sum()
val totalBudget = readln().toInt()

fun main() {

    if (sumOfBudgetRequest <= totalBudget) {
        println(sortedBudgetRequestList.last())
    } else {

        var start = 1
        var end = sortedBudgetRequestList.last()
        var result = start

        while (start <= end) {

            val mid = (start + end) / 2
            if (canAssign(mid)) {
                start = mid + 1
                result = mid
            } else {
                end = mid - 1
            }
        }

        println(result)
    }

}

fun canAssign(maxAssignBudget: Int): Boolean {

    var canAssignBudget = 0

    sortedBudgetRequestList.forEach { requestBudget ->
        canAssignBudget +=
            if (requestBudget > maxAssignBudget) maxAssignBudget
            else requestBudget
    }
    return canAssignBudget <= totalBudget
}