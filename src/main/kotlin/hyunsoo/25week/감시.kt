package hyunsoo.`25week`

/**
 *
 * <문제>
 * [감시](https://www.acmicpc.net/problem/15683)
 *
 * - 아이디어
 * cctv가 최대 8 개
 * 각 cctv의 모든 경우의 수를 뽑아 직접 탐색
 * - 트러블 슈팅
 *
 */
class `전현수_감시` {

    private var row: Int = 0
    private var column: Int = 0

    private data class Position(val x: Int, val y: Int)
    private data class CctvData(val kind: String, val pos: Position)

    // 북 동 남 서
    private val dirs = listOf(
        Position(-1, 0),
        Position(0, 1),
        Position(1, 0),
        Position(0, -1),
    )

    private val board = mutableListOf<MutableList<String>>()
    private val cctvDataList = mutableListOf<CctvData>()
    private val cctvModeList = mutableListOf<Int>()
    private var leastBlindSpotSize = Int.MAX_VALUE

    fun solution() {
        readln().split(" ").map { it.toInt() }.apply {
            row = first()
            column = last()
        }

        repeat(row) {
            val rowData = readln().split(" ").toMutableList()
            board.add(rowData)
        }

        // CCTV 좌표 확인 및 5번 CCTV는 감시 진행
        for (i in 0 until row) {
            for (j in 0 until column) {

                val curBoardInfo = board[i][j]

                // 빈공간이나 벽이 아니라면
                if (curBoardInfo != EMPTY && curBoardInfo != WALL) {

                    when (curBoardInfo) {
                        // 사방을 감지할 수 있는 5번 CCTV라면 - 감지
                        ALL_SIDES_CCTV -> {
                            dirs.forEach { dir ->
                                detect(board, Position(i, j), dir)
                            }

                        }
                        // 그외 나머지 1~4번 CCTV라면 cctvData에 추가
                        in CCTV_KIND_LIST.filter { it != ALL_SIDES_CCTV } -> {
                            cctvDataList.add(
                                CctvData(
                                    curBoardInfo,
                                    Position(i, j)
                                )
                            )
                        }
                    }
                }
            }
        }

        checkAllCases()

        println(leastBlindSpotSize)
    }

    private fun checkAllCases(cnt: Int = 0) {
        if (cnt == cctvDataList.size) {
            val newBoard = board.copy()
            detect(newBoard)
            return
        }
        for (mode in 0..3) {
            cctvModeList.add(mode)
            checkAllCases(cnt + 1)
            cctvModeList.removeLast()
        }
    }

    private fun detect(newBoard: MutableList<MutableList<String>>) {

        cctvDataList.forEachIndexed { index, cctvData ->

            val mode = cctvModeList[index]
            when (cctvData.kind) {
                "1" -> {
                    when (mode) {
                        0 -> detect(newBoard, cctvData.pos, dirs[0])

                        1 -> detect(newBoard, cctvData.pos, dirs[1])

                        2 -> detect(newBoard, cctvData.pos, dirs[2])

                        3 -> detect(newBoard, cctvData.pos, dirs[3])
                    }
                }

                "2" -> {
                    when (mode) {
                        0 -> {
                            detect(newBoard, cctvData.pos, dirs[0])
                            detect(newBoard, cctvData.pos, dirs[2])
                        }

                        1 -> {
                            detect(newBoard, cctvData.pos, dirs[1])
                            detect(newBoard, cctvData.pos, dirs[3])
                        }

                        2 -> {
                            detect(newBoard, cctvData.pos, dirs[2])
                            detect(newBoard, cctvData.pos, dirs[0])
                        }

                        3 -> {
                            detect(newBoard, cctvData.pos, dirs[3])
                            detect(newBoard, cctvData.pos, dirs[1])
                        }
                    }
                }

                "3" -> {
                    when (mode) {
                        0 -> {
                            detect(newBoard, cctvData.pos, dirs[0])
                            detect(newBoard, cctvData.pos, dirs[1])
                        }

                        1 -> {
                            detect(newBoard, cctvData.pos, dirs[1])
                            detect(newBoard, cctvData.pos, dirs[2])
                        }

                        2 -> {
                            detect(newBoard, cctvData.pos, dirs[2])
                            detect(newBoard, cctvData.pos, dirs[3])
                        }

                        3 -> {
                            detect(newBoard, cctvData.pos, dirs[3])
                            detect(newBoard, cctvData.pos, dirs[0])
                        }
                    }
                }

                "4" -> {
                    when (mode) {
                        0 -> {
                            detect(newBoard, cctvData.pos, dirs[0])
                            detect(newBoard, cctvData.pos, dirs[1])
                            detect(newBoard, cctvData.pos, dirs[2])
                        }

                        1 -> {
                            detect(newBoard, cctvData.pos, dirs[1])
                            detect(newBoard, cctvData.pos, dirs[2])
                            detect(newBoard, cctvData.pos, dirs[3])
                        }

                        2 -> {
                            detect(newBoard, cctvData.pos, dirs[2])
                            detect(newBoard, cctvData.pos, dirs[3])
                            detect(newBoard, cctvData.pos, dirs[0])
                        }

                        3 -> {
                            detect(newBoard, cctvData.pos, dirs[3])
                            detect(newBoard, cctvData.pos, dirs[0])
                            detect(newBoard, cctvData.pos, dirs[1])
                        }
                    }
                }
            }
        }

        val curBlindSpot = newBoard.sumOf { rowData ->
            rowData.count { it == EMPTY }
        }
        if (curBlindSpot < leastBlindSpotSize) leastBlindSpotSize = curBlindSpot
    }

    companion object {
        private const val DETECTED = "#"
        private const val EMPTY = "0"
        private const val WALL = "6"
        private const val ALL_SIDES_CCTV = "5"
        private val CCTV_KIND_LIST = listOf("1", "2", "3", "4", "5")
    }

    private fun detect(newBoard: MutableList<MutableList<String>>, startPosition: Position, dir: Position) {

        var nx = startPosition.x
        var ny = startPosition.y

        while (true) {

            nx += dir.x
            ny += dir.y

            // 범위 초과 혹은 벽을 만나면 감지 종료
            if (nx !in 0 until row
                || ny !in 0 until column
                || newBoard[nx][ny] == WALL
            ) break

            // cctv 종류라면 "#"로 바꿔주지 않음.
            if (newBoard[nx][ny] !in CCTV_KIND_LIST) {
                newBoard[nx][ny] = DETECTED
            }
        }
    }

    private fun <T> MutableList<MutableList<T>>.copy(): MutableList<MutableList<T>> {
        val newList = mutableListOf<MutableList<T>>()
        this.forEach { rowData ->
            newList.add(rowData.toMutableList())
        }
        return newList
    }
}

fun main() {
    전현수_감시().solution()
}