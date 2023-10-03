package hyunsoo.`46week`

/**
 *
 * <문제>
 * [숨바꼭질3](https://www.acmicpc.net/problem/13549)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_숨바꼭질_3` {

    private data class Task(val pos: Int, val cost: Int)

    fun solution() {

        val taskQueue = ArrayDeque<Task>()
        val (subin, sister) = readln().split(" ").map { it.toInt() }

        val array = IntArray(200_001) { -1 }
        val visited = BooleanArray(200_001)
        taskQueue.add(Task(subin, 0))

        while (taskQueue.isNotEmpty()) {

            val (currentPosition, currentCost) = taskQueue.removeFirst()

            if (visited[currentPosition]) continue

            array[currentPosition] = currentCost
            visited[currentPosition] = true

            if (currentPosition - 1 in 0..200_000) {
                if (array[currentPosition - 1] < 0) taskQueue.addLast(Task(currentPosition - 1, currentCost + 1))
            }

            if (currentPosition + 1 in 0..200_000) {
                if (array[currentPosition + 1] < 0) taskQueue.addLast(Task(currentPosition + 1, currentCost + 1))
            }

            if (currentPosition * 2 in 0..200_000) {
                if (array[currentPosition * 2] < 0) taskQueue.addFirst(Task(currentPosition * 2, currentCost))
            }
        }

        println(array[sister])

    }
}

fun main() {
    전현수_숨바꼭질_3().solution()
}