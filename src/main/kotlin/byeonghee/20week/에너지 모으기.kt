package byeonghee.`20week`

import java.io.*

class 소병희_에너지모으기 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val n = readLine().toInt()
            val beads = readLine().split(" ").map { it.toInt() }
            var answer = 0

            for(i in 1 until n - 1) {
                answer = answer.coerceAtLeast(dfs(beads, i))
            }

            println(answer)
        }

        fun dfs(list: List<Int>, rm: Int) : Int {
            val preSum = list[rm - 1] * list[rm + 1]
            val newList = list.toMutableList().apply { removeAt(rm) }
            var ret = 0

            for(i in 1 until newList.lastIndex) {
                ret = ret.coerceAtLeast(dfs(newList, i))
            }

            return preSum + ret
        }
    }
}

fun main() {
    소병희_에너지모으기.solve()
}