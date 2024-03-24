package heejik.`55week`

class `여행 가자` {

    fun solve() {
        val n = readln().toInt()
        val m = readln().toInt()

        val relation = List(size = n) { BooleanArray(size = n) }

        repeat(n) { stand ->
            readln().split(' ').map { it.toInt() }.forEachIndexed { index, i ->
                relation[stand][index] = (i == 1)
            }
        }

        val plan = readln().split(' ').map { it.toInt() - 1 }

        for (idx in 0 until plan.size - 1) {
            val visited = BooleanArray(size = n).apply { this[plan[idx]] = true }
            val deque = ArrayDeque<Int>().apply { add(plan[idx]) }
            while (true) {
                val city = deque.removeFirst()
                if (city == plan[idx + 1]) break
                relation[city].forEachIndexed { index, b ->
                    if (b && visited[index].not()) {
                        deque.add(index)
                        visited[index] = true
                    }
                }
                if (deque.isEmpty()) {
                    println("NO"); return
                }
            }
        }
        println("YES")
    }
}


fun main() {
    `여행 가자`().solve()
}