package byeonghee.week52

class 소병희_풀링 {
    companion object {
        data class Pos(val r: Int, val c: Int)

        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val conv = Array(n) { IntArray(n) }

            repeat(n) { i ->
                readLine().split(" ").forEachIndexed { j, v ->
                    conv[i][j] = v.toInt()
                }
            }

            fun getSecondPos(p1: Pos, p2: Pos, p3: Pos, p4: Pos): Pos {
                val v1 = conv[p1.r][p1.c]
                val v2 = conv[p2.r][p2.c]
                val v3 = conv[p3.r][p3.c]
                val v4 = conv[p4.r][p4.c]

                val l1 = maxOf(v1, v2)
                val l2 = maxOf(v3, v4)
                val ll = maxOf(l1, l2)

                return when(ll) {
                    v1 -> if (v2 > l2) p2 else if (v3 > v4) p3 else p4
                    v2 -> if (v1 > l2) p1 else if (v3 > v4) p3 else p4
                    v3 -> if (v4 > l1) p4 else if (v1 > v2) p1 else p2
                    else -> if (v3 > l1) p3 else if (v1 > v2) p1 else p2
                }
            }

            fun recursive(p: Pos, len: Int): Pos {
                if (len == 2) {
                    return getSecondPos(p,
                            Pos(p.r, p.c+1),
                            Pos(p.r+1, p.c),
                            Pos(p.r+1, p.c+1)
                    )
                }

                val nlen = len / 2
                val p1 = recursive(p, nlen)
                val p2 = recursive(Pos(p.r, p.c + nlen), nlen)
                val p3 = recursive(Pos(p.r + nlen, p.c), nlen)
                val p4 = recursive(Pos(p.r + nlen, p.c + nlen), nlen)

                return getSecondPos(p1, p2, p3, p4)
            }

            println(recursive(Pos(0, 0), n).let { conv[it.r][it.c] })
        }
    }
}

fun main() {
    소병희_풀링.solve()
}