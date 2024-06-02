package jimin.`64week`

class `가장 긴 증가하는 부분 수열 4` {
    fun solve() {
        val n = readln().toInt()
        val numbers = readln().split(" ").map { it.toInt() }
        val dp = MutableList<MutableList<Int>>(n) { mutableListOf() }
        dp[0] = mutableListOf(0)
        var maxNum = mutableListOf(0)

        for (i in 1 until n) {
            var maxi = mutableListOf<Int>()
            for (j in i - 1 downTo 0) {
                if (numbers[i] > numbers[j]) {
                    if (dp[j].size > maxi.size) {
                        maxi = dp[j].toMutableList()
                    }
                }
            }
            maxi.add(i)
            dp[i] = maxi

            if (dp[i].size > maxNum.size) {
                maxNum = dp[i]
            }
        }

        println(maxNum.size)
        maxNum.forEach {
            print("${numbers[it]} ")
        }
    }
}

fun main() {
    `가장 긴 증가하는 부분 수열 4`().solve()
}