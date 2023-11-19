package byeonghee.week50

class 소병희_숫자고르기 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val arr = IntArray(n + 1)
            val answer = BooleanArray(n + 1)
            val sb = StringBuilder()

            repeat(n) {
                arr[it + 1] = readLine().toInt()
            }

            for(i in 1..n) {
                val visited = BooleanArray(n + 1)
                var p = i
                while(visited[p].not()) {
                    visited[p] = true
                    p = arr[p]
                }
                if (p == i) {
                    for(j in 1..n) {
                        answer[j] = answer[j] or visited[j]
                    }
                }
            }

            var cnt = 0
            for(i in 1..n) {
                if (answer[i]) {
                    cnt++
                    sb.appendLine(i)
                }
            }
            println(cnt)
            println(sb)
        }
    }
}

fun main() {
    소병희_숫자고르기.solve()
}