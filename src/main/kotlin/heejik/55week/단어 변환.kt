package heejik.`55week`


class `단어 변환` {
    fun solution(begin: String, target: String, words: Array<String>): Int {
        return bfs(begin, target, words)
    }

    fun bfs(begin: String, target: String, words: Array<String>): Int {
        val deque = ArrayDeque<Pair<String, Int>>()
        val visited = mutableListOf<String>()
        visited.add(begin)
        deque.add(begin to 0)

        while (deque.isNotEmpty()) {
            val (current, count) = deque.removeFirst()
            if (current == target) return count
            words.forEach { word ->
                if (word in visited) return@forEach
                var diffCount = 0
                for (i in current.indices) {
                    if (word[i] != current[i]) diffCount++
                }
                if (diffCount == 1) {
                    deque.add(word to count + 1)
                    visited.add(word)
                }
            }
        }

        return 0
    }
}