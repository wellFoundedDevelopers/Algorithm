package hyunsoo.`5week`


/**
 * <문제>
 * [킹](https://www.acmicpc.net/problem/1063)
 *
 * 8*8 크기의 체스판에 킹이 있음.
 * 알파벳은 열 / 숫자는 행을 상
 * 체스판에는 돌이 있고 킹이 돌한테 움직이면 돌은 킹과 동일한 방향으로 이동해야함.
 * 만약 킹이나 돌이 체스판 밖으로 이동하는 경우는 무시하기
 *
 * 아이디어
 * - 객체지향적으로 구현을 해보자..!!!
 */

data class Position(val x: Int, val y: Int)

data class GameCharacter(var x: Int = 0, var y: Int = 0)

enum class Dir(val position: Position) {
    R(Position(1, 0)),
    L(Position(-1, 0)),
    B(Position(0, -1)),
    T(Position(0, 1)),
    RT(Position(1, 1)),
    LT(Position(-1, 1)),
    RB(Position(1, -1)),
    LB(Position(-1, -1)),
}

enum class Alphabet {
    A, B, C, D, E, F, G, H
}

fun main() {

    val (kingLocation, stoneLocation, moveCnt) = readln().split(" ")

    val king = GameCharacter()
    coordinateToPosition(kingLocation).apply {
        king.x = this.x
        king.y = this.y
    }

    val stone = GameCharacter()
    coordinateToPosition(stoneLocation).apply {
        stone.x = this.x
        stone.y = this.y
    }

    repeat(moveCnt.toInt()) {
        val command = readln()
        lateinit var dir: Dir
        when (command) {
            (Dir.R).toString() -> {
                dir = Dir.R
            }

            (Dir.L).toString() -> {
                dir = Dir.L
            }

            (Dir.B).toString() -> {
                dir = Dir.B
            }

            (Dir.T).toString() -> {
                dir = Dir.T
            }

            (Dir.RT).toString() -> {
                dir = Dir.RT
            }

            (Dir.LT).toString() -> {
                dir = Dir.LT
            }

            (Dir.RB).toString() -> {
                dir = Dir.RB
            }

            (Dir.LB).toString() -> {
                dir = Dir.LB
            }
        }

        val nx = dir.position.x
        val ny = dir.position.y

        // 킹이 이동할 수 없는 위치면 명령 무시
        if ((king.x + nx in 0..7).not() ||
            (king.y + ny in 0..7).not()
        ) {
            return@repeat
        }
        // 킹이 이동할 위치에 돌이 있다면
        if (king.x + nx == stone.x &&
            king.y + ny == stone.y
        ) {
            // 돌이 움직일 수 있는 위치라면
            if (stone.x + nx in 0..7 &&
                stone.y + ny in 0..7
            ) {
                // 돌 이동 후 킹 이동
                stone.x += nx
                stone.y += ny
                king.x += nx
                king.y += ny
            }
            // 킹이 이동할 위치에 돌이 없다면 바로 이동
        } else {
            king.x += nx
            king.y += ny
        }

    }
    println(positionToCoordinate(king))
    println(positionToCoordinate(stone))
}

fun coordinateToPosition(coordinate: String): Position {
    val (xPos, yPos) = coordinate.chunked(1)
    return Position(
        Alphabet.values().indexOf(Alphabet.valueOf(xPos)),
        yPos.toInt() - 1
    )
}

fun positionToCoordinate(character: GameCharacter): String {
    return "${(Alphabet.values()[character.x])}${(character.y) + 1}"
}
