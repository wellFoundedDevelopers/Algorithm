package byeonghee.week44

/**
 * 공식 참고: https://m.blog.naver.com/greenbox2000/140187921735
 */

class 소병희_CCW {

    companion object {
        const val X = 0
        const val Y = 1

        fun solve() = with(System.`in`.bufferedReader()) {
            val p = Array(3) { readLine().split(" ").map { it.toInt() } }
            val relation = (p[1][X] - p[0][X]) * (p[2][Y] - p[0][Y]) - (p[2][X] - p[0][X]) * (p[1][Y] - p[0][Y])

            if (relation > 0) println(1)
            else if (relation < 0) println(-1)
            else println(0)
        }
    }
}

fun main() {
    소병희_CCW.solve()
}