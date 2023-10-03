package byeonghee.week46

class 소병희_경주로건설 {

    var ans = 0
    var n = 0
    lateinit var board: Array<IntArray>
    lateinit var visited: Array<BooleanArray>

    val dr = intArrayOf(0, 1, 0, -1)
    val dc = intArrayOf(1, 0, -1, 0)

    fun solution(_board: Array<IntArray>): Int {
        board = _board
        n = _board.size
        ans = n * n * 500
        visited = Array(n) { BooleanArray(n) }
        for(d in 0 until 4) {
            val nr = dr[d]
            val nc = dc[d]
            if (nr !in 0 until n || nc !in 0 until n) continue
            if (board[nr][nc] == 1 || visited[nr][nc]) continue

            visited[nr][nc] = true
            dfs(d, nr, nc, 100)
            visited[nr][nc] = false
        }
        return ans
    }

    fun dfs(from: Int, r: Int, c: Int, cost: Int) {
        if (r == n-1 && c == n-1) {
            ans = minOf(ans, cost)
            return
        }

        for(d in 0 until 4) {
            val nr = r + dr[d]
            val nc = c + dc[d]
            if (nr !in 0 until n || nc !in 0 until n) continue
            if (board[nr][nc] == 1 || visited[nr][nc]) continue

            visited[nr][nc] = true
            val ncost = cost + if ((from + d) % 2 == 0) 100 else 600
            dfs(d, nr, nc, ncost)
            visited[nr][nc] = false
        }
    }
}