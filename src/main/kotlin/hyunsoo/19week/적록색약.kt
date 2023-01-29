package hyunsoo.`19week`

/**
 *
 * <문제>
 * [적록색약](https://www.acmicpc.net/problem/10026)
 *
 * - 아이디어
 *
 * 2차월 리스트를 깊은 복사해주는 함수를 만들고 DFS로 탐색
 * - 단, 일반 사람과 적록색약의 복사를 다르게 해주어야함.
 *
 * - 트러블 슈팅
 *
 */
class `전현수_적록색약` {

    data class Pos(val x: Int, val y: Int)

    // 상 하 좌 우
    val dirList = listOf(
        Pos(-1, 0),
        Pos(1, 0),
        Pos(0, -1),
        Pos(0, 1),
    )

    fun solution() {
        val rowCnt = readln().toInt()
        val areaData = mutableListOf<List<Char>>()

        repeat(rowCnt) {
            areaData.add(
                readln().toList()
            )
        }

        val common = areaData.deepCopy()
        var commonAreaCnt = 0
        val weakness = areaData.deepCopy(isCommon = false)
        var weaknessAreaCnt = 0

        for (i in 0 until rowCnt) {
            for (j in 0 until rowCnt) {

                if (checkArea(i, j, common, common[i][j])) commonAreaCnt++
                if (checkArea(i, j, weakness, weakness[i][j])) weaknessAreaCnt++

            }
        }


        println("$commonAreaCnt $weaknessAreaCnt")
    }

    private fun MutableList<List<Char>>.deepCopy(isCommon: Boolean = true): MutableList<MutableList<Char>> {

        val copiedList = mutableListOf<MutableList<Char>>()

        this.forEach { row ->
            val rowList = mutableListOf<Char>()
            row.forEach {
                if (isCommon) rowList.add(it)
                else rowList.add(if (it == 'R') 'G' else it)
            }
            copiedList.add(rowList)
        }

        return copiedList
    }

    private fun checkArea(x: Int, y: Int, areaData: MutableList<MutableList<Char>>, preData: Char): Boolean {

        val curPosData = areaData[x][y]

        if (curPosData == 'X') return false
        if (curPosData != preData) return false

        areaData[x][y] = 'X'

        dirList.forEach { dir ->

            val nx = x + dir.x
            val ny = y + dir.y

            if (nx in 0 until areaData.size && ny in 0 until areaData.size)
                checkArea(nx, ny, areaData, curPosData)
        }

        return true
    }

}

fun main() {
    전현수_적록색약().solution()
}