package heejik.`62week`

class Coins {

    fun solve() {
        val t = readln().toInt()
        repeat(t) {
            val n = readln().toInt()
            val coins = readln().split(' ').map { it.toInt() }
            val money = readln().toInt()
            val dp = MutableList(size = money + 1) { 0 }


            coins.forEach { coin ->
                dp[coin] += 1
                for (i in coin..money) {
                    if (dp[i - coin] != 0) {
                        dp[i] += dp[i - coin]
                    }
                }
            }
            println(dp[money])
        }
    }
}


fun main() {
    Coins().solve()
}