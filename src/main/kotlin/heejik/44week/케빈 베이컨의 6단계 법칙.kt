package heejik.`44week`

import kotlin.properties.Delegates

class `케빈 베이컨의 6단계 법칙` {

    var n by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()
    lateinit var relations: List<MutableList<Int>>

    fun solve() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }
        relations = List(size = n + 1) { mutableListOf() }

        repeat(m) {
            readln().split(' ').map { it.toInt() }.run {
                val a = this[0]
                val b = this[1]

                relations[a].add(b)
                relations[b].add(a)
            }
        }

        val answers = MutableList(size = n + 1) { Int.MAX_VALUE }

        for (man in 1..n) {
            answers[man] = bfs(man)
        }
        val minCount = answers.min()
        println(answers)
        println(answers.indexOfFirst { it == minCount })
    }

    private fun bfs(standard: Int): Int {
        var totalCount = 0
        val findRelations = mutableSetOf<Int>()

        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(Pair(standard, 0))

        while (queue.isNotEmpty()) {
            val (man, count) = queue.removeFirst()
            if (findRelations.add(man)) {
                totalCount += count
                relations[man].forEach {
                    if (it !in findRelations) queue.add(Pair(it, count + 1))
                }
            }
        }
        return totalCount
    }
}


fun main() {
    `케빈 베이컨의 6단계 법칙`().solve()
}