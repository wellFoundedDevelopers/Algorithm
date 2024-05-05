val dr = intArrayOf(0, 1, 0, -1)
val dc = intArrayOf(1, 0, -1, 0)

class 소병희_무인도여행 {
    fun solution(maps: Array<String>): IntArray {
        val n = maps.size
        val m = maps[0].length
        val visited = Array(n) { BooleanArray(m) }
        val q = ArrayDeque<IntArray>()
        val answer = ArrayDeque<Int>()
        var island = 0
        var food = 0

        for(i in 0 until n) for(j in 0 until m) {
            if (visited[i][j]) continue
            visited[i][j] = true
            if (maps[i][j] == 'X') continue

            island++
            food = maps[i][j].digitToInt()

            q.add(intArrayOf(i, j))
            while(q.isNotEmpty()) {
                val (r, c) = q.removeFirst()
                for(d in 0 until 4) {
                    val nr = r + dr[d]
                    val nc = c + dc[d]
                    if (nr !in 0 until n || nc !in 0 until m) continue
                    if (visited[nr][nc]) continue
                    visited[nr][nc] = true
                    if(maps[nr][nc] == 'X') continue

                    food += maps[nr][nc].digitToInt()
                    q.add(intArrayOf(nr, nc))
                }
            }

            answer.add(food)
        }

        return if (island == 0) intArrayOf(-1) else answer.sorted().toIntArray()
    }
}