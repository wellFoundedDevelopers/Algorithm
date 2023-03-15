package byeonghee.week27

import java.io.*

class 소병희_친구네트워크 {

    companion object {
        lateinit var parent : IntArray

        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val sb = StringBuilder()

            repeat(readLine().toInt()) {
                val f = readLine().toInt()
                var id = 1
                val ids = HashMap<String, Int>(2 * f + 1)
                val sizes = IntArray(2 * f + 1) { 1 }
                parent = IntArray(2 * f + 1) { it }

                for(r in 0 until f) {
                    val (par1, par2) = readLine().split(" ")
                        .map { getParent(ids.getOrPut(it) { id++ }) }

                    if (par1 < par2) {
                        parent[par2] = par1
                        sizes[par1] += sizes[par2]
                        sb.appendLine(sizes[par1])
                    }
                    else if (par2 < par1) {
                        parent[par1] = par2
                        sizes[par2] += sizes[par1]
                        sb.appendLine(sizes[par2])
                    }
                    else sb.appendLine(sizes[par1])
                }
            }
            println(sb)
        }

        fun getParent(i: Int) : Int {
            return if (i == parent[i]) i else getParent(parent[i])
        }
    }
}

fun main() {
    소병희_친구네트워크.solve()
}