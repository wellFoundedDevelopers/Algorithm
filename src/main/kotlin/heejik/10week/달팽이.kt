package heejik.`10week`

import java.io.BufferedReader
import java.io.InputStreamReader

class 달팽이 {

    var findPos = 0
    var answer = Pair(0, 0)
    fun solve() {
        val br = BufferedReader(InputStreamReader(System.`in`))

        val n = br.readLine().toInt()
        findPos = br.readLine().toInt()

        println(getSnail(n).joinToString("\n") { it.joinToString(" ") })
        answer.run {
            println("${first+1} ${second+1}")
        }
    }

    fun getSnail(n: Int): List<List<Int>> {
        // 위, 오른, 아래, 왼
        val dx = listOf(-1, 0, 1, 0)
        val dy = listOf(0, 1, 0, -1)

        val snail = MutableList(n) { MutableList(n) { 0 } }
        var num = 1
        var x = n / 2
        var y = n / 2
        if (num == findPos) answer = Pair(x,y)
        snail[x][y] = num++
        var tmp = 1
        tmp += 2

        while (true) {
            x += dx[0]  // 한칸 위
            y += dy[0]
            if (num == findPos) answer = Pair(x,y)
            snail[x][y] = num++

            for (i in 0 until tmp - 2) {    // 오른
                x += dx[1]
                y += dy[1]
                if (num == findPos) answer = Pair(x,y)
                snail[x][y] = num++
            }

            for (i in 0 until tmp - 1) {    // 아래
                x += dx[2]
                y += dy[2]
                if (num == findPos) answer = Pair(x,y)
                snail[x][y] = num++
            }

            for (i in 0 until tmp - 1) {    // 왼
                x += dx[3]
                y += dy[3]
                if (num == findPos) answer = Pair(x,y)
                snail[x][y] = num++
            }

            for (i in 0 until tmp - 1) {    // 위
                x += dx[0]
                y += dy[0]
                if (num == findPos) answer = Pair(x,y)
                snail[x][y] = num++
            }

            if (num -1 == n * n) {
                break
            }
            tmp += 2
        }
        return snail
    }

}


fun main() {
    달팽이().solve()

}