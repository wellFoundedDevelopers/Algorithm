package heejik

import java.io.BufferedReader
import java.io.InputStreamReader

fun main():Unit= with(BufferedReader(InputStreamReader(System.`in`))) {

    val dx = intArrayOf(0,0,1,1)
    val dy = intArrayOf(0,1,1,0)
    val answer = intArrayOf(0,0,0,0,0)

    val (r,c) = readln().split(' ').map { it.toInt() }
    val li = arrayListOf<String>()

    repeat(r){
        li.add(readln())
    }

    li.forEachIndexed { x, s ->
        li[x].forEachIndexed { y, q ->
            var cnt = 0
            for (i in dx.indices){
                val nx = x+dx[i]
                val ny = y+dy[i]
                if (nx in 0 until r && ny in 0 until c){
                    if (li[nx][ny] == '#') {
                        cnt = -1
                        break
                    }
                    else if (li[nx][ny] == 'X') cnt +=1
                }
                else {
                    cnt = -1
                    break
                }
            }
            if (cnt!= -1) answer[cnt] += 1
        }
    }
    answer.forEach { println(it) }
}
