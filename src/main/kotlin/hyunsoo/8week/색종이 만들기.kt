package hyunsoo.`8week`

/**
 * <문제>
 * [색종이 만들기](https://www.acmicpc.net/problem/2630)
 *
 * 전체 종잉의 크기가 N * N(N은 2의 K승)이라면 종이를 자르는 규칙은 다음과 같음
 * - 전체 종이가 모두 같은 색으로 칠해져있지 않으면 가로와 세로로 중간 부분을 잘라서 4개의 영역으로 나누기
 * - 마찬가지로 각 영역이 단일 색상이 아니면 반복하기
 *
 * 규칙에 따라 모든 종이를 자르고 하얀색 색종이와 파란색 색종이가 각각 몇개인지 찾기
 *
 * 아이디어
 * - 분할정복입니다아~~~
 *   - 모든 색상이 같은지 확인
 *     - 같다면 해당 색상의 종이 + 1
 *     - 같지 않다면 4분할 후 각 영역별로 다시 작업
 *
 */
var whiteCnt = 0
var blueCnt = 0

const val WHITE = "0"
const val BLUE = "1"

fun main() {

    val n = readln().toInt()
    val colorPaperInfo = mutableListOf<List<String>>()

    repeat(n) {
        colorPaperInfo.add(
            readln().split(" ")
        )
    }

    cutThePaper(colorPaperInfo)

    println(whiteCnt)
    println(blueCnt)
}

fun cutThePaper(colorPaperInfo: List<List<String>>) {

    val firstColor = colorPaperInfo.first().first()
    val isAllSameColor = colorPaperInfo.all { column ->
        column.all { it == firstColor }
    }

    if (isAllSameColor) {
        when (firstColor) {
            WHITE -> whiteCnt++
            BLUE -> blueCnt++
        }
    } else {
        val size = colorPaperInfo.size
        val halfSize = size / 2

        // 보기 좋게 firstArea라는 변수를 만들어 놓은 것.
        // run 스코프함수가 있어서 영역을 나누자마자 재귀를 호출
        // 이쁘게 묶을 수 있긴할 듯?
        val firstArea = colorPaperInfo
            .subList(0, halfSize)
            .map { column ->
                column.subList(0, halfSize)
            }.run {
                cutThePaper(this)
            }

        val secondArea = colorPaperInfo
            .subList(0, halfSize)
            .map { column ->
                column.subList(halfSize, size)
            }.run {
                cutThePaper(this)
            }

        val thirdArea = colorPaperInfo
            .subList(halfSize, size)
            .map { column ->
                column.subList(0, halfSize)
            }.run {
                cutThePaper(this)
            }

        val fourthArea = colorPaperInfo
            .subList(halfSize, size)
            .map { column ->
                column.subList(halfSize, size)
            }.run {
                cutThePaper(this)
            }
    }
}