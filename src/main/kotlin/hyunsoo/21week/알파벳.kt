package hyunsoo.`21week`

/**
 *
 * <문제>
 * [알파벳](https://www.acmicpc.net/problem/1987)
 *
 * - 아이디어
 * DFS로 완탐 해보기
 * -- 백트래킹을 곁들인...
 * - 트러블 슈팅
 * -- 아무 곳도 가지 못할 경우 최소 내가 있는 위치인 1임..!
 * 0이 아니라
 * -- 최대 값을 갱신해주는 시점이 이상했음.
 * -- 정사각형의 배열이라고 생각해서 인덱스 이슈가 발생했음. 및 탐색 자체가 안되었음.
 */
class `전현수_알파벳` {

    private data class Position(val x: Int, val y: Int)

    private val alphabetChecker = BooleanArray(26)
    private val board = mutableListOf<MutableList<Char>>()
    private var maxAlphabetCnt = 0

    // 상 하 좌 우
    private val dirList = listOf(
        Position(-1, 0),
        Position(1, 0),
        Position(0, -1),
        Position(0, 1),
    )


    fun solution() {

        val (r, c) = readln().split(" ").map { it.toInt() }

        repeat(r) {
            board.add(readln().toMutableList())
        }

        search(0, 0)

        println(maxAlphabetCnt)

    }

    private fun search(x: Int, y: Int) {

        // 현재 위치의 알파벳 땅을 이미 밟았는지 확인
        val curAlphabet = board[x][y]

        // 이미 해당 땅을 밟았다면 return
        if (alphabetChecker[curAlphabet.alphabetToIndex()]) {
            return
        }

        // 그렇지 않다면 dfs 탐색
        alphabetChecker[curAlphabet.alphabetToIndex()] = true
        updateMaxAlphabetCnt()
        dirList.forEach { pos ->

            val nx = x + pos.x
            val ny = y + pos.y

            if (nx in 0..board.lastIndex && ny in 0..board.first().lastIndex) {
                search(nx, ny)
            }
        }

        alphabetChecker[curAlphabet.alphabetToIndex()] = false
    }

    private fun updateMaxAlphabetCnt() {
        val curVisitedAlphabetCnt = alphabetChecker.count { it }
        if (maxAlphabetCnt < curVisitedAlphabetCnt) {
            maxAlphabetCnt = curVisitedAlphabetCnt
        }
    }

    private fun Char.alphabetToIndex(): Int = this.code - 65
}

fun main() {
    전현수_알파벳().solution()
}