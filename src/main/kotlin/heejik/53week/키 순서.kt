package heejik.`53week`

import java.io.BufferedReader
import java.io.InputStreamReader

class `키 순서` {

    private var n = 0
    private var m = 0
    private lateinit var relation: List<BooleanArray>

    val br = BufferedReader(InputStreamReader(System.`in`))

    fun solve() {
        input()
        createRelation()
        getAnswer().also { answer ->
            println(answer)
        }
    }

    private fun input() {
        br.readLine().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }
        relation = List(size = n + 1) { BooleanArray(size = n + 1) }

        repeat(m) {
            val (small, large) = br.readLine().split(' ').map { it.toInt() }
            relation[small][large] = true
        }
    }

    private fun createRelation() {
        for (k in 1..n) {
            for (i in 1..n) {
                for (j in 1..n) {
                    if (relation[i][k] && relation[k][j])
                        relation[i][j] = true
                }
            }
        }
    }

    private fun getAnswer(): Int {
        var answer = 0
        for (i in 1..n) {
            var count = 0
            for (j in 1..n) {
                if (relation[i][j] || relation[j][i]) count++
            }
            if (count == n - 1) answer++
        }

        return answer
    }
}


fun main() {
    `키 순서`().solve()
}