package byeonghee.week55

class `소병희_꿀 따기` {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val road = IntArray(n+1)
            val sum = IntArray(n+1)
            var max = 0

            readLine().split(" ").forEachIndexed { i, v ->
                road[i+1] = v.toInt()
                sum[i+1] = sum[i] + v.toInt()
                max = max.coerceAtLeast(v.toInt())
            }
            max += sum[n-1] - sum[1]

            for(bee in 2 until n) {
                max = max.coerceAtLeast(sum[bee - 1] + sum[n-1] - road[bee])  // 왼꿀
                        .coerceAtLeast(sum[n] * 2 - sum[1] - sum[bee] - road[bee])  // 오꿀
            }

            println(max)
        }
    }
}

fun main() {
    `소병희_꿀 따기`.solve()
}