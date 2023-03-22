package byeonghee.week28

import java.io.StreamTokenizer

class 소병희_미세먼지안녕 {

    companion object {
        val dr = arrayOf(-1, 0, 1, 0)
        val dc = arrayOf(0, 1, 0, -1)

        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
            fun input(): Int {
                nextToken()
                return nval.toInt()
            }

            val r = input()
            val c = input()
            val t = input()
            var purifier = 0
            val rRange = Array(2) { 0 .. 0 }
            val room = Array(r) { IntArray(c) { input() } }
            val nroom = Array(r) { IntArray(c) }
            val iniroom = Array(r) { IntArray(c) }

            for(i in 0 until r) {
                if(room[i][0] == -1) {
                    purifier = i
                    room[i][0] = 0
                    room[i + 1][0] = 0
                    rRange[0] = 0 .. i
                    rRange[1] = i + 1 until r
                    break
                }
            }

            fun copy(a: Array<IntArray>, b: Array<IntArray>) {
                for(i in 0 until r) for(j in 0 until c) {
                    a[i][j] = b[i][j]
                }
            }

            var spreadCnt : Int
            var spreadAmt : Int
            var ni = 0
            var nj = 0
            var flip = 1

            repeat(t) {
                copy(nroom, iniroom)
                for(i in 0 until r) for(j in 0 until c) {
                    if (room[i][j] <= 0) continue

                    spreadCnt = 0
                    spreadAmt = room[i][j] / 5
                    for(d in 0 until 4) {
                        ni = i + dr[d]
                        nj = j + dc[d]
                        if (ni !in 0 until r || nj !in 0 until c) continue
                        if (nj == 0 && ni in purifier .. purifier + 1) continue
                        spreadCnt++
                        nroom[ni][nj] += spreadAmt
                    }
                    nroom[i][j] += room[i][j] - spreadCnt * spreadAmt
                }

                for(p in 0 .. 1) {
                    ni = purifier + p
                    nj = 0
                    for(d in 0 until 4) {
                        while(ni + dr[d] * flip in rRange[p] && nj + dc[d] in 0 until c) {
                            nroom[ni][nj] = nroom[ni + dr[d] * flip][nj + dc[d]]
                            ni += dr[d] * flip
                            nj += dc[d]
                        }
                    }
                    nroom[purifier + p][1] = 0
                    nroom[purifier + p][0] = 0
                    flip *= -1
                }

                copy(room, nroom)
            }

            var answer = 0
            for(i in 0 until r) for(j in 0 until c) {
                answer += room[i][j]
            }
            print(answer)
        }
    }
}

fun main() {
    소병희_미세먼지안녕.solve()
}