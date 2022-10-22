package heejik.`6week`

// 답보풀


class 근손실 {
    var kit = listOf<Int>()
    var n = 0
    var k = 0
    var result = 0
    var check = booleanArrayOf()

    fun solve() {

        readln().split(' ').map { it.toInt() }.apply {
            n = first()
            k = last()
        }

        kit = readln().split(' ').map { it.toInt() }.toMutableList()
        check = BooleanArray(n) { false }

        weightCheck(0, 500)
        println(result)
    }

    fun weightCheck(cnt: Int, weight: Int) {
        if (weight < 500) {
            return
        }

        if (cnt == n) {
            result += 1
            return
        }

        for (i in 0 until n) {
            if (check[i].not()) {
                check[i] = true
                weightCheck(cnt + 1, weight + kit[i] - k)
                check[i] = false
            }
        }
    }
}

fun main() {
    근손실().solve()
}