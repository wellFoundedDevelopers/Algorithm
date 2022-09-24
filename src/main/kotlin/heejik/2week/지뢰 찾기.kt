package heejik.`2week`

import java.lang.StringBuilder
import kotlin.properties.Delegates

val dx = intArrayOf(1, 1, 1, 0, 0, -1, -1, -1)
val dy = intArrayOf(1, 0, -1, 1, -1, 1, 0, -1)
var n by Delegates.notNull<Int>()

fun main() {

    n = readln().toInt()
    var isEnd = false
    val mineLocation = arrayListOf<String>()
    val nowLocation = arrayListOf<String>()
    val answerLocation = arrayListOf<StringBuilder>()

    repeat(n) {
        mineLocation.add(readln())
    }

    repeat(n) {
        nowLocation.add(readln())
    }

    repeat(n) {
        answerLocation.add(StringBuilder())
    }


    nowLocation.forEachIndexed { i, s ->
        nowLocation[i].forEachIndexed { j, c ->
            when (c) {
                'x' -> {
                    if (mineLocation[i][j] == '*' ){
                        answerLocation[i].append("*")
                        isEnd = true
                    }
                    else {
                        answerLocation[i].append(findMine(i, j, mineLocation).toString())
                    }
                }
                else -> {
                    answerLocation[i].append(c.toString())
                }
            }
        }
    }
    if (isEnd) {
        nowLocation.forEachIndexed { i, s ->
            nowLocation[i].forEachIndexed { j, c ->
                if (mineLocation[i][j] == '*') {
                    answerLocation[i][j] = '*'
                }
            }
        }
    }



    answerLocation.forEach {
        println(it.toString())
    }
}

fun findMine(x: Int, y: Int, mineLocation: ArrayList<String>): Int {
    var cnt = 0
    for (i in dx.indices) {
        val nx = x + dx[i]
        val ny = y + dy[i]
        if (nx in 0 until n && ny in 0 until n) {
            if (mineLocation[nx][ny] == '*') {
                cnt += 1
            }
        }
    }
    return cnt
}
