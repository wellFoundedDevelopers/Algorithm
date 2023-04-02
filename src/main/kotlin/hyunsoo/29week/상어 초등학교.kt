package hyunsoo.`29week`

import java.util.*

/**
 *
 * <문제>
 * [상어 초등학교](https://www.acmicpc.net/problem/21608)
 *
 * - 아이디어
 *
 * 동, 서, 남, 북에 있는 칸들만 인접한 칸이라고 생각
 *
 * 비어있는 칸 중 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정함
 * - 여러 칸이 조건을 만족할 경우, 인접한 칸 중 비어있는 칸이 가장 많은 칸으로 자리를 정함
 *   - 이 또한 여러 칸이 조건을 만족할 경우, 행의 번호가 가장 작은 칸으로
 *     - 이 또한 여러 칸이 조건을 만족할 경우, 열의 번호가 가장 작은 칸으로
 *
 * - 트러블 슈팅
 *
 */
class `전현수_상어_초등학교` {

    private data class Position(val x: Int, val y: Int)
    private data class StudentInfo(val num: Int, val bestFriends: List<Int>)

    // 동 서 남 북
    private val dirs = listOf(
        Position(0, 1),
        Position(0, -1),
        Position(1, 0),
        Position(-1, 0),
    )

    private var size = 0
    private var score = 0

    private val studentInfoQueue: Queue<StudentInfo> = LinkedList()

    private lateinit var board: Array<IntArray>

    fun solution() {

        size = readln().toInt()

        board = Array(size) {
            IntArray(size)
        }

        repeat(size * size) {
            val rawStudentInfo = readln().split(" ").map { it.toInt() }
            studentInfoQueue.add(
                StudentInfo(
                    rawStudentInfo.first(),
                    rawStudentInfo.drop(1)
                )
            )
        }

        repeat(size * size) {

            val curStudentInfo = studentInfoQueue.poll()
            studentInfoQueue.add(curStudentInfo)

            val firstCheckedPositions = checkFirst(curStudentInfo)

            if (firstCheckedPositions.size == 1) {
                val targetPosition = firstCheckedPositions.first()
                board[targetPosition.x][targetPosition.y] = curStudentInfo.num
                return@repeat
            }

            val secondCheckedPositions = checkSecond(firstCheckedPositions)

            if (secondCheckedPositions.size == 1) {
                val targetPosition = secondCheckedPositions.first()
                board[targetPosition.x][targetPosition.y] = curStudentInfo.num
                return@repeat
            }

            val targetPosition = secondCheckedPositions.sortedWith(
                compareBy<Position> { it.x }.thenBy { it.y }
            ).first()

            board[targetPosition.x][targetPosition.y] = curStudentInfo.num
        }

        while (studentInfoQueue.isNotEmpty()) {
            val curStudentInfo = studentInfoQueue.poll()
            board.forEachIndexed { row, rowData ->
                rowData.forEachIndexed { column, curData ->
                    if (curData == curStudentInfo.num) {
                        val cnt = searchAllDirections(row, column, curStudentInfo.bestFriends)
                        when (cnt) {
                            0 -> score += 0
                            1 -> score += 1
                            2 -> score += 10
                            3 -> score += 100
                            4 -> score += 1000
                        }
                    }
                }
            }
        }

        println(score)
    }

    // 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸의 좌표를 반환
    private fun checkFirst(studentInfo: StudentInfo): List<Position> {

        val targetPositions = mutableListOf<Position>()
        var maxNearFriendsCnt = 0

        board.forEachIndexed { row, rowData ->
            rowData.forEachIndexed { column, curData ->
                if (curData == EMPTY) {

                    val curNearFriendsCnt = searchAllDirections(row, column, studentInfo.bestFriends)

                    if (maxNearFriendsCnt < curNearFriendsCnt) {
                        maxNearFriendsCnt = curNearFriendsCnt
                        targetPositions.clear()
                        targetPositions.add(Position(row, column))
                    } else if (maxNearFriendsCnt == curNearFriendsCnt) {
                        targetPositions.add(Position(row, column))
                    }
                }
            }
        }

        return targetPositions
    }

    private fun checkSecond(filteredPositions: List<Position>): List<Position> {

        val targetPositions = mutableListOf<Position>()
        var maxNearEmptyCnt = 0

        board.forEachIndexed { row, rowData ->
            rowData.forEachIndexed { column, curData ->
                if (Position(row, column) in filteredPositions) {

                    val curNearFriendsCnt = searchAllDirections(row, column, listOf(0))

                    if (maxNearEmptyCnt < curNearFriendsCnt) {
                        maxNearEmptyCnt = curNearFriendsCnt
                        targetPositions.clear()
                        targetPositions.add(Position(row, column))
                    } else if (maxNearEmptyCnt == curNearFriendsCnt) {
                        targetPositions.add(Position(row, column))
                    }
                }
            }
        }

        return targetPositions
    }

    private fun searchAllDirections(x: Int, y: Int, bestFriends: List<Int>): Int {
        var nearFriendsCnt = 0
        dirs.forEach { dir ->

            val nx = x + dir.x
            val ny = y + dir.y

            if (nx !in 0 until size || ny !in 0 until size) return@forEach

            if (board[nx][ny] in bestFriends) nearFriendsCnt++

        }
        return nearFriendsCnt
    }

    companion object {
        const val EMPTY = 0
    }
}

fun main() {
    전현수_상어_초등학교().solution()
}