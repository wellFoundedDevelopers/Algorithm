package heejik.`53week`

import kotlin.math.max

class 서강그라운드 {

    fun solve() {
        val (n, m, r) = readln().split(' ').map { it.toInt() }
        val itemCounts = readln().split(' ').map { it.toInt() }.toMutableList()
        itemCounts.add(0, 0)
        val distances = List(size = n + 1) { MutableList(size = n + 1) { 16 } }
        var answer = 0

        repeat(r) {
            val (a, b, distance) = readln().split(' ').map { it.toInt() }
            distances[a][b] = distance
            distances[b][a] = distance
        }

        for (k in 1..n) {
            for (i in 1..n) {
                for (j in 1..n) {
                    if (distances[i][j] > distances[i][k] + distances[k][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j]
                    }
                }
            }
        }

        for (region in 1..n) {
            var sum = itemCounts[region]
            for (other in 1..n) {
                if (region == other) continue
                if (distances[region][other] <= m) sum += itemCounts[other]
            }
            answer = max(answer, sum)
        }

        println(answer)
    }
}


fun main() {
    서강그라운드().solve()
}