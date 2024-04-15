package hyunsoo.`58week`

import kotlin.properties.Delegates

/**
 *
 * <문제>
 * [토끼와 경주](https://www.codetree.ai/training-field/frequent-problems/problems/rabit-and-race/description?page=1&pageSize=20)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_토끼와_경주` {

    private data class Position(val x: Int, val y: Int) {

        operator fun plus(other: Position): Position =
            Position(this.x + other.x, this.y + other.y)

        operator fun minus(other: Position): Position =
            Position(this.x - other.x, this.y - other.y)

        operator fun times(cnt: Int): Position =
            Position(this.x * cnt, this.y * cnt)

    }

    private val dirs = listOf(
        Position(1, 0),
        Position(0, 1),
        Position(0, -1),
        Position(-1, 0),
    )

    private val reversedDirs = listOf(
        Position(-1, 0),
        Position(0, -1),
        Position(0, 1),
        Position(1, 0),
    )

    private lateinit var board: Array<IntArray>

    private lateinit var idList: IntArray
    private lateinit var disList: IntArray
    private lateinit var scoreList: IntArray
    private lateinit var jumpCntList: IntArray
    private lateinit var posList: Array<Position>

    fun solution() {
        val commandCnt = readLine()!!.toInt()

        repeat(commandCnt) {

            val rawCommand = readLine()!!.split(" ").map { it.toInt() }
            operateCommand(rawCommand)

        }

    }

    private fun operateCommand(rawCommand: List<Int>) {

        when (rawCommand.first()) {
            COMMAND_READY -> {
                val n = rawCommand[1]
                val m = rawCommand[2]
                val rabbitCnt = rawCommand[3]

                board = Array(n) {
                    IntArray(m)
                }
                idList = IntArray(rabbitCnt)
                disList = IntArray(rabbitCnt)
                scoreList = IntArray(rabbitCnt)
                jumpCntList = IntArray(rabbitCnt)
                posList = Array(rabbitCnt) {
                    Position(0, 0)
                }


                for ((rabbitIndex, i) in (4 until rawCommand.size step 2).withIndex()) {
                    idList[rabbitIndex] = rawCommand[i]
                    disList[rabbitIndex] = rawCommand[i + 1]
                    posList[rabbitIndex] = Position(0, 0)
                }
            }

            COMMAND_START -> {

                val (repeatCnt, score) = rawCommand.drop(1)
                val pickedRabbitIndex = mutableSetOf<Int>()
                repeat(repeatCnt) {

                    val targetIndex = findHighestPriority()
                    pickedRabbitIndex.add(targetIndex)
                    val targetPos = posList[targetIndex]
                    val targetDis = disList[targetIndex]
                    jumpCntList[targetIndex]++

                    // 방향 구하기
                    val dirCandidates = mutableListOf<Position>()

                    var topX = (targetPos.x + targetDis) % (2 * (board.size - 1))
                    if (topX >= board.size) topX = 2 * (board.size - 1) - topX
                    dirCandidates.add((Position(topX, targetPos.y)))

                    var botX = (targetPos.x - targetDis) % (2 * (board.size - 1))
                    if (botX >= board.size) botX = 2 * (board.size - 1) - botX
                    dirCandidates.add((Position(botX, targetPos.y)))

                    var leftY = (targetPos.y + targetDis) % (2 * (board.first().size - 1))
                    if (leftY >= board.size) leftY = 2 * (board.first().size - 1) - leftY
                    dirCandidates.add((Position(targetPos.x, leftY)))

                    var rightY = (targetPos.y - targetDis) % (2 * (board.first().size - 1))
                    if (rightY >= board.size) rightY = 2 * (board.first().size - 1) - rightY
                    dirCandidates.add((Position(targetPos.x, rightY)))

                    val destinationPos = findHighestPriorityPosition(dirCandidates).first()
                    posList[targetIndex] = destinationPos

                    // 움직인 친구 제외 점수 상승
                    for (i in scoreList.indices) {
                        if (i == targetIndex) continue
                        // 좌표 계산은 -1, -1 씩 해주고 하였음
                        scoreList[i] += destinationPos.x + destinationPos.y + 2
                    }

                }

                // 움직인 애들 중 하나 점수 상승 처리
                val higherPositionForScoring = findHighestPriorityPosition(
                    pickedRabbitIndex.map {
                        posList[it]
                    }
                )
                var whoWouldGetScoreIndex by Delegates.notNull<Int>()
                whoWouldGetScoreIndex = if (higherPositionForScoring.size == 1) {
                    posList.indexOf(higherPositionForScoring.first())
                } else {
                    findMinimumId(higherPositionForScoring.map {
                        posList.indexOf(it)
                    })
                }

                scoreList[whoWouldGetScoreIndex] += score

            }

            COMMAND_MODIFY -> {
                val targetId = rawCommand[1]
                val times = rawCommand[2]

                val targetIndex = idList.indexOf(targetId)
                disList[targetIndex] *= times
            }

            COMMAND_FINISH -> {
                println(scoreList.max())
            }
        }
    }

    private fun findHighestPriority(): Int {

        val firstCandidates = findMinimumJumper()
        if (firstCandidates.size == 1) return firstCandidates.first()

        val secondCandidates = findMinimumSum(firstCandidates)
        if (secondCandidates.size == 1) return secondCandidates.first()

        val thirdCandidates = findMinimumRow(secondCandidates)
        if (thirdCandidates.size == 1) return thirdCandidates.first()

        val fourthCandidates = findMinimumCol(thirdCandidates)
        if (fourthCandidates.size == 1) return fourthCandidates.first()

        return findMinimumId(fourthCandidates)

    }

    private fun findMinimumJumper(): List<Int> {
        val targetIndexes = mutableListOf<Int>()
        var min = Int.MAX_VALUE
        for (i in jumpCntList.indices) {
            val cur = jumpCntList[i]
            if (cur == min) {
                targetIndexes.add(i)
            } else if (cur < min) {
                targetIndexes.clear()
                targetIndexes.add(i)
                min = cur
            }
        }

        return targetIndexes
    }

    private fun findMinimumSum(candidates: List<Int>): List<Int> {
        val targetIndexes = mutableListOf<Int>()
        var min = Int.MAX_VALUE
        for (i in posList.indices) {

            // 후보에 없으면 무시
            if (candidates.contains(i).not()) continue

            val cur = posList[i].x + posList[i].y
            if (cur == min) {
                targetIndexes.add(i)
            } else if (cur < min) {
                targetIndexes.clear()
                targetIndexes.add(i)
                min = cur
            }
        }

        return targetIndexes
    }

    private fun findMinimumRow(candidates: List<Int>): List<Int> {
        val targetIndexes = mutableListOf<Int>()
        var min = Int.MAX_VALUE
        for (i in posList.indices) {

            // 후보에 없으면 무시
            if (candidates.contains(i).not()) continue

            val cur = posList[i].x
            if (cur == min) {
                targetIndexes.add(i)
            } else if (cur < min) {
                targetIndexes.clear()
                targetIndexes.add(i)
                min = cur
            }
        }

        return targetIndexes
    }

    private fun findMinimumCol(candidates: List<Int>): List<Int> {
        val targetIndexes = mutableListOf<Int>()
        var min = Int.MAX_VALUE
        for (i in posList.indices) {

            // 후보에 없으면 무시
            if (candidates.contains(i).not()) continue

            val cur = posList[i].y
            if (cur == min) {
                targetIndexes.add(i)
            } else if (cur < min) {
                targetIndexes.clear()
                targetIndexes.add(i)
                min = cur
            }
        }

        return targetIndexes
    }

    private fun findMinimumId(candidates: List<Int>): Int {
        var min = Int.MAX_VALUE
        var targetIndex = -1
        for (i in posList.indices) {
            // 후보에 없으면 무시
            if (candidates.contains(i).not()) continue
            val cur = idList[i]
            if (cur < min) {
                min = cur
                targetIndex = i
            }
        }

        return targetIndex
    }

    private fun findHighestPriorityPosition(positionList: List<Position>): List<Position> {

        val firTargetList = mutableListOf<Position>()
        var maxSum = -1
        for (i in positionList.indices) {
            val curSum = positionList[i].x + positionList[i].y

            if (maxSum < curSum) {
                maxSum = curSum
                firTargetList.clear()
                firTargetList.add(positionList[i])
            } else if (maxSum == curSum) {
                firTargetList.add(positionList[i])
            }
        }
        if (firTargetList.size == 1) return firTargetList

        val secTargetList = mutableListOf<Position>()
        var maxRow = -1
        for (i in firTargetList.indices) {
            val curRow = firTargetList[i].x

            if (maxRow < curRow) {
                maxRow = curRow
                secTargetList.clear()
                secTargetList.add(firTargetList[i])
            } else if (maxRow == curRow) {
                secTargetList.add(firTargetList[i])
            }
        }
        if (secTargetList.size == 1) return secTargetList

        val thirdTargetList = mutableListOf<Position>()
        var maxCol = -1
        for (i in secTargetList.indices) {
            val curCol = secTargetList[i].y

            if (maxCol < curCol) {
                maxCol = curCol
                thirdTargetList.clear()
                thirdTargetList.add(secTargetList[i])
            } else if (maxCol == curCol) {
                thirdTargetList.add(secTargetList[i])
            }
        }
        return thirdTargetList

    }

    companion object {
        const val COMMAND_READY = 100
        const val COMMAND_START = 200
        const val COMMAND_MODIFY = 300
        const val COMMAND_FINISH = 400
    }
}

fun main() {
    전현수_토끼와_경주().solution()
}