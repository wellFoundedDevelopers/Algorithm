package heejik.`20week`

import kotlin.properties.Delegates

class 양 {

    data class Pos(
        val x: Int,
        val y: Int
    )

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    var r by Delegates.notNull<Int>()
    var c by Delegates.notNull<Int>()
    private val yard = mutableListOf<MutableList<String>>()
    val queue = ArrayDeque<Pos>()

    private var sheepCntWhenMorning = 0
    private var wolfCntWhenMorning = 0
    fun solve() {
        readln().split(' ').map { it.toInt() }.run {
            r = first()
            c = last()
        }

        repeat(r) {
            yard.add(readln().chunked(1).toMutableList())
        }

        yard.forEachIndexed { x, row ->
            row.forEachIndexed { y, filed ->
                if (filed in "ov") {
                    bfs(x, y)
                }
            }
        }

        println("$sheepCntWhenMorning $wolfCntWhenMorning")
    }

    fun bfs(_x: Int, _y: Int) {
        queue.addFirst(Pos(_x, _y))
        var sheepCnt = 0
        var wolfCnt = 0
        if (yard[_x][_y] == "o") sheepCnt++
        if (yard[_x][_y] == "v") wolfCnt++
        yard[_x][_y] = "#"
        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            for (i in dx.indices) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (nx !in 0 until r || ny !in 0 until c) continue
                when (yard[nx][ny]) {
                    "#" -> continue
                    "v" -> wolfCnt++
                    "o" -> sheepCnt++
                }
                yard[nx][ny] = "#"
                queue.add(Pos(nx, ny))
            }
        }
        if (sheepCnt > wolfCnt) {
            sheepCntWhenMorning += sheepCnt
        } else {
            wolfCntWhenMorning += wolfCnt
        }
    }
}

fun main() {
    양().solve()
}


