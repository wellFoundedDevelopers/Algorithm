package heejik.`27week`

class `주사위 굴리기` {

    enum class Direction(val pos: Pos) {
        RIGHT(Pos(0, 1)), LEFT(Pos(0, -1)), UP(Pos(-1, 0)), DOWN(Pos(1, 0))
    }

    data class Pos(
        var x: Int,
        var y: Int
    ) {
        operator fun plus(other: Pos): Pos {
            return Pos(x + other.x, y + other.y)
        }
    }

    inner class Dice(
        var pos: Pos,
        var nums: MutableList<Int> = mutableListOf(0, 0, 0, 0, 0, 0),
    ) {
        fun move(direction: Direction): Int {

            val newPos = pos + direction.pos
            if ((newPos.x !in 0 until board.size) or (newPos.y !in 0 until board.first().size))
                return -1

            pos = newPos

            val rotateIdxes = when (direction) {
                Direction.UP -> listOf(1, 5, 2, 3, 0, 4)
                Direction.DOWN -> listOf(4, 0, 2, 3, 5, 1)
                Direction.LEFT -> listOf(2, 1, 5, 0, 4, 3)
                Direction.RIGHT -> listOf(3, 1, 0, 5, 4, 2)
            }

            val rotatedNums = mutableListOf<Int>()
            rotateIdxes.forEach {
                rotatedNums.add(nums[it])
            }

            nums = rotatedNums

            if (board[pos.x][pos.y] == 0) {
                board[pos.x][pos.y] = nums.first()
            } else {
                nums[0] = board[pos.x][pos.y]
                board[pos.x][pos.y] = 0
            }


            return nums.last()
        }
    }

    lateinit var dice: Dice
    val board = mutableListOf<MutableList<Int>>()

    fun solve() {
        getInput()
    }

    private fun getInput() {
        val (n, _, x, y, k) = readln().split(' ').map { it.toInt() }
        repeat(n) {
            board.add(readln().split(' ').map { it.toInt() }.toMutableList())
        }

        dice = Dice(pos = Pos(x, y))

        readln().split(' ').map { it.toInt() }.forEach {
            moveDice(Direction.values()[it - 1])
        }
    }

    private fun moveDice(direction: Direction) {
        dice.move(direction).also {
            if (it != -1) println(it)
        }
    }
}

fun main() {
    `주사위 굴리기`().solve()
}