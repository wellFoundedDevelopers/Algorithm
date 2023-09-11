package heejik.`44week`

class `함께 블록 쌓기` {

    fun solve() {
        val (n, m, h) = readln().split(' ').map { it.toInt() }
        val dp = MutableList(size = h + 1) { 0 }
        val tmpStored = MutableList(size = h + 1) { 0 }
        dp[0] = 1

        repeat(n) {
            val heights = readln().split(' ').map { it.toInt() }

            for (i in 0..h) {
                if (dp[i] > 0) {
                    heights.forEach { height ->
                        if (i + height > h) return@forEach
                        tmpStored[i + height] += dp[i]
                    }
                }
            }
            tmpStored.forEachIndexed { index, i ->
                dp[index] += i
                dp[index] %= 10007
            }
            tmpStored.fill(0)
        }

        println(dp[h])
    }
}


fun main() {
    `함께 블록 쌓기`().solve()
}