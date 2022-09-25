package byeonghee.`2week`

import java.io.BufferedReader
import java.io.InputStreamReader

var N = 0
var M = 0
var rect : MutableList<MutableList<Int>> = mutableListOf()
var maxDist = 1

fun calcMaxSize() : Int {
    for(d in maxDist downTo 1) {
        for (r in 0 until N - d) for (c in 0 until M - d) {
            if (rect[r][c] != rect[r][c + d]) continue
            if (rect[r][c] != rect[r + d][c]) continue
            if (rect[r][c] != rect[r + d][c + d]) continue

            return (d + 1) * (d + 1)
        }
    }
    return 1
}

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    this.readLine().trim().split(" ").map{ it.toInt() }.apply {
        N = this.first()
        M = this.last()
    }
    maxDist = (if (N < M) N else M) - 1

    repeat(N) { r ->
        rect.add(mutableListOf())
        this.readLine().trim().forEach {
            rect[r].add(it.code)
        }
    }

    println(calcMaxSize())
}