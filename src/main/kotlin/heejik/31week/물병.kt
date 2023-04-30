package heejik.`31week`

import kotlin.math.pow

class 물병 {

    fun solve() {
        var (n, k) = readln().split(' ').map { it.toInt() }
        var answer = 0
        while (Integer.toBinaryString(n).count { it == '1' } > k) {
            val idx = Integer.toBinaryString(n).reversed().indexOf('1')
            val addNumber = 2.0.pow(idx).toInt()
            n += addNumber
            answer += addNumber
        }

        println(answer)
    }
}


fun main() {
    물병().solve()
}