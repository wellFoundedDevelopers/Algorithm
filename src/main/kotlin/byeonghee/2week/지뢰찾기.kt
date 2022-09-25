package byeonghee.`2week`

import java.io.BufferedReader
import java.io.InputStreamReader

val dr = listOf(-1, -1, -1, 0, 0, 1, 1, 1)
val dc = listOf(-1, 0, 1, -1, 1, -1, 0, 1)

val mineMap = mutableListOf<String>()
val curMap = mutableListOf<MutableList<Char>>()

fun getSwipeNum(r : Int, c: Int) : Char {
    var bombN = 0
    for(d in 0 until 8) {
        if (mineMap[r+dr[d]][c+dc[d]] == '*') {
            bombN++
        }
    }
    return bombN.digitToChar()
}

// 틀림!!
fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().trim().toInt()

    mineMap.add(".".repeat(n+2))
    repeat(n) {
        mineMap.add("." + readLine().trim() + ".")
    }
    mineMap.add(".".repeat(n+2))

    repeat(n) {
        curMap.add(readLine().trim().toMutableList())
    }

    mineMap.forEach {
        println(it)
    }

    var bomb = false
    for(i in 1..n) for (j in 1..n) {
        if (curMap[i-1][j-1] == 'x') {
            if (mineMap[i][j] == '*') {
                bomb = true
                curMap[i-1][j-1] = '*'
            }
            else {
                curMap[i-1][j-1] = getSwipeNum(i, j)
            }
        }
        if (bomb && mineMap[i][j] == '*') {
            curMap[i-1][j-1] = '*'
        }
    }

    curMap.forEach {
        println(it.joinToString(""))
    }
}