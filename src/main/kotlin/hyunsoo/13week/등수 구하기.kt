package hyunsoo.`13week`


/**
 *
 * <문제>
 * [등수 구하기](https://www.acmicpc.net/problem/1205)
 *
 *
 */
fun main() {

    val (scoreCnt, newScore, canRankCnt) =
        readln().split(" ")
            .map { it.toInt() }

    if (scoreCnt == 0) {
        println(1)
        return
    }

    val newList = (listOf(newScore) + readln().split(" ").map { it.toInt() })
        .sortedByDescending { it }

    newList
        .lastIndexOf(newScore).apply {
            if (canRankCnt < this + 1) {
                println(-1)
            } else {
                newList.indexOf(newScore).apply {
                    println(this + 1)
                }
            }
        }


}