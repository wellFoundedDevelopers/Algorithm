package heejik.`30week`

import kotlin.properties.Delegates

class `마법사 상어와 비바라기` {

    val dx = listOf(0, -1, -1, -1, 0, 1, 1, 1)
    val dy = listOf(-1, -1, 0, 1, 1, 1, 0, -1)

    inner class Pos(
        var x: Int,
        var y: Int
    ) {
        fun move(direction: Int, distance: Int) {
            repeat(distance) {
                x = (x + dx[direction]).run {
                    if (this < 0) {
                        n - 1
                    } else if (this >= n) {
                        0
                    } else {
                        this
                    }
                }
                y = (y + dy[direction]).run {
                    if (this < 0) {
                        n - 1
                    } else if (this >= n) {
                        0
                    } else {
                        this
                    }
                }
            }
        }

        override fun equals(other: Any?): Boolean {
            val otherPos = other as Pos
            return this.x == otherPos.x && this.y == otherPos.y
        }
    }

    private var n by Delegates.notNull<Int>()
    private var m by Delegates.notNull<Int>()
    private val sky = mutableListOf<MutableList<Int>>()
    private val cloudsPos = mutableListOf<Pos>()
    private val removedCloudsPos = mutableListOf<Pos>()

    fun solve() {
        setting()
        repeat(m) {
            val (d, s) = readln().split(' ').map { it.toInt() }
            operate(d - 1, s)
        }
        println(getAmountOfWater())
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }
        repeat(n) {
            sky.add(readln().split(' ').map { it.toInt() }.toMutableList())
        }
        setCloud()
    }

    private fun setCloud() {
        cloudsPos.run {
            add(Pos(n - 1, 0))
            add(Pos(n - 1, 1))
            add(Pos(n - 2, 0))
            add(Pos(n - 2, 1))
        }
    }


    private fun operate(d: Int, s: Int) {
        move(d, s)
        rain()
        removeCloud()
        makeCloud()
    }

    private fun move(direction: Int, distance: Int) {
        cloudsPos.forEach {
            it.move(direction, distance)
        }
    }

    private fun rain() {
        cloudsPos.forEach {
            sky[it.x][it.y] += 1
        }
    }

    private fun removeCloud() {
        useBug()
        cloudsPos.forEach {
            removedCloudsPos.add(Pos(it.x, it.y))
        }
        cloudsPos.clear()
    }

    private fun useBug() {
        cloudsPos.forEach {
            var cnt = 0
            listOf(1, 3, 5, 7).forEach sub@{ idx ->
                val nx = it.x + dx[idx]
                val ny = it.y + dy[idx]
                if (nx !in 0 until n || ny !in 0 until n) return@sub
                if (sky[nx][ny] == 0) return@sub
                cnt++
            }
            sky[it.x][it.y] += cnt
        }
    }

    private fun makeCloud() {
        repeat(n) { x ->
            repeat(n) { y ->
                if (sky[x][y] >= 2) {
                    val cloudPos = Pos(x, y)
                    if (cloudPos !in removedCloudsPos) {
                        cloudsPos.add(cloudPos)
                        sky[x][y] -= 2
                    }
                }
            }
        }
        removedCloudsPos.clear()
    }

    private fun getAmountOfWater(): Int {
        return sky.sumOf { it.sum() }
    }
}


fun main() {
    `마법사 상어와 비바라기`().solve()
}