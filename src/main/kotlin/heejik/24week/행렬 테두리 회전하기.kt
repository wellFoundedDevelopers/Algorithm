package heejik.`24week`

import java.util.Collections

class `행렬 테두리 회전하기` {

    data class Pos(
        val x: Int, val y: Int
    )

    private lateinit var matrix: MutableList<MutableList<Int>>
    private val borderPos = mutableListOf<Pos>()


    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): List<Int> {
        val answer = mutableListOf<Int>()

        var num = 1
        matrix = MutableList(rows) {
            MutableList(columns) { num++ }
        }

        queries.forEach { query ->
            query.run {
                getBorder(
                    pos1 = Pos(component1() - 1, component2() - 1),
                    pos2 = Pos(component3() - 1, component4() - 1)
                )
                answer.add(rotate()).also { borderPos.clear() }
            }
        }
        return answer
    }

    private fun getBorder(pos1: Pos, pos2: Pos) {

        (pos1.y..pos2.y).drop(1).forEach {
            borderPos.add(Pos(pos1.x, it))
        }
        (pos1.x..pos2.x).drop(1).forEach {
            borderPos.add(Pos(it, pos2.y))
        }
        (pos2.y downTo pos1.y).drop(1).forEach {
            borderPos.add(Pos(pos2.x, it))
        }
        (pos2.x downTo pos1.x).drop(1).forEach {
            borderPos.add(Pos(it, pos1.y))
        }
    }

    private fun rotate(): Int {

        val borderValues = borderPos.map {
            matrix[it.x][it.y]
        }.toMutableList()

        Collections.rotate(borderValues, 1)

        borderPos.forEachIndexed { index, pos ->
            matrix[pos.x][pos.y] = borderValues[index]
        }

        return borderValues.minOf { it }
    }
}
