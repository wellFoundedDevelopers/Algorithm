package heejik.`35week`

class 장군 {

    enum class Move(val pos: Pos) {
        TOP(Pos(-1, 0)),
        BOTTOM(Pos(1, 0)),
        LEFT(Pos(0, -1)),
        RIGHT(Pos(0, 1)),
        TOP_LEFT(Pos(-1, -1)),
        TOP_RIGHT(Pos(-1, 1)),
        BOTTOM_LEFT(Pos(1, -1)),
        BOTTOM_RIGHT(Pos(1, 1)),
    }

    enum class Route(val moves: List<Move>) {
        FIRST(listOf(Move.TOP, Move.TOP_LEFT, Move.TOP_LEFT)),
        SECOND(listOf(Move.TOP, Move.TOP_RIGHT, Move.TOP_RIGHT)),

        THIRD(listOf(Move.LEFT, Move.TOP_LEFT, Move.TOP_LEFT)),
        FOURTH(listOf(Move.LEFT, Move.BOTTOM_LEFT, Move.BOTTOM_LEFT)),

        FIFTH(listOf(Move.RIGHT, Move.TOP_RIGHT, Move.TOP_RIGHT)),
        SIXTH(listOf(Move.RIGHT, Move.BOTTOM_RIGHT, Move.BOTTOM_RIGHT)),

        SEVENTH(listOf(Move.BOTTOM, Move.BOTTOM_LEFT, Move.BOTTOM_LEFT)),
        EIGHTH(listOf(Move.BOTTOM, Move.BOTTOM_RIGHT, Move.BOTTOM_RIGHT)),
    }


    data class Pos(
        val x: Int,
        val y: Int
    )

    private lateinit var kingPos: Pos
    private lateinit var sangPos: Pos
    private val n = 10
    private val m = 9


    fun solve() {
        setting()
        move().also { count ->
            println(count)
        }
    }

    private fun setting() {
        val (sangX, sangY) = readln().split(' ').map { it.toInt() }
        val (kingX, kingY) = readln().split(' ').map { it.toInt() }

        sangPos = Pos(sangX, sangY)
        kingPos = Pos(kingX, kingY)
    }

    private fun move(): Int {
        val visited = MutableList(n) { MutableList(m) { false } }
        val queue = ArrayDeque<Pair<Pos, Int>>()
        queue.add(Pair(sangPos, 0))
        visited[sangPos.x][sangPos.y] = true

        while (queue.isNotEmpty()) {
            val (pos, count) = queue.removeFirst()
            val x = pos.x
            val y = pos.y
            if (kingPos.x == x && kingPos.y == y) return count

            Route.values().forEach route@{ route ->
                var nx = x
                var ny = y
                route.moves.forEachIndexed move@{ index, move ->
                    nx += move.pos.x
                    ny += move.pos.y
                    if (index != 2 && kingPos.x == nx && kingPos.y == ny) return@route
                    if (nx !in 0 until n || ny !in 0 until m) return@route
                }
                if (visited[nx][ny]) return@route
                visited[nx][ny] = true
                queue.add(Pair(Pos(nx, ny), count + 1))
            }
        }
        return -1
    }
}

fun main() {
    장군().solve()
}