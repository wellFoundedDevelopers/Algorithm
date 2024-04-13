package hyunsoo.`58week`

import java.util.Stack
import kotlin.math.absoluteValue

/**
 *
 * <문제>
 * [술래 잡기](https://www.codetree.ai/training-field/frequent-problems/problems/hide-and-seek/description?page=1&pageSize=20)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_술래_잡기` {

    data class Position(val x: Int, val y: Int) {

        operator fun plus(other: Position): Position {
            return Position(x + other.x, y + other.y)
        }

        operator fun minus(other: Position): Position {
            return Position(x - other.x, y - other.y)
        }

        operator fun times(cnt: Int): Position {
            return Position(x * cnt, y * cnt)
        }

        fun diff(other: Position): Int {
            return (this.x - other.x).absoluteValue + (this.y - other.y).absoluteValue
        }
    }

    private data class Fugitive(val pos: Position, val orientation: FugitiveDirs)

    private val fugitiveList = mutableListOf<Fugitive?>()

    private val treeList = mutableListOf<Position>()

    private val dirs = listOf(
        Position(-1, 0),
        Position(0, 1),
        Position(1, 0),
        Position(0, -1)
    )

    enum class FugitiveDirs(val dir: Position) {
        UP(Position(-1, 0)),
        DOWN(Position(1, 0)),
        RIGHT(Position(0, 1)),
        LEFT(Position(0, -1));

        fun change() = when (this) {
            UP -> DOWN
            DOWN -> UP
            LEFT -> RIGHT
            else -> LEFT
        }
    }

    fun solution() {

        val (size, fugitiveCnt, treeCnt, turnCnt) = readLine()!!.split(" ").map { it.toInt() }
        var totalScore = 0

        var tagger = Position(size / 2, size / 2)
        var taggerDir = dirs.first()
        // 움직인 횟수
        var taggerMoveCnt = 0
        var criterionForCriterion = 0
        var criterion = 1
        var isForward = true

        repeat(fugitiveCnt) {
            val (x, y, dir) = readLine()!!.split(" ").map { it.toInt() }
            fugitiveList.add(
                Fugitive(
                    Position(x - 1, y - 1),
                    if (dir == 1) FugitiveDirs.RIGHT else FugitiveDirs.DOWN
                )
            )
        }

        repeat(treeCnt) {
            readLine()!!.split(" ").map { it.toInt() }.apply {
                treeList.add(Position(first() - 1, last() - 1))
            }
        }

        repeat(turnCnt) { turnIndex ->

            // 도망자 이동
            fugitiveList.forEachIndexed { fugIndex, curFug ->

                if (curFug == null) return@forEachIndexed

                val diff = curFug.pos.diff(tagger)
                if (4 <= diff) return@forEachIndexed

                val nextPos = curFug.pos + curFug.orientation.dir

                // 격자를 벗어나지 않는 경우
                if (nextPos.x in 0 until size &&
                    nextPos.y in 0 until size
                ) {
                    // 다음 칸에 술래 여부 확인
                    if (nextPos == tagger) return@forEachIndexed

                    fugitiveList[fugIndex] = curFug.copy(pos = nextPos)
                    // 격자를 벗어나는 경우
                } else {

                    val rotatedCurFug = curFug.copy(
                        orientation = curFug.orientation.change()
                    )

                    // 방향 전환 적용
                    fugitiveList[fugIndex] = rotatedCurFug

                    val nextPos = rotatedCurFug.pos + rotatedCurFug.orientation.dir

                    // 다음 칸에 술래 여부 확인
                    if (nextPos == tagger) return@forEachIndexed

                    fugitiveList[fugIndex] = rotatedCurFug.copy(pos = nextPos)

                }
            }

            // 술래 이동
            if (isForward) {
                tagger += taggerDir
                taggerMoveCnt++

                // 기준치 만큼 움직였을 경우 방향 전환
                if (taggerMoveCnt == criterion) {
                    taggerDir = dirs[(dirs.indexOf(taggerDir) + 1) % 4]
                    criterionForCriterion++
                    taggerMoveCnt = 0
                }

                // 1 1, 2 2, 3 3 이런식으로 움직일 수 있도록
                if (criterionForCriterion == 2) {
                    criterion++
                    criterionForCriterion = 0
                }

                if (tagger == Position(0, 0)) {
                    isForward = false
                    taggerDir = dirs[2]
                    taggerMoveCnt = 0
                }

            } else {
                tagger += taggerDir
                taggerMoveCnt++

                // 처음 역방향 탐색 시 5 5 5, 4 4 이런식으로 움직여야함.
                if (tagger.x !in 0 until size ||
                    tagger.y !in 0 until size
                ) {
                    tagger -= taggerDir
                    taggerDir = dirs[(dirs.indexOf(taggerDir) + 3) % 4]
                    tagger += taggerDir
                    taggerMoveCnt = 1
                    criterion--
                } else {
                    // 기준치 만큼 움직였을 경우 방향 전환
                    if (taggerMoveCnt == criterion) {
                        taggerDir = dirs[(dirs.indexOf(taggerDir) + 3) % 4]
                        criterionForCriterion++
                        taggerMoveCnt = 0
                    }

                    if (criterionForCriterion == 2) {
                        criterion--
                        criterionForCriterion = 0
                    }

                    if (tagger == Position(size / 2, size / 2)) {
                        isForward = true
                        taggerDir = dirs.first()
                        taggerMoveCnt = 0
                        criterion = 1
                        criterionForCriterion = 0
                    }
                }

            }

            val caughtFugitive = Stack<Int>()
            for (i in 0 until 3) {
                val nextPos = tagger + (taggerDir * i)
                if (nextPos in treeList) continue

                fugitiveList.forEachIndexed { index, fug ->
                    if (fug?.pos == nextPos) caughtFugitive.add(index)
                }
            }

            while (caughtFugitive.isNotEmpty()) {
                val targetIndex = caughtFugitive.pop()
                fugitiveList[targetIndex] = null
                totalScore += turnIndex + 1
            }

        }

        println(totalScore)
    }


}

fun main() {
    전현수_술래_잡기().solution()
}