package byeonghee.`22week`

import java.io.*

class 소병희_ABCDE {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val friends = Array(n) { mutableListOf<Int>() }
            var answer = 0

            repeat(m) {
                readLine().split(" ").map { it.toInt() }.let {
                    friends[it[0]].add(it[1])
                    friends[it[1]].add(it[0])
                }
            }

            fun dfs(added: List<Int>) {
                if (added.size == 5) {
                    answer = 1
                    return
                }

                for(friend in friends[added.last()]) {
                    if (friend in added) continue

                    dfs(added.plus(friend))
                    if (answer == 1) return
                }
            }

            for(i in 0 until n) {
                dfs(listOf(i))
                if (answer == 1) break
            }

            println(answer)
        }
    }
}

fun main() {
    소병희_ABCDE.solve()
}