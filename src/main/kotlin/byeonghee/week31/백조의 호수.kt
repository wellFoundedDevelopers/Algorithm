package byeonghee.week31

import java.io.*

class 소병희_백조의호수 {

    companion object {
        const val WATER = 0
        const val ICE = -1

        val dr = intArrayOf(-1, 0, 1, 0)
        val dc = intArrayOf(0, 1, 0, -1)

        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val lake = Array(n) { IntArray(m) }
            val time = Array(n) { IntArray(m) }
            val area = ArrayDeque<Int>().apply { add(0) }
            val water = ArrayDeque<IntArray>()
            val ice = ArrayDeque<IntArray>()
            val visited = Array(n) { BooleanArray(m) }
            var areaCnt = 1
            var nr = 0
            var nc = 0

            for(i in 0 until n) {
                readLine().forEachIndexed { j, v ->
                    lake[i][j] = when(v) {
                        '.' -> WATER
                        'X' -> ICE
                        else -> {
                            area.add(areaCnt)
                            areaCnt++
                        }
                    }
                }
            }

            fun getParent(v: Int): Int {
                var p = v
                while(area[p] != p) {
                    p = area[p]
                }
                return p
            }

            for(i in 0 until n) for(j in 0 until m) {
                if (visited[i][j] || lake[i][j] == ICE) continue

                if (lake[i][j] == WATER) {
                    lake[i][j] = areaCnt
                    area.add(areaCnt++)
                }
                water.add(intArrayOf(i, j, lake[i][j]))
                visited[i][j] = true

                while(water.isNotEmpty()) {
                    val (r, c, a) = water.removeFirst()

                    for(d in 0 until 4) {
                        nr = r + dr[d]
                        nc = c + dc[d]
                        if (nr !in 0 until n || nc !in 0 until m) continue
                        if (visited[nr][nc]) continue

                        visited[nr][nc] = true
                        if (lake[nr][nc] == ICE) {
                            lake[nr][nc] = a
                            time[nr][nc] = 1
                            ice.add(intArrayOf(nr, nc, a))
                        }
                        else  {
                            if (lake[nr][nc] == WATER) lake[nr][nc] = a
                            else if (lake[nr][nc] < a) {
                                area[a] = lake[nr][nc]
                            }
                            water.add(intArrayOf(nr, nc, getParent(a)))
                        }
                    }
                }
            }

            while(ice.isNotEmpty()) {
                val (r, c, a) = ice.removeFirst()
                val curA = getParent(a)

                for(d in 0 until 4) {
                    nr = r + dr[d]
                    nc = c + dc[d]
                    if (nr !in 0 until n || nc !in 0 until m) continue
                    if(lake[nr][nc] == ICE) {
                        lake[nr][nc] = curA
                        time[nr][nc] = time[r][c] + 1
                        ice.add(intArrayOf(nr, nc, curA))
                        continue
                    }

                    val preA = getParent(lake[nr][nc])
                    if (preA == curA) continue
                    if ((preA == 1 && curA == 2)||(preA == 2 && curA == 1)) {
                        println(time[r][c].coerceAtLeast(time[nr][nc]))
                        return
                    }
                    else if (preA < curA) {
                        area[curA] = preA
                    }
                    else area[preA] = curA
                }
            }
        }
    }
}

fun main() {
    소병희_백조의호수.solve()
}