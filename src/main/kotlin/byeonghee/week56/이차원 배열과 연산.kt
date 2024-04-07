package byeonghee.week56

import java.util.PriorityQueue

class 소병희_이차원배열과연산 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val (r, c, k) = readLine().split(" ").map { it.toInt() }
            val arr = Array(100) { IntArray(100) }
            val counts = IntArray(101)
            val pq = PriorityQueue<IntArray> { a, b -> if (a[1] == b[1]) a[0] - b[0] else a[1] - b[1] }
            var maxR = 3
            var maxC = 3

            repeat(3) { i ->
                readLine().split(" ").forEachIndexed { j, v -> arr[i][j] = v.toInt() }
            }

            if (arr[r-1][c-1] == k) {
                println(0)
                return@with
            }

            for(time in 1 .. 100) {
                if (maxR >= maxC) {
                    maxC = 0
                    for(i in 0 until maxR) {
                        counts.fill(0)
                        pq.clear()

                        for (v in arr[i]) {
                            if (v != 0) {
                                pq.add(intArrayOf(v, ++counts[v]))
                            }
                        }

                        var j = 0
                        while(pq.isNotEmpty()) {
                            val (v, t) = pq.poll()
                            if (counts[v] == 0 || counts[v] != t) continue
                            counts[v] = 0
                            arr[i][j++] = v
                            arr[i][j++] = t
                        }
                        maxC = maxOf(maxC, j)
                        arr[i].fill(0, j)
                    }
                }
                else {
                    maxR = 0
                    for(j in 0 until maxC) {
                        counts.fill(0)
                        pq.clear()

                        for (i in 0 until 100) {
                            if (arr[i][j] != 0) {
                                pq.add(intArrayOf(arr[i][j], ++counts[arr[i][j]]))
                            }
                        }

                        var i = 0
                        while(pq.isNotEmpty()) {
                            val (v, t) = pq.poll()
                            if (counts[v] == 0 || counts[v] != t) continue
                            counts[v] = 0
                            arr[i++][j] = v
                            arr[i++][j] = t
                        }
                        maxR = maxOf(maxR, i)
                        while(i < 100) {
                            arr[i++][j] = 0
                        }
                    }
                }

                if (arr[r-1][c-1] == k) {
                    println(time)
                    return@with
                }
            }

            println(-1)
        }
    }
}

fun main() {
    소병희_이차원배열과연산.solve()
}