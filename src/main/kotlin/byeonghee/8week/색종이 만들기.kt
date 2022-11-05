package byeonghee.`8week`

import java.io.*

class `소병희_색종이 만들기` {

    data class Pos(val r: Int, val c: Int)

    val br = BufferedReader(InputStreamReader(System.`in`))
    lateinit var paper : Array<IntArray>
    var white = 0
    var blue = 0

    fun solution() {
        val n = br.readLine().toInt()
        paper = Array(n) { br.readLine().split(" ").map{ it.toInt() }.toIntArray() }

        quadTree(Pos(0, 0), n)
        println(white)
        println(blue)
    }

    fun quadTree(p: Pos, l: Int) {
        var count = 0
        for(r in p.r until p.r + l) for(c in p.c until p.c + l) {
            if (paper[r][c] == 0) count++
        }
        if (count == l * l) {
            white++
            return
        }
        else if (count == 0) {
            blue++
            return
        }

        val nxtL = l / 2
        quadTree(p, nxtL)
        quadTree(Pos(p.r + nxtL, p.c), nxtL)
        quadTree(Pos(p.r, p.c + nxtL), nxtL)
        quadTree(Pos(p.r + nxtL, p.c + nxtL), nxtL)
    }
}

fun main() {
    `소병희_색종이 만들기`().solution()
}