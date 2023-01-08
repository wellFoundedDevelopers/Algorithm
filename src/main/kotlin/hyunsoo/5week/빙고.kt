package hyunsoo.`5week`

/**
 * <문제>
 * [빙고](https://www.acmicpc.net/problem/2578)
 * 철수의 빙고판은 2차원으로 입력받고, 사회자가 부르는 번호는 1차원으로 입력받음
 * 사회자가 부르는 번호를 순차탐색하면서 cnt를 1씩 증가시키기!
 * 입력값 및 범위 자체가 적어서 사회자가 수를 부를때 마다 매번 선이 몇개 생겼는지 판정 로직을 굴려야겠다.
 */

val bingoTable = mutableListOf<MutableList<Int>>()
val mcNumList = mutableListOf<Int>()

fun main() {

    var cnt = 0

    repeat(5) {
        val bingoRow = readln().split(" ").map { it.toInt() }.toMutableList()
        bingoTable.add(bingoRow)
    }

    repeat(5) {
        readln()
            .split(" ")
            .map { it.toInt() }
            .forEach {
                mcNumList.add(it)
            }
    }

    mcNumList.forEach { num ->
        cnt++
        bingoTable.forEachIndexed { index, bingoRow ->
            val rowIndex = bingoRow.indexOf(num)

            if (rowIndex != -1) {
                bingoTable[index][rowIndex] = 0
                return@forEachIndexed
            }

        }
        if (howManyLines() >= 3) {
            println(cnt)
            return
        }
    }


}

fun howManyLines(): Int {
    var lineCnt = 0
    var zeroCnt = 0

    // 가로
    for (i in 0 until 5) {

        zeroCnt = 0

        for (j in 0 until 5) {
            if (bingoTable[i][j] == 0) zeroCnt++
        }

        if (zeroCnt == 5) lineCnt++
    }

    // 세로
    for (i in 0 until 5) {

        zeroCnt = 0

        for (j in 0 until 5) {
            if (bingoTable[j][i] == 0) zeroCnt++
        }

        if (zeroCnt == 5) lineCnt++
    }

    zeroCnt = 0
    // 대각선 - 좌상 -> 우하
    for (i in 0 until 5) {
        if (bingoTable[i][i] == 0) zeroCnt++
    }
    if (zeroCnt == 5) lineCnt++

    // 대각선 - 좌하 -> 우상
    zeroCnt = 0
    for (i in 0 until 5) {

        val j = 4 - i
        if (bingoTable[i][j] == 0) zeroCnt++
    }
    if (zeroCnt == 5) lineCnt++

    return lineCnt
}