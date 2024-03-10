package heejik.`53week`

class 작업 {

    private var n = 0
    private var m = 0
    private var findNum = 0
    private var answer = 0

    // 내부엔 나를 쏘는 애들
    private lateinit var relation: List<MutableList<Int>>

    fun solve() {
        input()
        bfs()
        println(answer)
    }

    private fun input() {
        readln().split(' ').map { it.toInt() }.also {
            n = it[0]
            m = it[1]
        }
        relation = List(size = n + 1) { emptyList<Int>().toMutableList() }
        repeat(m) {
            val (shot, receive) = readln().split(' ').map { it.toInt() }
            relation[receive].add(shot)
        }
        findNum = readln().toInt()
    }

    private fun bfs() {
        val deque = ArrayDeque<Int>()
        val visited = BooleanArray(size = n + 1)
        deque.add(findNum)
        visited[findNum] = true

        while (deque.isNotEmpty()) {
            val standard = deque.removeFirst()
            relation[standard].forEach {
                if (visited[it]) return@forEach
                deque.add(it)
                visited[it] = true
                answer++
            }
        }
    }
}

fun main() {
    작업().solve()
}