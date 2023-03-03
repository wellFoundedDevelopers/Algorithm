package byeonghee.`25week`

import java.io.StreamTokenizer

class 소병희_감시 {

    companion object {
        val mv = arrayOf(
            Pos(-1, 0),
            Pos(0, 1),
            Pos(1, 0),
            Pos(0, -1)
        )

        val sights = arrayOf(
            arrayOf(),
            arrayOf(0),
            arrayOf(0, 2),
            arrayOf(0, 1),
            arrayOf(0, 1, 2),
            arrayOf(0, 1, 2, 3)
        )

        val rotations = arrayOf(
            0..0,
            0 until 4,
            0 until 2,
            0 until 4,
            0 until 4,
            0 until 1
        )

        data class Pos(val r: Int, val c: Int) {
            operator fun plus(p: Pos) : Pos {
                return Pos(r + p.r, c + p.c)
            }
        }

        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {

            fun input() : Int {
                nextToken()
                return nval.toInt()
            }

            val n = input()
            val m = input()
            val office = Array(n + 2) { IntArray(m + 2) { 6 } }
            val cctvList = mutableListOf<Pos>()
            var answer = 0

            for(r in 1 .. n) for(c in 1 .. m) {
                office[r][c] = input()
                if (office[r][c] == 0) answer++
                else if (office[r][c] != 6) cctvList.add(Pos(r, c))
            }

            var blind = answer

            fun updateSights(v: Int, _p: Pos, _cctv: Int, _rotation: Int) {
                for(sight in sights[_cctv]) {
                    var curP = _p + mv[(sight + _rotation) % 4]
                    while(office[curP.r][curP.c] != 6) {
                        if (office[curP.r][curP.c] < 0) {
                            office[curP.r][curP.c] += v
                        }
                        if (office[curP.r][curP.c] == 0) {
                            if (v == -1) office[curP.r][curP.c] += v
                            blind += v
                        }
                        curP += mv[(sight + _rotation) % 4]
                    }
                }
            }

            fun dfs(i: Int, _blind: Int) {
                if (i == cctvList.size) {
                    answer = minOf(answer, _blind)
                    return
                }

                val p = cctvList[i]
                val cctv = office[p.r][p.c]
                for(rotation in rotations[cctv]) {
                    updateSights(-1, p, cctv, rotation)
                    dfs(i + 1, blind)
                    updateSights(1, p, cctv, rotation)
                }
            }

            dfs(0, answer)
            println(answer)
        }
    }
}

fun main() {
    소병희_감시.solve()
}