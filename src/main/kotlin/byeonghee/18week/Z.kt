package byeonghee.`18week`

import java.io.*
import kotlin.math.pow

class 소병희_Z1 {

    companion object {
        var answer = 0
        var offset = 0

        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            var (n, r, c) = readLine().split(" ").map { it.toInt() }
            n = 2.0.pow(n.toDouble()).toInt()

            while(r > 0 || c > 0) {
                offset =
                    if (r < n) if (c < n) 0 else 1
                    else if (c < n) 2
                    else 3

                answer += n * n * offset
                r %= n
                c %= n
                n /= 2
            }

            println(answer)
        }
    }
}

class 소병희_Z2 {
    companion object {
        data class Pos(val r: Int, val c: Int) {
            operator fun plus(p: Pos) : Pos {
                return Pos(this.r + p.r, this.c + p.c)
            }

            operator fun times(i: Int) : Pos {
                return Pos(this.r * i, this.c * i)
            }

            fun isIn(l : Int) : Boolean {
                return (target.r in this.r until this.r + l) && (target.c in this.c until this.c + l)
            }
        }

        var answer = 0
        lateinit var target : Pos

        val mv = listOf(
            Pos(0, 0),
            Pos(0, 1),
            Pos(1, 0),
            Pos(1, 1)
        )

        fun solve() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val (n, r, c) = readLine().split(" ").map { it.toInt() }
            target = Pos(r, c)

            biSearch(2.0.pow(n.toDouble()).toInt(), Pos(0, 0))
            println(answer)
        }

        fun biSearch(l: Int, p: Pos) {
            if (p == target) return

            for(i in mv.indices) {
                if ((p + (mv[i] * l)).isIn(l)) {
                    answer += i * l * l
                    return biSearch(l / 2, p + (mv[i] * l))
                }
            }
        }
    }
}

fun main() {
    소병희_Z1.solve()
    소병희_Z2.solve()
}