package heejik.`35week`

import kotlin.properties.Delegates

class 토마토 {

    enum class Move(val pos: Pos) {
        UP(pos = Pos(-1, 0, 0)),
        DOWN(pos = Pos(1, 0, 0)),
        GO(pos = Pos(0, -1, 0)),
        BACK(pos = Pos(0, 1, 0)),
        LEFT(pos = Pos(0, 0, -1)),
        RIGHT(pos = Pos(0, 0, 1)),
    }

    data class Pos(
        val z: Int,
        val x: Int,
        val y: Int
    )

    var m by Delegates.notNull<Int>()
    var n by Delegates.notNull<Int>()
    var h by Delegates.notNull<Int>()
    private val tomatoes = mutableListOf<MutableList<MutableList<Int>>>()
    private val queue = ArrayDeque<Pos>()
    private val nextQueue = ArrayDeque<Pos>()
    private var times = 0

    fun solve() {
        getInput()
        while (true) {
            if (ripen().not()) break
            times++
        }

        print(if (tomatoes.any { it.any { row -> row.contains(0) } }) -1 else times)

    }

    private fun getInput() {
        readln().split(' ').map { it.toInt() }.run {
            m = this[0]
            n = this[1]
            h = this[2]
        }

        repeat(h) { z ->
            val tomato = mutableListOf<MutableList<Int>>()
            repeat(n) { x ->
                val row = readln().split(' ').map { it.toInt() }.toMutableList()
                row.forEachIndexed { y, value ->
                    if (value == 1) nextQueue.add(Pos(z, x, y))
                }
                tomato.add(row)
            }
            tomatoes.add(tomato)
        }
    }

    private fun ripen(): Boolean {
        queue.clear()
        queue.addAll(nextQueue)
        nextQueue.clear()

        var isRipened = false

        while (queue.isNotEmpty()) {
            val (z, x, y) = queue.removeFirst()

            for (move in Move.values()) {
                val nz = z + move.pos.z
                val nx = x + move.pos.x
                val ny = y + move.pos.y

                if (nz !in 0 until h || nx !in 0 until n || ny !in 0 until m) continue
                if (tomatoes[nz][nx][ny] == 0) {
                    tomatoes[nz][nx][ny] = 1
                    nextQueue.add(Pos(nz, nx, ny))
                    isRipened = true
                }
            }
        }

        return isRipened
    }
}


fun main() {
    토마토().solve()
}