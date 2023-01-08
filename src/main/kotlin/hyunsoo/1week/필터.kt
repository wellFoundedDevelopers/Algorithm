package hyunsoo.`1week`

/**
 * 제한사항
 * - 숫자 9개가 정렬되어있을 경우 5번째 숫자가 중앙값
 * - 이미지 I는 크기가 R * C인 2차원 픽셀
 *      - R과 C는 3이상 40이하
 * - 각 픽셀은 어두운 정도 V를 나타냄
 *      - V는 0이상 255이하
 * - 필터의 크기는 3 * 3
 *
 * 아이디어
 * - 3 * 3 크기로 이미지를 완전탐색
 * - 중앙값들을 모은 후 T보다 크거나 같은 픽셀의 수를 구하자
 *
 * 이슈
 * - NumberFormat 에러가 발생...왜지?
 *      - C개의 픽셀 값이 주어질 때 공백이 딸려오나봄..^^ 코틀린 왜이래
 */

data class Pos(val x: Int, val y: Int)

fun main() {

    val filterDir = listOf(
        Pos(0, 0),
        Pos(0, 1),
        Pos(0, 2),
        Pos(1, 0),
        Pos(2, 0),
        Pos(1, 1),
        Pos(1, 2),
        Pos(2, 1),
        Pos(2, 2),
    )

    var ans = 0
    val (r, c) = readln().split(" ").map { it.toInt() }
    val imgDataList = mutableListOf<List<Int>>()

    repeat(r) {
        val colList = readln().trim().split(" ").map { it.toInt() }
        imgDataList.add(colList)
    }

    val t = readln().toInt()

    for (row in 0..r - 3) {
        for (col in 0..c - 3) {
            val filteredList = mutableListOf<Int>()
            filterDir.forEach { pos ->
                filteredList.add(imgDataList[row + pos.x][col + pos.y])
            }
            if (t <= filteredList.sorted()[4]) ans++
        }
    }

    println(ans)
}