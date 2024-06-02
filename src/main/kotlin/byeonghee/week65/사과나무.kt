package byeonghee.week65

class 소병희_사과나무 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val trees = IntArray(n)

            var oneCnt = 0 //1 짜리 물뿌리개의 최소 필요개수
            var twoCnt = 0 // 2짜리 물뿌리개의 최대 필요개수
            var sum = 0

            readLine().split(" ").forEachIndexed { i, v ->
                trees[i] = v.toInt().also {
                    if (it % 2 == 1) oneCnt++
                    if (it >= 2) twoCnt += it / 2
                    sum += it
                }
            }

            if (sum % 3 > 0 || oneCnt > twoCnt) println("NO")
            else println("YES")
        }
    }
}

fun main() {
    소병희_사과나무.solve()
}