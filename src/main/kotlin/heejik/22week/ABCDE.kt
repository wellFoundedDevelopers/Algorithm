package heejik.`22week`

import kotlin.system.exitProcess

class ABCDE {

    private lateinit var friend: List<MutableSet<Int>>
    private lateinit var visited: BooleanArray
    fun solve() {
        val (n, m) = readln().split(' ').map { it.toInt() }
        friend = List(n) { mutableSetOf() }

        repeat(m) {
            readln().split(' ').map { it.toInt() }.run {
                val a = first()
                val b = last()
                friend[a].add(b)
                friend[b].add(a)
            }
        }

        repeat(n - 1) {
            visited = BooleanArray(n) { false }.apply { this[it] = true }
            dfs(number = it, cnt = 1)
        }

        println(0)
    }

    fun dfs(number: Int, cnt: Int) {
        if (cnt >= 5) {
            println(1)
            exitProcess(0)
        }

        friend[number].forEach {
            if (visited[it]) return@forEach
            visited[it] = true
            dfs(number = it, cnt = cnt + 1)
            visited[it] = false
        }
    }
}


fun main() {
    ABCDE().solve()
}