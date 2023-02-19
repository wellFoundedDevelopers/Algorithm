package hyunsoo.`23week`

/**
 *
 * <문제>
 * [센서](https://www.acmicpc.net/problem/2212)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_센서` {

    fun solution() {

        val censorCnt = readln().toInt()
        val targetCnt = readln().toInt()

        val censorList =
            readln()
                .split(" ")
                .map { it.toInt() }
                .toMutableList()
                .sorted()

        val diffArray = mutableListOf<Int>()

        for (index in 0 until censorList.lastIndex) {
            val diffOfCensor = censorList[index + 1] - censorList[index]
            diffArray.add(diffOfCensor)
        }

        println(diffArray.sortedByDescending { it }.drop(targetCnt - 1).sum())
    }
}

fun main() {
    전현수_센서().solution()
}