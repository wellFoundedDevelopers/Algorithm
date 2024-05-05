package byeonghee.week60

class 소병희_공유기설치 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, c) = readLine().split(" ").map { it.toInt() }
            val house = IntArray(n)

            repeat(n) { i ->
                house[i] = readLine().toInt()
            }

            house.sort()

            fun getWifiCnt(d: Int): Int {
                var p = house[0]
                var cnt = 1

                for(h in 1 until n) {
                    if (house[h] - p >= d) {
                        cnt++
                        p = house[h]
                    }
                }

                return cnt
            }

            var lm = 1
            var rm = house[n-1] - house[0] + 1
            var mid = 0
            var curC = 0

            while(lm < rm - 1) {
                mid = (lm + rm) / 2
                curC = getWifiCnt(mid)

                if (curC >= c) {
                    lm = mid
                }
                else  {
                    rm = mid
                }
            }

            println(lm)
        }
    }
}

fun main() {
    소병희_공유기설치.solve()
}