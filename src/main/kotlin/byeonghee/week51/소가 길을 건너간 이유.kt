package byeonghee.week51

class 소병희_소가길을건너간이유 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val cows = IntArray(11) { -1 }
            var ans = 0

            repeat(n) {
                val (num, pos) = readLine().split(" ").map { it.toInt() }

                if (cows[num] != -1 && cows[num] != pos) ans++
                cows[num] = pos
            }

            println(ans)
        }
    }
}

fun main() {
    소병희_소가길을건너간이유.solve()
}