package hyunsoo.`28week`

/**
 *
 * <문제>
 * [미세먼지 안녕!](https://www.acmicpc.net/problem/17144)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_미세먼지_안녕` {

    private data class Position(var x: Int, var y: Int) {

        fun moveRight() {
            this.y++
        }

        fun moveTop() {
            this.x--
        }

        fun moveLeft() {
            this.y--
        }

        fun moveBottom() {
            this.x++
        }
    }

    private data class SpreadInfo(val size: Int, val positionList: List<Position>)

    private data class MoveDustInfo(val pre: Position, val dir: Position, val size: Int)

    // 상 하 좌 우
    private val dirs = listOf(
        Position(-1, 0),
        Position(1, 0),
        Position(0, -1),
        Position(0, 1),
    )

    private val moveInfoList = mutableListOf<MoveDustInfo>()

    private val airCleaner = mutableListOf<Position>()

    private val room = mutableListOf<MutableList<Int>>()

    fun solution() {

        val (r, c, t) = readln().split(" ").map { it.toInt() }

        repeat(r) { rowIndex ->
            val rowData = readln().split(" ").map { it.toInt() }.apply {
                this.forEachIndexed { columnIndex, value ->
                    if (value == AIR_CLEANER) airCleaner.add(Position(rowIndex, columnIndex))
                }
            } as MutableList
            room.add(rowData)
        }

        repeat(t) {

            val SpreadInfos = mutableListOf<SpreadInfo>()

            // 미세먼지 확산정보를 처리
            room.forEachIndexed { rowIndex, rowData ->
                rowData.forEachIndexed { columnIndex, value ->
                    if (value != EMPTY && value != AIR_CLEANER) {

                        val spreadList = spread(rowIndex, columnIndex)
                        val spreadSize = (room[rowIndex][columnIndex] / 5)

                        room[rowIndex][columnIndex] -= spreadSize * spreadList.size

                        SpreadInfos.add(
                            SpreadInfo(spreadSize, spreadList)
                        )

                    }
                }
            }

            // 미세먼지 확산 정보를 기반으로 확산 작업 진행
            SpreadInfos.forEach { spreadInfo ->
                spreadInfo.positionList.forEach { pos ->
                    room[pos.x][pos.y] += spreadInfo.size
                }
            }

            airCleaner.forEachIndexed { index, startPosition ->

                moveInfoList.clear()
                val curAir = startPosition.apply {
                    moveRight()
                }

                // 우
                blowRight(curAir, index == 0)

                if (index == 0) {
                    blowTop(curAir)
                } else blowBottom(curAir)

                // 좌
                blowLeft(curAir, index == 0)

                // 하
                if (index == 0) {
                    blowBottom(curAir)
                } else {
                    blowTop(curAir)
                }

                moveInfoList.forEach infoLoop@{ info ->

                    val pre = info.pre
                    val dir = info.dir

                    // 공기청정기에 들어가면 삭제
                    if (room[pre.x + dir.x][pre.y + dir.y] == AIR_CLEANER) return@infoLoop

                    room[pre.x + dir.x][pre.y + dir.y] = info.size
                }

            }
        }

        room.sumOf { rowData ->
            rowData
                .filter { it != AIR_CLEANER && it != 0 }
                .sumOf { it }
        }.apply {
            println(this)
        }

    }

    private fun blowRight(curAir: Position, isFirstAirCleaner: Boolean) {
        // 우
        while (curAir.y in 0 until room.first().size) {

            val curInfo = room[curAir.x][curAir.y]

            if (curInfo != EMPTY && curInfo != AIR_CLEANER) {
                moveInfoList.add(
                    // 오른쪽으로 이동
                    if (curAir.y != room.first().size - 1) {
                        MoveDustInfo(curAir.copy(), dirs[3], curInfo)
                    } else {
                        // 첫 번째 공기청정기 바람의 마지막은 위로, 아니면 아래로
                        if (isFirstAirCleaner) MoveDustInfo(curAir.copy(), dirs[0], curInfo)
                        else MoveDustInfo(curAir.copy(), dirs[1], curInfo)
                    }
                )
                room[curAir.x][curAir.y] = 0
            }
            curAir.moveRight()
        }
        curAir.moveLeft()
    }

    private fun blowTop(curAir: Position) {
        while (curAir.x in 0 until room.size) {

            val curInfo = room[curAir.x][curAir.y]

            if (curInfo == AIR_CLEANER) return

            if (curInfo != EMPTY) {
                moveInfoList.add(
                    // 상 이동
                    if (curAir.x != 0) {
                        MoveDustInfo(curAir.copy(), dirs[0], curInfo)
                    } else MoveDustInfo(curAir.copy(), dirs[2], curInfo)
                )
                room[curAir.x][curAir.y] = 0
            }
            curAir.moveTop()
        }
        curAir.moveBottom()
    }

    private fun blowLeft(curAir: Position, isFirstAirCleaner: Boolean) {
        while (curAir.y in 0 until room.first().size) {

            val curInfo = room[curAir.x][curAir.y]

            if (curInfo != EMPTY && curInfo != AIR_CLEANER) {
                moveInfoList.add(
                    // 좌로 이동
                    if (curAir.y != 0) {
                        MoveDustInfo(curAir.copy(), dirs[2], curInfo)
                    } else {
                        // 첫 번째 공기청정기 바람의 마지막은 아래로, 아니면 위로
                        if (isFirstAirCleaner) MoveDustInfo(curAir.copy(), dirs[1], curInfo)
                        else MoveDustInfo(curAir.copy(), dirs[0], curInfo)
                    }
                )
                room[curAir.x][curAir.y] = 0
            }
            curAir.moveLeft()
        }
        curAir.moveRight()
    }

    private fun blowBottom(curAir: Position) {
        while (curAir.x in 0 until room.size) {

            val curInfo = room[curAir.x][curAir.y]

            if (curInfo == AIR_CLEANER) return

            if (curInfo != EMPTY) {

                moveInfoList.add(
                    // 하 이동
                    if (curAir.x != room.size - 1) {
                        MoveDustInfo(curAir.copy(), dirs[1], curInfo)
                    } else MoveDustInfo(curAir.copy(), dirs[2], curInfo)
                )
                room[curAir.x][curAir.y] = 0
            }
            curAir.moveBottom()
        }
        curAir.moveTop()
    }


    private fun spread(r: Int, c: Int): List<Position> {

        val spreadDusts = mutableListOf<Position>()

        dirs.forEach { dir ->

            val nx = r + dir.x
            val ny = c + dir.y

            // 범위 초과 혹은 공기 청정기일경우 확신 X
            if (nx !in 0 until room.size ||
                ny !in 0 until room.first().size ||
                room[nx][ny] == AIR_CLEANER
            ) return@forEach

            spreadDusts.add(Position(nx, ny))
        }

        return spreadDusts

    }

    companion object {
        private const val AIR_CLEANER = -1
        private const val EMPTY = 0
    }
}

fun main() {
    전현수_미세먼지_안녕().solution()
}