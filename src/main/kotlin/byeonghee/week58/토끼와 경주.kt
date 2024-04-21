package byeonghee.week58

import java.util.*
import kotlin.collections.HashMap

class 소병희 {
    companion object {
        const val PID = 0
        const val R = 1
        const val C = 2
        const val JUMP = 3

        var N = 0
        var M = 0
        var P = 0
        var L = 0
        var K = 0
        var S  =0

        var pid = 0
        var d = 0

        lateinit var dists : HashMap<Int, Int>
        lateinit var scores : HashMap<Int, Int>
        lateinit var lastJumps : HashMap<Int, Int>
        lateinit var stk : StringTokenizer
        lateinit var rpq : PriorityQueue<IntArray>

        val dr = intArrayOf(-1, 0, 1, 0)
        val dc = intArrayOf(0, 1, 0, -1)

        val pq = PriorityQueue<IntArray>(2001,
                compareBy({ it[JUMP] }, { it[R] + it[C] }, { it[R]}, { it[C] }, { it[PID] }))

        val cmp = compareByDescending<IntArray>{ it[R] + it[C] }
                .thenComparing(compareByDescending<IntArray>{ it[R] }
                        .thenComparing(compareByDescending<IntArray>{ it[C] }
                                .thenComparing(compareByDescending<IntArray>{ it[PID] })
                        )
                )

        fun solve() = with(System.`in`.bufferedReader()) {
            val Q = readLine().toInt()

            for(q in 0 until Q) {
                stk = StringTokenizer(readLine())

                when(stk.nextToken()) {
                    "100" -> getSetReady()
                    "200" -> runTheRace()
                    "300" -> changeLength()
                    "400" -> awardBestRabbit()
                }
            }
        }

        fun input() = stk.nextToken().toInt()

        fun getSetReady() {
            N = input()
            M = input()
            P = input()

            dists = HashMap(P)
            scores = HashMap(P)
            lastJumps = HashMap(P)

            for(p in 0 until P) {
                pid = input()
                d = input()
                dists[pid] = d
                scores[pid] = 0
                pq.add(intArrayOf(pid, 0, 0, 0))
            }
        }

        fun runTheRace() {
            K = input()
            S = input()
            rpq = PriorityQueue(K, cmp)
            lastJumps.clear()

            for(k in 0 until K) {
                val (pid, r, c, jump) = pq.poll()
                var rr = -1
                var cc = -1

                for(dir in 0 until 4) {
                    var nr = r + dr[dir] * (dists[pid]!! % (2 * N))
                    var nc = c + dc[dir] * (dists[pid]!! % (2 * M))

                    while(nr !in 0 until N) {
                        if (nr < 0) nr *= -1
                        else nr = 2 * N - nr - 2
                    }

                    while(nc !in 0 until M) {
                        if (nc < 0) nc *= -1
                        else nc = 2 * M - nc - 2
                    }

                    if ((nr + nc > rr + cc)
                            || (nr + nc == rr + cc
                                    && (nr > rr || (nr == rr && nc > cc)))) {
                        rr = nr
                        cc = nc
                    }
                }

                pq.add(intArrayOf(pid, rr, cc, jump + 1))
                rpq.add(intArrayOf(pid, rr, cc, jump + 1))
                lastJumps[pid] = jump + 1
                for(x in scores.keys) {
                    if (x != pid) scores[x] = scores[x]!! + rr + cc + 2
                }
            }

            while(rpq.peek()[JUMP] != lastJumps[rpq.peek()[PID]]) rpq.poll()
            val (pid, _, _, _) = rpq.poll()
            scores[pid] = scores[pid]!! + S

            pq.forEach { it[JUMP] = 0 }
        }

        fun changeLength() {
            pid = input()
            L = input()
            dists[pid] = dists[pid]!! * L
        }

        fun awardBestRabbit() {
            println(scores.values.maxBy { it })
        }
    }
}

fun main() {
    소병희.solve()
}