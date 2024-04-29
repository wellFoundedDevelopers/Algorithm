package heejik.`60week`

class `트리의 지름` {

    fun solve() {
        val n = readln().toInt()

        val nodes = List(size = n + 1) { mutableListOf<Pair<Int, Int>>() }
        val queue = ArrayDeque<Pair<Int, Int>>()
        val visited = BooleanArray(size = n + 1)

        repeat(n - 1) {
            val (root, child, distance) = readln().split(' ').map { it.toInt() }

            nodes[root].add(child to distance)
            nodes[child].add(root to distance)
        }

        fun bfs(start: Int): Pair<Int, Int> {
            visited.fill(element = false)
            // 원소, 가중치
            queue.add(start to 0)
            visited[start] = true
            var maxDis = 0
            var maxNumber = 0

            while (queue.isNotEmpty()) {
                val (number, sumDistance) = queue.removeFirst()
                nodes[number].filter { visited[it.first].not() }.forEach {
                    queue.add(it.first to sumDistance + it.second)
                    visited[it.first] = true
                }
                if (sumDistance > maxDis) {
                    maxDis = sumDistance
                    maxNumber = number
                }
            }

            return maxNumber to maxDis
        }

        val (number, _) = bfs(1)
        println(bfs(number).second)
    }
}


fun main() {
    `트리의 지름`().solve()
}