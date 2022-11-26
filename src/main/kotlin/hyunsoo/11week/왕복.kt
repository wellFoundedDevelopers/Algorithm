package hyunsoo.`11week`

/**
 *
 * <문제>
 * [왕복](https://www.acmicpc.net/problem/18311)
 *
 * n개의 이어진 일직선상의 코스들을 모두 지나 끝까지 도달한 뒤에 다시 출발 지점으로 돌아온다.
 * 이 때 거리가 K일 때 지나고 잇는 코스의 번호를 출력하자
 *
 * K의 최대는 49억.999...?...
 *
 */
fun main() {

    var (n, k) = readln().trim().split(" ").map { it.toLong() }
    val courseLengthData = readln().trim().split(" ").map { it.toLong() }

    var courseNum = 1

    courseLengthData.forEach { courseLength ->
        k -= courseLength
        if (k < 0) {
            println(courseNum)
            return
        }
        courseNum++
    }

    courseLengthData.reversed().forEach { courseLength ->
        k -= courseLength
        courseNum--
        if (k < 0) {
            println(courseNum)
            return
        }
    }

}