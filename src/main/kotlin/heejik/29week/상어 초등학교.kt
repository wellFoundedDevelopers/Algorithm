package heejik.`29week`

import kotlin.math.pow
import kotlin.properties.Delegates

class `상어 초등학교` {

    data class Pos(
        val x: Int,
        val y: Int
    )

    val dx = listOf(1, -1, 0, 0)
    val dy = listOf(0, 0, 1, -1)

    private var n by Delegates.notNull<Int>()
    private lateinit var seat: MutableList<MutableList<Int>>
    private val likeStudent = mutableMapOf<Int, List<Int>>()
    fun solve() {
        setting()
        sitDown()
        printSatisfaction()
    }


    private fun setting() {
        n = readln().toInt()
        seat = MutableList(n) { MutableList(n) { 0 } }

        repeat(n * n) {
            readln().split(' ').map { it.toInt() }.run {
                likeStudent[this.first()] = this.drop(1)
            }
        }
    }

    private fun sitDown() {
        for (student in likeStudent.keys) {
            val firstAnswer = matchFirstCondition(student)

            if (firstAnswer.first) {
                seat[firstAnswer.second.first().x][firstAnswer.second.first().y] = student
                continue
            }

            val secondAnswer = matchSecondCondition(firstAnswer.second)

            if (secondAnswer.first) {
                seat[secondAnswer.second.first().x][secondAnswer.second.first().y] = student
                continue
            }
            val thirdAnswer = matchThirdCondition(secondAnswer.second)
            seat[thirdAnswer.x][thirdAnswer.y] = student
        }
    }

    private fun matchFirstCondition(student: Int): Pair<Boolean, List<Pos>> {
        var goodSeat = mutableListOf<Pos>()
        var maxCnt = 0
        seat.forEachIndexed { x, row ->
            row.forEachIndexed second@ { y, i ->
                if (i != 0) return@second
                var cnt = 0
                for (i in dx.indices) {
                    val nx = x + dx[i]
                    val ny = y + dy[i]
                    if ((nx !in 0 until n) or (ny !in 0 until n)) continue
                    if (seat[nx][ny] in likeStudent[student]!!) {
                        cnt++
                    }
                }
                if (cnt > maxCnt) {
                    goodSeat = mutableListOf(Pos(x, y))
                    maxCnt = cnt
                } else if (cnt == maxCnt) {
                    goodSeat.add(Pos(x, y))
                }
            }
        }
        return if (goodSeat.size == 1) Pair(true, goodSeat) else Pair(false, goodSeat)
    }

    private fun matchSecondCondition(seatPos: List<Pos>): Pair<Boolean, List<Pos>> {
        var goodSeat = mutableListOf<Pos>()
        var maxCnt = 0
        for (pos in seatPos) {
            var cnt = 0
            for (i in dx.indices) {
                val nx = pos.x + dx[i]
                val ny = pos.y + dy[i]
                if ((nx !in 0 until n) or (ny !in 0 until n)) continue
                if (seat[nx][ny] != 0) continue
                if (seat[nx][ny] == 0) {
                    cnt++
                }
            }
            if (cnt > maxCnt) {
                goodSeat = mutableListOf(pos)
                maxCnt = cnt
            } else if (cnt == maxCnt) {
                goodSeat.add(pos)
            }
        }

        return if (goodSeat.size == 1) Pair(true, goodSeat) else Pair(false, goodSeat)
    }

    private fun matchThirdCondition(seatPos: List<Pos>): Pos {
        return seatPos.first()
    }


    private fun printSatisfaction() {
        var satisfaction = 0
        seat.forEachIndexed { x, row ->
            row.forEachIndexed { y, student ->
                var cnt = 0
                for (i in dx.indices) {
                    val nx = x + dx[i]
                    val ny = y + dy[i]
                    if ((nx !in 0 until n) or (ny !in 0 until n)) continue
                    if (seat[nx][ny] in likeStudent[student]!!) cnt++
                }
                satisfaction += if (cnt == 0) 0 else 10.0.pow(cnt - 1).toInt()
            }
        }
        println(satisfaction)
    }
}

fun main() {
    `상어 초등학교`().solve()
}