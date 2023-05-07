package heejik.`34week`

import kotlin.properties.Delegates

class 저울 {

    var n by Delegates.notNull<Int>()
    private val relations = mutableListOf<Pair<Int, Int>>()
    private val reverseRelations = mutableListOf<Pair<Int, Int>>()
    private lateinit var visited: BooleanArray


    fun solve() {
        setting()

        repeat(n) {
            val compareCnt = (it + 1).getCompareCnt(relations) + (it + 1).getCompareCnt(reverseRelations)
            println(n - 1 - compareCnt)
            visited.fill(false)
        }
    }

    private fun setting() {
        n = readln().toInt()
        visited = BooleanArray(n + 1)

        repeat(readln().toInt()) {
            val (big, small) = readln().split(' ').map { it.toInt() }
            relations.add(Pair(big, small))
            reverseRelations.add(Pair(small, big))
        }
    }

    private fun Int.getCompareCnt(pairs: MutableList<Pair<Int, Int>>): Int {
        var cnt = 0
        val queue = ArrayDeque<Int>()
        queue.add(this)
        visited[this] = true

        while (queue.isNotEmpty()) {
            val number = queue.removeFirst()
            pairs.forEach {
                if (it.first == number && visited[it.second].not()) {
                    queue.add(it.second)
                    visited[it.second] = true
                    cnt++
                }
            }
        }

        return cnt
    }
}

fun main() {
    저울().solve()
}