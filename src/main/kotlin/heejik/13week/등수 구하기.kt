package heejik.`13week`

class `등수 구하기` {

    fun solve() {
        val (n, score, p) = readln().split(' ').map { it.toInt() }

        if (n == 0) {
            println(1)
            return
        }
        val scores = readln().split(' ').map { it.toInt() }.toMutableList()

        if (scores.count { it >= score } >= p) {
            println(-1)
        } else {
            println(scores.count { it > score } + 1)
        }
    }
}

fun main() {
    `등수 구하기`().solve()
}