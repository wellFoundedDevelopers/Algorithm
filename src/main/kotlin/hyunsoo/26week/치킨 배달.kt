package hyunsoo.`26week`

import java.util.LinkedList
import java.util.Queue
import kotlin.math.absoluteValue

/**
 *
 * <문제>
 * [치킨 배달](https://www.acmicpc.net/problem/15686)
 *
 * - 아이디어
 *
 * 치킨 거리: 집과 가장 가까운 치킨집 사이의 거리
 * - 치킨 거리는 집을 기준으로 정해지며, 각각의 집은 치킨 거리를 가지고 있음.
 *
 *
 * - 트러블 슈팅
 *
 * - BFS를 할 때 방문 처리를 하지 않았더니 메모리 초과 발생
 * - BFS로 가장 가까운 치킨 집을 탐색했더니 시간 초과 발생
 * - 선택된 치킨집 좌표 리스트에서 최솟값으로 찾았더니 시간초과 발생
 *   - 치킨 집을 선택하는 경우의 수를 재귀적으로 구할 때 인자를 잘못 넘겨주어 백트래킹이 안되어서 시간초과가 발생..
 *
 */
class `전현수_치킨_배달` {

    private data class Position(val x: Int, val y: Int) {

        fun distanceFrom(other: Position) =
            (this.x - other.x).absoluteValue + (this.y - other.y).absoluteValue

    }

    private val board = mutableListOf<MutableList<Int>>()
    private val chickenPositions = mutableListOf<Position>()

    private var boardSize = 0
    private var maxChickenCnt = 0

    private val selectedChickens = mutableListOf<Position>()
    private lateinit var selectedChickenInfo: BooleanArray

    private var minOfChickenDistance = Int.MAX_VALUE


    fun solution() {

        readln().split(" ").map { it.toInt() }.apply {
            boardSize = first()
            maxChickenCnt = last()
        }

        repeat(boardSize) { rowIndex ->
            val rowData = readln()
                .split(" ")
                .map { it.toInt() }
                .toMutableList()
                .apply {
                    forEachIndexed { colIndex, info ->
                        if (info == CHICKEN) chickenPositions.add(
                            Position(rowIndex, colIndex)
                        )
                    }
                }
            board.add(rowData)
        }

        selectedChickenInfo = BooleanArray(chickenPositions.size)

        checkAllCases()

        println(minOfChickenDistance)

    }

    private fun checkAllCases(cnt: Int = 0, startWith: Int = 0) {
        if (cnt == maxChickenCnt) {

            val newBoard = board.deepCopyExceptChicken()
            val curChickenDistance = calculateChickenDistances(newBoard)
            if (curChickenDistance < minOfChickenDistance) minOfChickenDistance = curChickenDistance

            return
        }

        for (index in startWith until chickenPositions.size) {

            if (selectedChickenInfo[index]) continue

            val chickenPosition = chickenPositions[index]

            selectedChickens.add(chickenPosition)
            selectedChickenInfo[index] = true
            checkAllCases(cnt + 1, index + 1)
            selectedChickens.removeLast()
            selectedChickenInfo[index] = false
        }
    }

    private fun calculateChickenDistances(targetBoard: MutableList<MutableList<Int>>): Int {

        var curChickenDistances = 0

        targetBoard.forEachIndexed { rowIndex, rowData ->
            rowData.forEachIndexed { columnIndex, curInfo ->
                if (curInfo == HOUSE) {
                    val curPosition = Position(rowIndex, columnIndex)
                    curChickenDistances += selectedChickens.minOf {
                        curPosition.distanceFrom(it)
                    }
                }
            }
        }

        return curChickenDistances

    }

    private fun MutableList<MutableList<Int>>.deepCopyExceptChicken(): MutableList<MutableList<Int>> {
        val newList = mutableListOf<MutableList<Int>>()
        this.forEach { rowData ->
            newList.add(
                rowData.map {
                    if (it == CHICKEN) EMPTY
                    else it
                } as MutableList
            )
        }
        return newList
    }

    companion object {
        private const val EMPTY = 0
        private const val HOUSE = 1
        private const val CHICKEN = 2
    }

}

fun main() {
    전현수_치킨_배달().solution()
}