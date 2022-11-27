package byeonghee.`11week`

import java.io.*
import kotlin.math.min

class 소병희_꽃길 {

    companion object {
        val br = BufferedReader(InputStreamReader(System.`in`))

        var n = 0
        lateinit var nRange: IntRange
        lateinit var garden : List<List<Int>>
        lateinit var planted : List<MutableList<Int>>

        var answer = Int.MAX_VALUE

        fun solve() {
            n = br.readLine().toInt()
            garden = List(n) { br.readLine().split(" ").map { it.toInt() } }
            planted = List(n) { MutableList(n) { 0 } }
            nRange = 1 until n - 1

            dfs(0, 0)
            println(answer)
        }

        fun dfs(sum: Int, depth: Int) {
            if (depth == 3) {
                answer = min(answer, sum)
                return
            }

            for(r in nRange) for(c in nRange) {
                if (planted[r][c] == 0) {
                    plantFlower(r, c, 1)
                    dfs(sum + calcflowerCost(r, c), depth + 1)
                    plantFlower(r, c, -1)
                }
            }
        }

        fun plantFlower(r: Int, c: Int, value: Int) {
            if (r > 1) planted[r-2][c] += value
            if (r < n - 2) planted[r+2][c] += value
            if (c > 1) planted[r][c-2] += value
            if (c < n - 2) planted[r][c+2] += value
            for(i in r-1..r+1) for(j in c-1..c+1) {
                planted[i][j] += value
            }
        }

        fun calcflowerCost(r: Int, c: Int) : Int {
            return garden[r-1][c] + garden[r+1][c] + garden[r].subList(c-1, c+2).sumOf { it }
        }
    }
}

fun main() {
    소병희_꽃길.solve()
}