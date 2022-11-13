package byeonghee.`9week`

import java.io.*

class 소병희_배열복원하기 {
    companion object {
        val br = BufferedReader(InputStreamReader(System.`in`))

        lateinit var matrixA : Array<IntArray>
        lateinit var matrixB : List<List<Int>>

        fun solve() {
            val (h, w, x, y) = br.readLine().split(" ").map { it.toInt() }
            matrixB = List(h + x) { br.readLine().split(" ").map { it.toInt() } }
            matrixA = matrixB.subList(0, h).map { it.subList(0, w).toIntArray() }.toTypedArray()

            for (r in x until h) for(c in y until w) {
                matrixA[r][c] = matrixB[r][c] - matrixA[r - x][c - y]
            }

            println(matrixA.joinToString("\n") { it.joinToString(" ")})
        }
    }
}

fun main() {
    소병희_배열복원하기.solve()
}