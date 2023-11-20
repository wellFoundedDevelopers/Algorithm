package hyunsoo.`50week`

/**
 *
 * <문제>
 * [숫자고르기](https://www.acmicpc.net/problem/2668)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_숫자고르기` {

    private lateinit var numberList: IntArray
    private lateinit var linkInfoList: Array<MutableList<Int>>

    private var ansList = mutableSetOf<Int>()

    fun solution() {

        val cnt = readln().toInt()

        numberList = IntArray(cnt + 1)
        linkInfoList = Array(cnt + 1) {
            mutableListOf()
        }

        repeat(cnt) {
            val num = readln().toInt()
            numberList[it + 1] = num
        }

        numberList.drop(1)
            .forEachIndexed { unCompletedIndex, value ->
                linkInfoList[value].add(unCompletedIndex + 1)
            }

        for (i in 1..cnt) {
            dfs(i, i, BooleanArray(cnt + 1))
        }

        println(ansList.size)
        ansList.sortedBy { it }.forEach {
            println(it)
        }

    }

    fun dfs(target: Int, current: Int, visited: BooleanArray) {

        if (visited[target] && target == current) {
            ansList.add(target)
            return
        }

        if (visited[current]) return

        visited[current] = true
        linkInfoList[current].forEach {
            dfs(target, it, visited)
        }
    }

}

fun main() {
    전현수_숫자고르기().solution()
}