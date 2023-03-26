package byeonghee.week28

import java.io.*

class 소병희_친구비 {

    companion object {
        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {

            val (n, m, k) = readLine().split(" ").map { it.toInt() }
            val cost = IntArray(n + 1)
            val parent = IntArray(n + 1) { it }
            var answer = 0

            fun getParent(_x: Int): Int {
                var x = _x
                while(parent[x] != x) {
                    x = parent[x]
                }
                return x
            }

            var idx = 1
            readLine().split(" ").forEach {
                cost[idx++] = it.toInt()
            }

            repeat(m) {
                val (p1, p2) = readLine().split(" ").map { getParent(it.toInt()) }
                if (p1 != p2) {
                    parent[p2] = p1
                    cost[p1] = cost[p1].coerceAtMost(cost[p2])
                }
            }

            for(i in 1 .. n) {
                if (parent[i] == i) answer += cost[i]
                if (answer > k) {
                    print("Oh no")
                    kotlin.system.exitProcess(0)
                }
            }
            print(answer)
        }
    }
}

fun main() {
    소병희_친구비.solve()
}