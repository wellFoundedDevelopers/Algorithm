package hyunsoo.`34week`

/**
 *
 * <문제>
 * [행복 유치원](https://www.acmicpc.net/problem/13164)
 *
 * - 아이디어
 *
 * !! 혼자 있으면 돈이 안들어 !!
 * - 트러블 슈팅
 *
 * [참고](https://velog.io/@ich0906/%EB%B0%B1%EC%A4%80-13164-%ED%96%89%EB%B3%B5-%EC%9C%A0%EC%B9%98%EC%9B%90)
 *
 */
class `전현수_행복_유치원` {

    fun solution() {
        val (childCnt, teamCnt) = readln().split(" ").map { it.toInt() }
        val children = readln().split(" ").map { it.toInt() }

        val heightDiffList = mutableListOf<Int>()
        for (i in 0 until children.lastIndex) {
            heightDiffList.add(children[i + 1] - children[i])
        }

        println(heightDiffList.sortedByDescending { it }.drop(teamCnt - 1).sum())
    }
}

fun main() {
    전현수_행복_유치원().solution()
}