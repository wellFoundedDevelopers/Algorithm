package byeonghee.week42

import java.io.BufferedReader
import java.io.InputStreamReader

class 소병희_바이러스 {

    companion object {
        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val v = readLine().toInt()
            val e = readLine().toInt()
            val parent = IntArray(v + 1) { it }

            fun connect(a: Int, b: Int) {
                if (parent[a] == parent[b]) return

                val newParent = if (parent[a] < parent[b]) parent[a] else parent[b]
                val notParent = if (parent[a] < parent[b]) parent[b] else parent[a]

                for(i in 1 .. v) {
                    if (parent[i] == notParent)
                        parent[i] = newParent
                }
            }

            repeat(e) {
                readLine().split(" ").let {
                    val a = it[0].toInt()
                    val b = it[1].toInt()

                    connect(a, b)
                }
            }

            print(parent.count { it == 1 } - 1)
        }
    }
}

fun main() {
    소병희_바이러스.solve()
}