package hyunsoo.`7week`

/**
 * <문제>
 * [미로 만들기](https://www.acmicpc.net/problem/1347)
 *
 * 홍준이는 미로안의 한 칸에 남쪽을 보며 서있다.
 * 미로는 직사각형 격자모양이고, 각 칸은 이동할 수 있거나, 벽을 포함하고 있음.
 * 모든 행과 열에는 적어도 하나의 이동할 수 있는 칸이 있다.
 * 홍준이는 모든 칸을 걸어다녔고, 자기가 걸어다닌 움직임을 노트에 적었음.
 * F는 앞으로 한 칸, L, R은 왼쪽 혹은 오른쪽으로 전환.
 *
 * 아이디어 - 사람을 객체로 만들고 구현을 해보자?..
 */

class Hyunsoo {

    // 하좌상우
    // 오른쪽이면 +1, 왼쪽이면 - 1
    private val directionsList = listOf(
        Position(1, 0),
        Position(0, -1),
        Position(-1, 0),
        Position(0, 1),
    )

    private var curDir = 0
    private var moveCnt = 0
    private var lastPosition = Position(0, 0)
    private val visitedList = mutableListOf(Position(0, 0))

    fun move() {
        val newPosition = lastPosition + directionsList[curDir]
        visitedList.add(newPosition)
        lastPosition = newPosition
        moveCnt++
    }

    fun turnLeft() {
        if (curDir == 0) {
            curDir = 3
        } else {
            curDir--
        }
    }

    fun turnRight() {
        if (curDir == 3) {
            curDir = 0
        } else {
            curDir++
        }
    }

    fun getVisitedList() = visitedList.toList()

    fun getMapSize() = visitedList.maxOf { it.comparator() } + moveCnt
}

data class Position(val x: Int, val y: Int) {

    operator fun plus(position: Position): Position {
        return this.copy(
            x = this.x + position.x,
            y = this.y + position.y
        )
    }

    fun comparator(): Int =
        if (this.x > this.y) this.x else this.y

}

enum class Command {
    L,
    R,
    F
}

fun main() {

    val commandCnt = readln().toInt()
    val commandList = readln().chunked(1)
    val hyunsoo = Hyunsoo()

    commandList.forEach { command ->
        when (command) {
            Command.L.name -> {
                hyunsoo.turnLeft()
            }

            Command.R.name -> {
                hyunsoo.turnRight()
            }

            Command.F.name -> {
                hyunsoo.move()
            }
        }
    }

    val mapSize = hyunsoo.getMapSize()
    val mapData = Array(1000) {
        Array(1000) { "#" }
    }

    var startXIndex = Int.MAX_VALUE
    var startYIndex = Int.MAX_VALUE
    var endXIndex = 0
    var endYIndex = 0

    hyunsoo.getVisitedList().forEach { pos ->
        val x = pos.x + mapSize
        val y = pos.y + mapSize

        if (startXIndex > x) startXIndex = x
        if (startYIndex > y) startYIndex = y
        if (endXIndex < x) endXIndex = x
        if (endYIndex < y) endYIndex = y
        mapData[x][y] = "."
    }

    for (x in startXIndex..endXIndex) {
        for (y in startYIndex..endYIndex) {
            print(mapData[x][y])
        }
        println()
    }

}