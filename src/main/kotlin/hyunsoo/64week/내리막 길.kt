package hyunsoo.`64week`

/**
 *
 * <문제>
 * [내리막 길](https://www.acmicpc.net/problem/1520)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_내리막_길` {

    private data class Position(val x: Int, val y: Int)

    private val dirs = listOf(
        Position(0, 1),
        Position(0, -1),
        Position(1, 0),
        Position(-1, 0),
    )

    private var n = 0
    private var m = 0
    private val board = mutableListOf<MutableList<Int>>()
    private lateinit var dp: Array<IntArray>

    fun solution() {
        init()
        dfs(Position(0, 0))
        println(dp[0][0])
    }

    private fun init() {
        readln().split(" ").map { it.toInt() }
            .apply {
                n = this.first()
                m = this.last()
            }

        dp = Array(n) {
            IntArray(m) { -1 }
        }

        repeat(n) {
            board.add(readln().split(" ").map { it.toInt() } as MutableList)
        }
    }

    private fun dfs(pos: Position): Int {

        // 목적지라면
        if (pos.x == n - 1 && pos.y == m - 1) {
            return 1
        }

        // 이미 탐색한 곳이라면
        if (dp[pos.x][pos.y] != -1) return dp[pos.x][pos.y]

        dp[pos.x][pos.y] = 0
        dirs.forEach {
            val nx = pos.x + it.x
            val ny = pos.y + it.y

            if (nx !in 0 until n ||
                ny !in 0 until m ||
                board[pos.x][pos.y] <= board[nx][ny]
            ) return@forEach

            dp[pos.x][pos.y] += dfs(Position(nx, ny))
        }

        return dp[pos.x][pos.y]
    }
}

fun main() {
    전현수_내리막_길().solution()
}