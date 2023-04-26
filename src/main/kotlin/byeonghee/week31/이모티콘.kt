package byeonghee.week31

class 소병희_이모티콘 {

    companion object {
        fun solve() {
            val s = readln().toInt()
            val dp = IntArray(s+1) { it }.apply { this[1] = 0 }

            for(clipboard in 2 until s) for(emoji in clipboard .. s) {
                val ops = 1 + (emoji - clipboard) / clipboard + if (emoji % clipboard == 0) 0 else (1 + clipboard - (emoji % clipboard))
                dp[emoji] = dp[emoji].coerceAtMost(dp[clipboard] + ops)
            }

            print(dp[s])
        }
    }
}

fun main() {
    소병희_이모티콘.solve()
}