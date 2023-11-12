package byeonghee.week49

class 소병희_빗물 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val (h, _) = readLine().split(" ").map { it.toInt() }
            val blocks = Array(h+1) { ArrayDeque<Int>() }
            var water = 0

            readLine().split(" ").forEachIndexed { i, v ->
                for(j in 1 .. v.toInt()) {
                    blocks[j].add(i)
                }
            }

            for(i in 1 .. h) {
                if (blocks[i].size < 2) break
                water += blocks[i].last() - blocks[i].first() - blocks[i].size + 1
            }

            println(water)
        }
    }
}

fun main() {
    소병희_빗물.solve()
}