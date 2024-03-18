package byeonghee.week54

class 소병희_문자열게임2 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val tc = readLine().toInt()
            val sb = StringBuilder()

            for(t in 0 until tc) {
                val letter = Array(26) { ArrayDeque<Int>(10_000)}

                readLine().forEachIndexed { i, c ->
                    letter[c - 'a'].add(i)
                }

                val k = readLine().toInt()
                var minDist = 20_000
                var maxDist = 0
                for(list in letter) {
                    if (list.size < k) continue
                    for(i in k-1 until list.size) {
                        val dist = list[i] - list[i-k+1] + 1
                        if (dist < minDist) minDist = dist
                        if (dist > maxDist) maxDist = dist
                    }
                }

                if (maxDist == 0) sb.appendLine(-1)
                else sb.appendLine("$minDist $maxDist")
            }

            println(sb)
        }
    }
}

fun main() {
    소병희_문자열게임2.solve()
}