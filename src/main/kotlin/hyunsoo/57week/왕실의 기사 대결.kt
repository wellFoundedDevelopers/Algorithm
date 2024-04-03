package hyunsoo.`57week`

import java.util.Stack

/**
 *
 * <문제>
 * [왕실의 기사 대결](https://www.codetree.ai/training-field/frequent-problems/problems/royal-knight-duel/description?page=1&pageSize=20)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_왕실의_기사_대결` {

    private data class Position(val x: Int, val y: Int) {

        operator fun plus(other: Position): Position {
            return Position(x + other.x, y + other.y)
        }
    }

    private data class Knight(val pos: Position, val h: Int, val w: Int, val k: Int)

    private val dirs = listOf(
        Position(-1, 0),
        Position(0, 1),
        Position(1, 0),
        Position(0, -1),
    )

    private val board = mutableListOf<MutableList<Int>>()
    private lateinit var knights: Array<Knight?>

    fun solution() {

        val (l, n, q) = readLine()!!.split(" ").map { it.toInt() }

        repeat(l) {
            val row = readln().split(" ").map {
                val cur = it.toInt()
                if (cur == 2) WALL else cur
            } as MutableList
            board.add(row)
        }

        knights = Array(n + 1) { null }
        val initHealthList = IntArray(n + 1)

        repeat(n) { i ->
            val (r, c, h, w, k) = readln().split(" ").map { it.toInt() }
            knights[i + 1] = Knight(Position(r - 1, c - 1), h, w, k)
            initHealthList[i + 1] = k
        }

        repeat(q) {
            val (knightIndex, dirIndex) = readln().split(" ").map { it.toInt() }

            val dir = dirs[dirIndex]

            val knightMap = Array(l) {
                IntArray(l)
            }.apply {
                knights.forEachIndexed { knightIndex, knight ->
                    knight?.let {

                        for (i in it.pos.x until it.pos.x + it.h) {
                            for (j in it.pos.y until it.pos.y + it.w) {
                                this[i][j] = knightIndex
                            }
                        }

                    }
                }
            }

            val knightsStack = Stack<Int>().apply {
                add(knightIndex)
            }
            val visitedKnightNum = mutableSetOf<Int>()

            val canIMove = canMove(knightIndex, dir, knightMap, visitedKnightNum) {
                knightsStack.add(it)
            }

            if (canIMove) {
                move(knightsStack, dir, knightIndex)
            }

        }

        var totalDamage = 0
        for (i in 1 until knights.size) {
            val curKnight = knights[i] ?: continue
            totalDamage += initHealthList[i] - curKnight.k
        }

        println(totalDamage)
    }

    private fun canMove(
        knightIndex: Int,
        dir: Position,
        knightMap: Array<IntArray>,
        visitedKnightNum: MutableSet<Int>,
        onKnightDetected: (Int) -> Unit,
    ): Boolean {

        visitedKnightNum.add(knightIndex)
        val knight = knights[knightIndex] ?: return false

        val nx = knight.pos.x + dir.x
        val ny = knight.pos.y + dir.y

        if (nx !in 0 until board.size ||
            ny !in 0 until board.size ||
            nx + knight.h !in 0 .. board.size ||
            ny + knight.w !in 0 .. board.size
        ) return false

        for (i in nx until nx + knight.h) {
            for (j in ny until ny + knight.w) {
                if (board[i][j] == WALL) return false
            }
        }

        for (i in nx until nx + knight.h) {
            for (j in ny until ny + knight.w) {
                val curNum = knightMap[i][j]
                if (visitedKnightNum.contains(curNum)) continue
                if (curNum != EMPTY) {
                    visitedKnightNum.add(curNum)
                    onKnightDetected(curNum)
                    if (!canMove(curNum, dir, knightMap, visitedKnightNum) {
                            onKnightDetected(it)
                        }) return false
                }
            }
        }

        return true

    }

    private fun move(knightsStack: Stack<Int>, dir: Position, startKnightIndex: Int) {
        while (knightsStack.isNotEmpty()) {
            val curKnightIndex = knightsStack.pop()
            knights[curKnightIndex] = knights[curKnightIndex]?.copy(
                pos = knights[curKnightIndex]!!.pos + dir
            )

            // 밀치기 시작한 기사라면 체력 감소 X
            if (curKnightIndex == startKnightIndex) continue

            val updatedKnight = knights[curKnightIndex]!!
            var curDamage = 0

            // 체력 감소
            for (nx in updatedKnight.pos.x until updatedKnight.pos.x + updatedKnight.h) {
                for (ny in updatedKnight.pos.y until updatedKnight.pos.y + updatedKnight.w) {
                    if (board[nx][ny] == TRAP) curDamage++
                }
            }

            if (updatedKnight.k <= curDamage) knights[curKnightIndex] = null
            else knights[curKnightIndex] = updatedKnight.copy(
                k = updatedKnight.k - curDamage
            )
        }
    }

    companion object {
        const val EMPTY = 0
        const val TRAP = 1
        const val WALL = -1
    }
}

fun main() {
    전현수_왕실의_기사_대결().solution()
}