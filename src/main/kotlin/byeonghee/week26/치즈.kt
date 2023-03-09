package byeonghee.week26

import java.io.StreamTokenizer

class 소병희_치즈 {

    companion object {
        const val AIR = 0
        const val CHEESE = 1

        val dr = arrayOf(1, 0, -1, 0)
        val dc = arrayOf(0, 1, 0, -1)

        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {

            fun input() : Int {
                nextToken()
                return nval.toInt()
            }

            val h = input()
            val w = input()
            val plane = Array(h) { IntArray(w) }

            val q = ArrayList<Pair<Int, Int>>()
            var cheese = 0
            var record = 0
            var timeCost = 0

            for(r in 0 until h) for(c in 0 until w) {
                plane[r][c] = input()
                if (plane[r][c] == CHEESE) cheese++
            }

            var r = 0
            var c = 0
            var nr = 0
            var nc = 0
            while(cheese > 0) {
                val visited = Array(h) { BooleanArray(w) }
                record = cheese
                q.add(Pair(0, 0))
                visited[0][0] = true

                while(q.isNotEmpty()) {
                    q.removeFirst().let {
                        r = it.first
                        c = it.second
                    }

                    for(d in 0 until 4) {
                        nr = r + dr[d]
                        nc = c + dc[d]

                        if (nr !in 0 until h || nc !in 0 until w) continue
                        if (visited[nr][nc]) continue

                        if (plane[nr][nc] == CHEESE) {
                            plane[nr][nc] = AIR
                            cheese--
                        }
                        else q.add(Pair(nr, nc))
                        visited[nr][nc] = true
                    }
                }
                timeCost++
            }

            println("$timeCost\n$record")
        }
    }
}

fun main() {
    소병희_치즈.solve()
}