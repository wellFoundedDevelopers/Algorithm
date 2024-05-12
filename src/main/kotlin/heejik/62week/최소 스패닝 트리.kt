package heejik.`62week`

class `최소 스패닝 트리` {

    fun solve() {
        val (v, e) = readln().split(' ').map { it.toInt() }
        val graph = mutableListOf<Triple<Int, Int, Int>>()
        val parent = IntArray(size = v + 1) { it }
        var answer = 0L

        repeat(e) {
            val (a, b, c) = readln().split(' ').map { it.toInt() }
            graph.add(Triple(a, b, c))
        }
        graph.sortBy { it.third }

        for ((a, b, c) in graph) {
            var parentA = parent[a]
            var parentB = parent[b]

            while (parent[parentA] != parentA) {
                parentA = parent[parentA]
            }
            while (parent[parentB] != parentB) {
                parentB = parent[parentB]
            }

            if (parentA == parentB) continue

            if (a < b) {
                parent[parentB] = parentA
            } else {
                parent[parentA] = parentB
            }
            answer += c
        }

        println(answer)
    }
}


fun main() {
    `최소 스패닝 트리`().solve()
}