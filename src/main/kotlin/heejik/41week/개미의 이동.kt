package heejik.`41week`

import kotlin.properties.Delegates

class `개미의 이동` {

    enum class Direction {
        LEFT, RIGHT
    }

    private fun String.toDirection(): Direction {
        return if (this == "L") Direction.LEFT else Direction.RIGHT
    }

    data class Ant(
        val pos: Int,
        val dir: Direction
    )

    private var length by Delegates.notNull<Int>()
    private var times by Delegates.notNull<Int>()
    private val ants = mutableListOf<Ant>()
    private val movedAntsPos = mutableListOf<Int>()

    fun solve() {
        setting()
        move()

        movedAntsPos.sorted().forEach {
            print("$it ")
        }
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            length = this[0]
            times = this[1]
        }

        repeat(readln().toInt()) {
            val (pos, dir) = readln().split(' ')
            ants.add(Ant(pos.toInt(), dir.toDirection()))
        }
    }

    private fun move() {
        ants.forEach {
            val distance =
                if (it.dir == Direction.RIGHT) {
                    times + it.pos
                } else {
                    if (times >= it.pos) times - it.pos else it.pos - times
                }
            
            val isUp = ((distance / length) % 2 == 0)
            val movedPos = if (isUp) distance % length else length - (distance % length)
            movedAntsPos.add(movedPos)
        }
    }
}


fun main() {
    `개미의 이동`().solve()
}