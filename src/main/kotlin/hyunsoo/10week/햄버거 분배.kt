package hyunsoo.`10week`

/**
 * <문제>
 * [햄버거 분배](https://www.acmicpc.net/problem/19941)
 */

const val PEOPLE = "P"
const val HAMBURGER = "H"
const val ATE = "X"

fun main() {

    val (n, k) = readln().split(" ").map { it.toInt() }
    val data = readln().chunked(1).toMutableList()
    var ateCnt = 0

    data.forEachIndexed { dataIndex, curData ->
        // 현재 문자열이 사람이라면
        if (curData == PEOPLE) {
            // 왼쪽부터 먹을 수 있는 범위만큼 탐색
            (dataIndex - k..dataIndex + k).forEach { index ->
                // data의 범위를 넘지 않도록
                if (index in 0 until data.size) {
                    // 먹을 수 있는 햄버거가 있다면 먹기
                    if (data[index] == HAMBURGER) {
                        ateCnt++
                        data[index] = ATE
                        return@forEachIndexed
                    }
                }
            }
        }
    }
    println(ateCnt)
}