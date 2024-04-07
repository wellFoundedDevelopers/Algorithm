package byeonghee.week56

import kotlin.math.*

class 소병희_루돌프의반란 {
    companion object {
        const val R = 0
        const val C = 1
        const val SCORE = 2
        const val STATUS = 3

        const val OUT = -1

        const val INF = 2L * 50 * 50

        val dr = intArrayOf(1, 1, 1, 0, 0, -1, -1, -1, 0)
        val dc = intArrayOf(1, 0, -1, 1, -1, 1, 0, -1, 0)
        val sdi = intArrayOf(1, 3, 4, 6)
        val rdi = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7)

        fun solve() = with(System.`in`.bufferedReader()) {
            val (n, m, p, c, d) = readLine().split(" ").map { it.toInt() }
            var (rr, rc) = readLine().split(" ").map { it.toInt() - 1 }
            val santa = Array(p+1)  { IntArray(4) } // 점수, 상태(1=기절,2=탈락)
            val board = Array(n) { IntArray(n) }

            fun calcDist(pi: Int, sign: Int, d: Int = 8): Long {
                return hypot((santa[pi][R] + sign * dr[d] - rr).toDouble(), (santa[pi][C] + sign * dc[d] - rc).toDouble()).toLong()
            }

            fun selectSanta(): Int {
                var ret = 0
                var dist = INF
                for(pi in 2..p) {
                    if (santa[pi][STATUS] != OUT) {
                        val d = calcDist(pi, -1)
                        if (d > dist) continue
                        if (d == dist && santa[ret][R] > santa[pi][R]) continue
                        if (d == dist && santa[ret][C] > santa[pi][C]) continue

                        ret = pi
                        dist = d
                    }
                }
                return ret
            }

            fun selectDir(target: Int, range: IntArray, imSanta: Int) : Int {
                var dist = calcDist(target, imSanta)
                var nd = 8
                for(d in range) {
                    val tmp = calcDist(target, imSanta, d)
                    if (imSanta == 1 && santa[target][R] + dr[d] in 0 until n && santa[target][C] + dc[d] in 0 until n
                            && board[santa[target][R] + dr[d]][santa[target][C] + dc[d]] > 0) {
                        continue
                    }
                    else if (dist > tmp) {
                        dist = tmp
                        nd = d
                    }
                }
                return nd
            }

            fun stunAndCrash(_pi: Int, score: Int, stuned: Int, d: Int, w: Int) {
                var pi = _pi
                var pr = santa[pi][R]
                var pc = santa[pi][C]

                santa[pi][SCORE] += score
                santa[pi][STATUS] = stuned + 1

                board[pr][pc] = 0
                pr += score * dr[d] * w
                pc += score * dc[d] * w

                if (pr !in 0 until n || pc !in 0 until n) {
                    santa[pi][STATUS] = OUT
                    return
                }

                while(board[pr][pc] > 0) {
                    var np = board[pr][pc]
                    board[pr][pc] = pi
                    santa[pi][R] = pr
                    santa[pi][C] = pc

                    pi = np
                    pr += dr[d] * w
                    pc += dc[d] * w

                    // 탈락
                    if (pr !in 0 until n || pc !in 0 until n) {
                        santa[pi][STATUS] = OUT
                        break
                    }
                }

                if (santa[pi][STATUS] != OUT) {
                    board[pr][pc] = pi
                    santa[pi][R] = pr
                    santa[pi][C] = pc
                }
            }

            repeat(p) {
                val (pi, pr, pc) = readLine().split(" ").map { it.toInt() }
                santa[pi][R] = pr - 1
                santa[pi][C] = pc - 1
                board[pr - 1][pc - 1] = pi
            }

            for(turn in 0 until m) {
                // 루돌프 이동
                //// 산타 선택
                val target = selectSanta()
                if (target == 0) break
                //// 방향 선택
                val rd = selectDir(target, rdi, -1)
                rr += dr[rd]
                rc += dc[rd]
                // 충돌 + 기절 + 상호작용
                if (board[rr][rc] > 0) {
                    stunAndCrash(board[rr][rc], c, turn, rd, 1)
                }

                // 1 ~ p 기절x 탈락x 산타 이동
                for(pi in 1..p) {
                    if (santa[pi][STATUS] == OUT) continue
                    if (turn <= santa[pi][STATUS]) continue

                    val pd = selectDir(pi, sdi, 1)
                    var pr = santa[pi][R]
                    var pc = santa[pi][C]
                    board[pr][pc] = 0
                    pr += dr[pd]
                    pc += dc[pd]
                    board[pr][pc] = pi
                    // 충돌 + 기절 + 상호작용
                    if (rr == pr && rc == pc) {
                        stunAndCrash(pi, d, turn, pd, -1)
                    }
                }

                for(pi in 1..p) {
                    if (santa[pi][STATUS] == OUT) continue
                    santa[pi][SCORE]++
                }
            }

            println(santa.joinToString(" ") { it[SCORE].toString() })
        }
    }
}

fun main() {
    소병희_루돌프의반란.solve()
}

