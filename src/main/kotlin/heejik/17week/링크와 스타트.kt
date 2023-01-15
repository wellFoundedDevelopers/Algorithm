package heejik.`17week`

import kotlin.math.absoluteValue
import kotlin.math.min

class `링크와 스타트` {

    var n = 0
    private val s = mutableListOf<List<Int>>()
    private lateinit var visited: MutableList<Boolean>
    var answer = Int.MAX_VALUE
    fun solve() {
        setting()
    }

    private fun setting() {
        repeat(readln().toInt().run {
            n = this
            this
        }) {
            s.add(readln().split(' ').map { it.toInt() })
        }

        visited = MutableList(n) { false }

        rec(0)

        println(answer)
    }

    private fun rec(cnt: Int) {
        if (cnt == n) {
            answer = min(answer, compare())
            return
        }

        visited[cnt] = true
        rec(cnt + 1)
        visited[cnt] = false
        rec(cnt + 1)
    }

    private fun compare(): Int {
        var startValue = 0
        var linkValue = 0
        (0 until n - 1).forEach { i ->
            (i + 1 until n).forEach { j ->
                if (visited[i] && visited[j]) {
                    startValue += (s[i][j] + s[j][i])
                }
                if (visited[i].not() && visited[j].not()) {
                    linkValue += (s[i][j] + s[j][i])
                }
            }
        }
        return (startValue - linkValue).absoluteValue
    }
}

fun main() {
    `링크와 스타트`().solve()
}