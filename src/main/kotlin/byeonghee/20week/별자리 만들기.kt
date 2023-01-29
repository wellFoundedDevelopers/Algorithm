package byeonghee.`20week`

import java.io.*
import java.util.PriorityQueue
import kotlin.math.*

class 소병희_별자리만들기 {

    companion object {
        data class Edge(val s: Int, val e: Int, val d: Double)

        var n = 0
        lateinit var stars : Array<List<Double>>
        lateinit var parent : IntArray

        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            n = readLine().toInt()
            stars = Array(n) { readLine().split(" ").map { it.toDouble() } }
            parent = IntArray(n) { it }

            val pq = PriorityQueue(Comparator<Edge> { a, b -> (a.d - b.d).toInt() })
            var answer = 0.0

            for(s in 0 until n - 1) for(e in s + 1 until n) {
                pq.add(Edge(s, e, findDistance(s, e)))
            }

            while(pq.isNotEmpty()) {
                val (s, e, d) = pq.poll()
                val sParent = findParent(s)
                val eParent = findParent(e)
                if (sParent == eParent) continue
                if (sParent < eParent) updateParent(parent[s], parent[e])
                else updateParent(parent[e], parent[s])

                answer += sqrt(d)
            }

            println(String.format("%.2f", answer))
        }

        fun findDistance(s: Int, e: Int) : Double {
            return (stars[s][0] - stars[e][0]).pow(2) + (stars[s][1] - stars[e][1]).pow(2)
        }

        fun findParent(target: Int) : Int {
            var curIdx = target
            while(curIdx != parent[curIdx])
                curIdx = parent[curIdx]
            return curIdx
        }

        fun updateParent(goal: Int, target: Int) {
            for(i in 0 until n) {
                if (parent[i] == target) parent[i] = goal
            }
        }
    }
}

fun main() {
    소병희_별자리만들기.solve()
}