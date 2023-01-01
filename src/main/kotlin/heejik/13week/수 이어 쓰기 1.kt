package heejik.`13week`

class `수 이어 쓰기 1` {

    fun solve() {
        val n = readln().toInt()
        var answer = 0

        repeat(n) {
            answer += getDigit(it+1)
        }

        println(answer)
    }

    private fun getDigit(_num : Int): Int {
        var num = _num
        var cnt = 0
        while (num != 0) {
            num /= 10
            cnt ++
        }
        return cnt
    }
}

fun main() {
    `수 이어 쓰기 1`().solve()
}