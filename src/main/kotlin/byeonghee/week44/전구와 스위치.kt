package byeonghee.week44

class 소병희_전구와스위치 {

    companion object {
        const val INF = 999_999

        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val origin = readLine().map { it == '1' }
            val goal = readLine().map { it == '1' }
            val arr = BooleanArray(n+1)

            var cnt1 = 0
            repeat(n) { i -> arr[i] = origin[i] }
            for(s in 1 until n) {
                if (arr[s-1] != goal[s-1]) {
                    cnt1++
                    arr[s-1] = arr[s-1].not()
                    arr[s] = arr[s].not()
                    arr[s+1] = arr[s+1].not()
                }
            }
            if (arr[n-1] != goal[n-1]) cnt1 = INF

            var cnt2 = 1
            repeat(n) { i -> arr[i] = origin[i] }
            arr[0] = origin[0].not()
            arr[1] = origin[1].not()
            for(s in 1 until n) {
                if (arr[s-1] != goal[s-1]) {
                    cnt2++
                    arr[s-1] = arr[s-1].not()
                    arr[s] = arr[s].not()
                    arr[s+1] = arr[s+1].not()
                }
            }
            if (arr[n-1] != goal[n-1]) cnt2 = INF

            if (cnt1 < cnt2) println(cnt1)
            else if (cnt1 > cnt2) println(cnt2)
            else if (cnt1 == INF) println(-1)
            else println(cnt1)
        }
    }
}

fun main() {
    소병희_전구와스위치.solve()
}