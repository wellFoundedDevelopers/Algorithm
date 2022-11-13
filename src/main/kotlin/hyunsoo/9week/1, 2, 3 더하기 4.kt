package hyunsoo.`9week`

fun main() {

    val cnt = readln().toInt()
    val dp = IntArray(10001) { 1 }

    (2 .. 10000).forEach { num ->
        dp[num] += dp[num - 2]
    }
    (3 .. 10000).forEach { num ->
        dp[num] += dp[num - 3]
    }

    repeat(cnt) {
        val target = readln().toInt()
        println(dp[target])
    }

}