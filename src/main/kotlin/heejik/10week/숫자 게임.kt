package heejik.`10week`

class `숫자 게임` {

    fun solve() {
        val n = readln().toInt()
        var value = -1
        var winner = -1


        repeat(n) {
            readln().split(' ').map { it.toInt() }.run {
                val score = getScore(0, 3, 0, 0, mutableListOf())
                if (score >= value) {
                    winner = it + 1
                    value = score
                }
            }
        }
        println(winner)
    }

    private fun List<Int>.getScore(cnt: Int, depth: Int, idx: Int, s: Int, sumList: MutableList<Int>): Int {
        if (cnt == depth) {
            sumList.add(s)
            return -1
        }

        for (i in idx until this.size) {
            getScore(cnt + 1, depth, i + 1, s + this[i], sumList)
        }

        return sumList.map { it % 10 }.maxByOrNull { it % 10 }!!
    }
}


fun main() {
    `숫자 게임`().solve()
}