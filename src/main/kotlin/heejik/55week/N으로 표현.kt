package heejik.`55week`

class `N으로 표현` {
    fun solution(N: Int, number: Int): Int {
        val dp = List(size = 9) { mutableListOf<Int>() }

        dp[1].add(N)
        if (number in dp[1]) return 1

        for (i in 2..8) {
            var nn = ""
            repeat(i) {
                nn += N.toString()
            }
            dp[i].add(nn.toInt())
            for (j in 1 until i) {
                dp[j].forEach { stand ->
                    dp[i-j].forEach { rest ->
                        dp[i].add(stand + rest)
                        dp[i].add(stand - rest)
                        dp[i].add(stand * rest)
                        if (rest != 0) {
                            dp[i].add(stand / rest)
                        }
                    }
                }

            }
            if (number in dp[i]) return i
        }
        return -1
    }
}

fun main() {
    `N으로 표현`().solution(5, 3025).also {
        println(it)
    }
}