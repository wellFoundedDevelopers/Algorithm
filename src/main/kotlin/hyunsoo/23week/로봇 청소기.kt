package hyunsoo.`23week`

/**
 *
 * <문제>
 * [로봇 청소기](https://www.acmicpc.net/problem/14503)
 *
 * - 아이디어
 *
 * 시뮬레이션, 구현 문제
 * 1. 현재 칸이 청소되지 않았으면 청소.
 * 2. 현재 칸의 주변 4칸을 탐색.
 *  - 현재 칸의 주변 4칸 중 청소되지 않는 빈 칸이 있다면,
 *    - 반시계 방향으로 90도 회전.
 *    - 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진
 *    - 1로 돌아가기
 *  - 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없다면
 *    - 바라보는 방향을 유지한 채로 후진할 수 있다면 후진 후 1번으로 돌아감.
 *    - 뒤쪽 칸이 벽이라면 작동을 멈춘다.
 *
 *
 * - 트러블 슈팅
 *
 */
class `전현수_로봇_청소기` {

    private var n = 0
    private var m = 0
    private var cleanedUpCnt = 0

    data class Position(var x: Int, var y: Int) {

        operator fun plus(other: Directions): Position {
            return Position(
                this.x + other.position.x,
                this.y + other.position.y
            )
        }

        companion object {
            val initPosition = Position(0, 0)
        }
    }

    // 서 남 동 북
    enum class Directions(val position: Position) {
        WEST(Position(0, -1)),
        SOUTH(Position(1, 0)),
        EAST(Position(0, 1)),
        NORTH(Position(-1, 0))
    }

    private inner class Robot {

        private var isOn = true
        private var position = Position.initPosition
        private var direction = Directions.NORTH
        private val backDirection
            get() = Directions.values()[(this.direction.ordinal + 2) % 4]

        fun setPosition(newPosition: Position) {
            this.position = newPosition
        }

        fun setDirection(newDirection: Directions) {
            this.direction = newDirection
        }

        fun cleanUp() {
            if (boardInfo[position.x][position.y] == NOT_CLEAN) {
                cleanedUpCnt++
                boardInfo[position.x][position.y] = CLEAN
            }

        }

        fun isNotCleanedUpNear(): Boolean {

            Directions.values().forEach { dir ->
                val nx = position.x + dir.position.x
                val ny = position.y + dir.position.y

                if (nx in 0 until n && ny in 0 until m) {
                    if (boardInfo[nx][ny] == NOT_CLEAN) return true
                }
            }

            return false
        }

        fun isFrontCleanedUp(): Boolean {
            val frontPosition = this.position + this.direction
            return frontPosition.x in 0 until n &&
                    frontPosition.y in 0 until m &&
                    boardInfo[frontPosition.x][frontPosition.y] == NOT_CLEAN
        }

        fun move() {
            this.position = this.position + this.direction
        }

        fun moveBack() {
            this.position = this.position + this.backDirection
        }

        fun rotate() {
            this.direction = Directions.values()[(this.direction.ordinal + 1) % 4]
        }

        fun isNotStop() = isOn

        fun turnOff() {
            isOn = false
        }

        fun isBackWall(): Boolean {
            val backPosition = this.position + this.backDirection
            return boardInfo[backPosition.x][backPosition.y] == WALL
        }
    }

    val boardInfo = mutableListOf<MutableList<Int>>()

    fun solution() {

        val robot = Robot()

        readln().split(" ").map { it.toInt() }.apply {
            n = this.first()
            m = this.last()
        }

        readln().split(" ").map { it.toInt() }.apply {
            robot.setPosition(Position(x = this.first(), y = this[1]))
            robot.setDirection(Directions.values()[3 - this.last()])
        }

        repeat(n) {
            boardInfo.add(readln().split(" ").map { it.toInt() }.toMutableList())
        }

        with(robot) {
            while (isNotStop()) {
                // 우선 청소
                cleanUp()
                // 주변에 청소가 안된 곳이 있다면
                if (isNotCleanedUpNear()) {
                    // 반시계 방향으로 회원
                    rotate()
                    // 앞쪽이 청소되지 않은 칸일 경우 전진
                    if (isFrontCleanedUp()) move()
                } else {
                    // 뒤가 벽이라면 종료
                    if (isBackWall()) {
                        turnOff()
                        // 뒤가 벽이 아니라면 후진
                    } else {
                        moveBack()
                    }
                }
            }
        }

        println(cleanedUpCnt)

    }

    companion object {
        const val NOT_CLEAN = 0
        const val WALL = 1
        const val CLEAN = -1
    }
}

fun main() {
    전현수_로봇_청소기().solution()
}