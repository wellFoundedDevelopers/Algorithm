package byeonghee.week52

class 소병희_종이의개수 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val paper = Array(n) { IntArray(n) }
            val ans = IntArray(3)

            repeat(n) { i ->
                readLine().split(" ").forEachIndexed { j, v ->
                    paper[i][j] = v.toInt()
                }
            }

            fun recursive(r: Int, c: Int, size: Int): Int {
                if (size == 1) return paper[r][c] + 1

                val curAns = IntArray(3)
                val nsize = size / 3

                for(nr in r until r + size step nsize) {
                    for(nc in c until c + size step nsize) {
                        val ret = recursive(nr, nc, nsize)
                        if (ret >= 0) curAns[ret]++
                    }
                }

                repeat(3) { x ->
                    if (curAns[x] == 9)
                        return x
                    else ans[x] += curAns[x]
                }
                return -1
            }

            val ret = recursive(0, 0, n)
            if (ret >= 0) ans[ret]++

            println(ans.joinToString("\n"))
        }
    }
}

fun main() {
    소병희_종이의개수.solve()
}