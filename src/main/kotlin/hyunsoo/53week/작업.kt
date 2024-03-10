package hyunsoo.`53week`

/**
 *
 * <문제>
 * [작업](https://www.acmicpc.net/problem/21937)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_작업` {

    private lateinit var taskSequence: Array<MutableList<Int>>
    private lateinit var checkedList: IntArray

    fun solution() {


        val (taskCnt, taskInfoCnt) = readln()
            .split(" ")
            .map { it.toInt() }

        checkedList = IntArray(taskCnt + 1)

        taskSequence = Array(taskCnt + 1) {
            mutableListOf()
        }

        repeat(taskInfoCnt) {
            val (fir, sec) = readln()
                .split(" ")
                .map { it.toInt() }

            taskSequence[sec].add(fir)
        }

        val target = readln().toInt()

        dfs(target)

        println(checkedList.count { it == 1 } - 1)

    }

    private fun dfs(target: Int) {

        checkedList[target] = 1

        taskSequence[target].map { next ->
            if (checkedList[next] != 1) dfs(next)
        }

    }

}

fun main() {
    전현수_작업().solution()
}