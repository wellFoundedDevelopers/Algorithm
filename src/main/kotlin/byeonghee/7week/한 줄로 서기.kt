package byeonghee.`7week`

import java.io.*

class `소병희_한 줄로 서기` {

    val br = BufferedReader(InputStreamReader(System.`in`))

    lateinit var line : IntArray

    fun solution() {
        val n = br.readLine().toInt()
        line = IntArray(n)

        br.readLine().split(" ").map{ it.toInt() }.forEachIndexed { i, v ->
            line[findPos(v)] = i + 1
        }
        println(line.joinToString(" "))
    }

    fun findPos(d: Int) : Int{
        var blanks = 0
        var p = -1

        while (blanks <= d) {
            if (line[++p] == 0) blanks++
        }

        return p
    }
}

fun main() {
    `소병희_한 줄로 서기`().solution()
}