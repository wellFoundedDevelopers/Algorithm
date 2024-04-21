package byeonghee.week59

import java.util.*

class 소병희_택배배송 {
    companion object {
        data class Node(val i: Int, val v: Int)

        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val dist = IntArray(n) { m * 1000 }
            val edges = Array(n) { ArrayList<Node>() }
            val pq = PriorityQueue<Node>(m + 2) { a, b -> (a.v- b.v) }

            var a = 0
            var b = 0
            var c = 0
            repeat(m) {
                readLine().split(" ").let {
                    a = it[0].toInt() - 1
                    b = it[1].toInt() - 1
                    c = it[2].toInt()
                    edges[a].add(Node(b, c))
                    edges[b].add(Node(a, c))
                }
            }

            pq.add(Node(0, 0))
            while(pq.isNotEmpty()) {
                val (i, v) = pq.poll()
                if (dist[i] <= v) continue

                dist[i] = v
                edges[i].forEach { x ->
                    if (dist[x.i] > v + x.v)
                        pq.add(Node(x.i, x.v + v))
                }
            }

            println(dist[n-1])
        }

    }
}

fun main() {
    소병희_택배배송.solve()
}