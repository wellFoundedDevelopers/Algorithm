package heejik.`25week`

import kotlin.math.min
import kotlin.properties.Delegates

class 감시 {

    data class Pos(
        val x: Int,
        val y: Int
    )

    enum class Move(val pos: Pos) {
        TOP(Pos(-1, 0)), RIGHT(Pos(0, 1)), BOTTOM(Pos(1, 0)), LEFT(Pos(0, -1))
    }

    enum class CCTV(val ways: List<List<Move>>) {
        FIRST(ways = listOf(listOf(Move.TOP), listOf(Move.RIGHT), listOf(Move.BOTTOM), listOf(Move.LEFT))),
        SECOND(
            ways = listOf(
                listOf(Move.TOP, Move.BOTTOM),
                listOf(Move.LEFT, Move.RIGHT),
            )
        ),
        THIRD(
            ways = listOf(
                listOf(Move.TOP, Move.RIGHT),
                listOf(Move.RIGHT, Move.BOTTOM),
                listOf(Move.BOTTOM, Move.LEFT),
                listOf(Move.LEFT, Move.TOP),
            )
        ),
        FOURTH(
            ways = listOf(
                listOf(Move.TOP, Move.RIGHT, Move.LEFT),
                listOf(Move.TOP, Move.BOTTOM, Move.RIGHT),
                listOf(Move.TOP, Move.BOTTOM, Move.LEFT),
                listOf(Move.BOTTOM, Move.LEFT, Move.RIGHT),
            )
        ),
        FIFTH(
            ways = listOf(
                listOf(Move.TOP, Move.BOTTOM, Move.LEFT, Move.RIGHT),
            )
        )
    }

    var n by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()
    val room = mutableListOf<MutableList<Int>>()
    var answer = Int.MAX_VALUE
    val cctvPos = mutableListOf<Pos>()
    var blindSpotSize = 0

    fun solve() {
        setting()
        getCCTV()

        println(answer)
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }

        repeat(n) {
            room.add(readln().split(' ').map { it.toInt() }.toMutableList())
        }
    }

    private fun getCCTV() {
        room.forEachIndexed { x, row ->
            row.forEachIndexed { y, i ->
                if (i in 1..5) {
                    cctvPos.add(Pos(x, y))
                } else {
                    if (i == 0)
                        blindSpotSize++
                }
            }
        }
        rec(0, setOf(), idx = 0)
    }


    private fun rec(cnt: Int, monitoredPos: Set<Pos>, idx: Int) {
        if (cnt == cctvPos.size) {
            answer = min(answer, blindSpotSize - monitoredPos.size)
            return
        }

        for (way in CCTV.values()[room[cctvPos[idx].x][cctvPos[idx].y] - 1].ways) {
            rec(cnt + 1, (monitoredPos + monitor(cctvPos[idx], way)), idx = idx + 1)
        }
    }

    private fun monitor(pos: Pos, way: List<Move>): MutableSet<Pos> {
        val monitoredPos = mutableSetOf<Pos>()
        way.forEach { move ->
            var nx = pos.x
            while (true) {
                nx += move.pos.x
                if ((move.pos.x == 0) or (nx !in 0 until n)) break
                if (room[nx][pos.y] == 6) break
                if (room[nx][pos.y] == 0)
                    monitoredPos.add(Pos(nx, pos.y))
            }
            var ny = pos.y
            while (true) {
                ny += move.pos.y
                if ((move.pos.y == 0) or (ny !in 0 until m)) break
                if (room[pos.x][ny] == 6) break
                if (room[pos.x][ny] == 0)
                    monitoredPos.add(Pos(pos.x, ny))
            }
        }
        return monitoredPos
    }
}

fun main() {
    감시().solve()
}