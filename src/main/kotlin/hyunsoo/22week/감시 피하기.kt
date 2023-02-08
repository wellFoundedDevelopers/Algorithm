package hyunsoo.`22week`

import java.util.LinkedList
import java.util.Queue
import kotlin.system.exitProcess

/**
 *
 * <문제>
 * [감시 피하기](https://www.acmicpc.net/problem/18428)
 *
 * - 아이디어
 * 선생님 T, 학생 S, 장애물 O
 * 완전 탐색 시 대략 36c3의 복잡도..!! 완탐하자!
 *
 * 모든 경우를 뽑아서 장애물을 설치하지만, "X"인 칸에만 설치!!
 * - 트러블 슈팅
 *
 */
class `전현수_감시_피하기` {

    data class Position(val x: Int, val y: Int) {
        operator fun plus(other: Position): Position {
            return Position(
                this.x + other.x,
                this.y + other.y
            )
        }
    }

    private val pickedPosition = mutableListOf<Int>()
    private val size = readln().toInt()
    private val lastPosition = size * size - 1

    private val dirList = listOf(
        Position(-1, 0),
        Position(1, 0),
        Position(0, -1),
        Position(0, 1),
    )

    private val boardInfo = mutableListOf<List<String>>()

    fun solution() {

        repeat(size) {
            val rowData = readln().split(" ").toList()
            boardInfo.add(rowData)
        }

        checkAllCases(0, 0)

        println(CANT_AVOID)
    }

    private fun checkAllCases(cnt: Int, startWith: Int) {
        if (cnt == 3) {

            // 전체 복사 후 포지션에 끼워 넣기
            val copiedInfo = boardInfo.deepCopy().apply {
                setObstacles(this)
            }

            if (canAvoid(copiedInfo)) {
                println(CAN_AVOID)
                exitProcess(0)
            }
            return
        }

        for (index in startWith..lastPosition) {
            pickedPosition.add(index)
            checkAllCases(cnt + 1, index + 1)
            pickedPosition.removeLast()
        }

    }

    private fun setObstacles(targetList: MutableList<MutableList<String>>) {
        pickedPosition.forEach {
            val pos = it.convertToPosition()
            if (targetList[pos.x][pos.y] == NOTHING) targetList[pos.x][pos.y] = OBSTACLE
        }
    }

    private fun canAvoid(boardInfo: MutableList<MutableList<String>>): Boolean {

        for (i in 0 until size) {
            for (j in 0 until size) {

                val curLocationInfo = boardInfo[i][j]

                if (curLocationInfo == TEACHER) {
                    if (findStudent(i, j, boardInfo)) return false
                }

            }
        }

        return true
    }

    private fun findStudent(x: Int, y: Int, boardInfo: MutableList<MutableList<String>>): Boolean {

        dirList.forEach { dir ->

            val dirQueue: Queue<Position> = LinkedList()
            dirQueue.add(Position(x + dir.x, y + dir.y))

            while (dirQueue.isNotEmpty()) {

                val curPos = dirQueue.poll()
                // 범위 초과시
                if (curPos.x !in 0 until size || curPos.y !in 0 until size) break

                when (boardInfo[curPos.x][curPos.y]) {
                    STUDENT -> return true

                    OBSTACLE -> break

                    else -> dirQueue.add(curPos + dir)
                }

            }

        }

        return false
    }


    private fun Int.convertToPosition(): Position {
        return Position(
            this / size,
            this % size
        )
    }

    private fun <T> Collection<Collection<T>>.deepCopy(): MutableList<MutableList<T>> {
        val newDeepCopiedCollection = mutableListOf<MutableList<T>>()
        this.forEach { collection ->
            newDeepCopiedCollection.add(collection.toMutableList())
        }
        return newDeepCopiedCollection
    }

    companion object {
        private const val STUDENT = "S"
        private const val TEACHER = "T"
        private const val OBSTACLE = "O"
        private const val NOTHING = "X"

        private const val CAN_AVOID = "YES"
        private const val CANT_AVOID = "NO"
    }
}

fun main() {
    전현수_감시_피하기().solution()
}