package heejik.`50week`

import java.lang.Exception

/**
 * https://loosie.tistory.com/614
 */

class `북쪽나라의 도로` {

    val cities = MutableList(size = 10000 + 1) { mutableListOf<Pair<Int, Int>>() }
    val visited = BooleanArray(size = 10000 + 1)
    val deque = ArrayDeque<Pair<Int, Int>>()

    fun solve() {
        setting()
        println(bfs(bfs(3).first).second)
    }

    private fun setting() {
        while (true) {
            try {
                val (city1, city2, distance) = readln().split(' ').map { it.toInt() }
                cities[city1].add(city2 to distance)
                cities[city2].add(city1 to distance)
            } catch (_: Exception) {
                break
            }
        }
    }

    private fun bfs(start: Int): Pair<Int, Int> {
        var maxDistance = 0
        var maxCity = 0
        visited[start] = true
        deque.add(start to 0)
        while (deque.isNotEmpty()) {
            val (next, point) = deque.removeFirst()
            if (point > maxDistance) {
                maxDistance = point
                maxCity = next
            }
            for ((city, distance) in cities[next]) {
                if (visited[city]) continue
                deque.add(city to point + distance)
                visited[city] = true
            }
        }
        deque.clear()
        visited.fill(false)
        return maxCity to maxDistance
    }
}

fun main() {
    `북쪽나라의 도로`().solve()
}