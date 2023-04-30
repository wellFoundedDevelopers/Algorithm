package heejik.`31week`

import kotlin.properties.Delegates

class 이모티콘 {

    private var goalCount by Delegates.notNull<Int>()
    private var monitorCount = 1
    private var clipboardCount = 1
    private var time = 1

    fun solve() {
        val visited = MutableList(1001) { BooleanArray(1001) }
        goalCount = readln().toInt()
        val queue = ArrayDeque<Triple<Int, Int, Int>>()
        queue.add(Triple(monitorCount, clipboardCount, time))
        while (queue.isNotEmpty()) {
            val (monitorCount, clipboardCount, time) = queue.removeFirst()
            if (monitorCount == goalCount) {
                println(time)
                break
            }
            if (monitorCount -1  in 0..1000 && clipboardCount in 0..1000 &&
                visited[monitorCount - 1][clipboardCount].not()
            ) {
                queue.add(Triple(monitorCount - 1, clipboardCount, time + 1))
                visited[monitorCount - 1][clipboardCount] = true
            }
            if (monitorCount in 0..1000 &&
                visited[monitorCount][monitorCount].not()) {
                queue.add(Triple(monitorCount, monitorCount, time + 1))
                visited[monitorCount][monitorCount] = true
            }
            if (monitorCount + clipboardCount in 0..1000 && clipboardCount in 0..1000 &&
                    visited[monitorCount + clipboardCount][clipboardCount].not()) {
                queue.add(Triple(monitorCount + clipboardCount, clipboardCount, time + 1))
                visited[monitorCount + clipboardCount][clipboardCount] = true
            }
        }
    }
}

fun main() {
    이모티콘().solve()
}

