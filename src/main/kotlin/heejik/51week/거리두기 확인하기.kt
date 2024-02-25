package heejik.`51week`

// P -> 응시자 | O -> 빈테이블 | X -> 파티션

class `거리두기 확인하기` {
    data class Pos(
        val x: Int,
        val y: Int,
    )

    // 위, 오른, 아래, 왼, 오.위, 오.아래, 왼.위, 왼.아래
    private val checkDistance = listOf(
        Pos(-2, 0),
        Pos(0, 2),
        Pos(2, 0),
        Pos(0, -2),
        // -------------
        Pos(-1, 1),
        Pos(1, 1),
        Pos(-1, -1),
        Pos(1, -1)
    )
    private val checkPos = listOf(
        listOf(Pos(-1, 0)),
        listOf(Pos(0, 1)),
        listOf(Pos(1, 0)),
        listOf(Pos(0, -1)),
        // ------------------
        listOf(Pos(-1, 0), Pos(0, 1)),
        listOf(Pos(1, 0), Pos(0, 1)),
        listOf(Pos(-1, 0), Pos(0, -1)),
        listOf(Pos(1, 0), Pos(0, -1))
    )


    fun solution(places: Array<Array<String>>): IntArray {
        val answer: MutableList<Int> = MutableList(size = 5) { -1 }

        places.forEachIndexed { order, waitingRoom ->
            val isKeepDistance = checkKeepDistance(waitingRoom = waitingRoom.toList())
            answer[order] = if (isKeepDistance) 1 else 0
        }

        return answer.toIntArray()
    }


    private fun checkKeepDistance(waitingRoom: List<String>): Boolean {
        for ((x, row) in waitingRoom.withIndex()) {
            for ((y, element) in row.withIndex()) {
                if (element == 'P') {

                    // distance == 2
                    repeat(checkDistance.size) {
                        val nx = x + checkDistance[it].x
                        val ny = y + checkDistance[it].y

                        if (nx in 0 until 5 && ny in 0 until 5 && waitingRoom[nx][ny] == 'P') {
                            checkPos[it].forEach { check ->
                                val checkX = x + check.x
                                val checkY = y + check.y
                                if (waitingRoom[checkX][checkY] == 'O') return true
                            }
                        }
                    }

                    // distance == 1
                    listOf(Pos(1, 0), Pos(-1, 0), Pos(0, 1), Pos(0, -1)).forEach {
                        val nx = x + it.x
                        val ny = y + it.y

                        if (nx in 0 until 5 && ny in 0 until 5 && waitingRoom[nx][ny] == 'P') return true
                    }
                }
            }
        }
        return false
    }
}