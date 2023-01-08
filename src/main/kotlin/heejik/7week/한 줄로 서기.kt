package heejik.`7week`

class `한 줄로 서기` {

    fun solve() {

        val n = readln().toInt()
        val man = readln().split(' ').map { it.toInt() }
        val answer = MutableList(n) { Int.MAX_VALUE }

        for (i in 1..n) {
            val leftCnt = man[i - 1]
            var cnt = 0
            answer.forEachIndexed { index, value ->
                if (value == Int.MAX_VALUE && cnt == leftCnt) {
                    answer[index] = i
                }
                if (value > i) {
                    cnt += 1
                }
            }
        }
        answer.forEach {
            print("$it ")
        }
    }
}


fun main() {
    `한 줄로 서기`().solve()
}