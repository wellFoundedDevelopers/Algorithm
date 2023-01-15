package hyunsoo.`18week`

/**
 *
 * <문제>
 * [레이저빔은 어디로](https://www.acmicpc.net/problem/3709)
 *
 * 레이저는 일직선으로 움직이는데 우향우 거울에 들어가면 오른쪽으로 90도 꺾여서 나아감.
 * - 아이디어
 * 시뮬레이션!
 *
 * - 트러블 슈팅
 * 초기 방향세팅을 이상하게했음.
 *
 */

enum class Directions(val value: Int, val coordinate: MyCoordinate) {
    NORTH(0, MyCoordinate(-1, 0)),
    EAST(1, MyCoordinate(0, 1)),
    SOUTH(2, MyCoordinate(1, 0)),
    WEST(3, MyCoordinate(0, -1)),
}

data class MyCoordinate(var x: Int, var y: Int)

data class Laser(var x: Int, var y: Int) {

    private var curDir: Directions

    init {
        curDir =
            if (x == 0 && 0 < y) {
                Directions.SOUTH
            } else if (0 < x && y == 0) {
                Directions.EAST
            } else if (0 < x && 0 < y && x < y) {
                Directions.WEST
            } else {
                Directions.NORTH
            }
    }

    fun move() {
        this.x += curDir.coordinate.x
        this.y += curDir.coordinate.y
    }

    fun isOnMirror(board: Array<IntArray>) = board[this.x][this.y] == 1

    fun isOut(boardSize: Int) = x <= 0 || y <= 0 || boardSize + 1 <= x || boardSize + 1 <= y

    fun faceToRight() {
        curDir = Directions.values()[(curDir.value + 1) % 4]
    }

    override fun toString() = "$x $y"

}

fun makeLaser(): Laser {
    return readln().split(" ")
        .map { it.toInt() }
        .run { Laser(this.first(), this[1]) }
}

fun main() {

    val testCaseCnt = readln().toInt()

    repeat(testCaseCnt) {

        val (boardSize, rightMirrorCnt) = readln().split(" ").map { it.toInt() }

        val board = Array(boardSize + 2) {
            IntArray(boardSize + 2) { 0 }
        }

        repeat(rightMirrorCnt) {
            readln().split(" ").map { it.toInt() }.apply {
                board[this.first()][this[1]] = 1
            }
        }

        val laser = makeLaser()

        while (true) {
            laser.move()
            if (laser.isOut(boardSize)) break
            if (laser.isOnMirror(board)) laser.faceToRight()
        }

        println(laser)

    }

}