package heejik.`23week`

class 합분해 {

    fun solve() {
        val (n, k) = readln().split(' ').map { it.toInt() }

        val dp = MutableList(n + 1) { 1UL }


        repeat(k-1) {
            val tmp = dp.toMutableList()
            for (num in 1..n) {
                for (subNum in num..n){
                    dp[subNum] += tmp[subNum - num] % 1000000000UL
                }
            }
        }

        println(dp[n] % 1000000000UL)
    }
}

fun main() {
    합분해().solve()
}