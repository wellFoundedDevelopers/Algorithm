package heejik.`4week`

import java.lang.Integer.max


val dx = listOf(1,-1,0,0)
val dy = listOf(0,0,-1,1)
val li = arrayListOf<MutableList<Int>>()
var n = 0
var m = 0
var cnt_1 = 0

fun main() {
    var cnt = 0
    var size = 0

    readln().split(' ').map { it.toInt() }.run {
        n = this[0]
        m = this[1]
    }


    repeat(n) {
        li.add(readln().split(' ').map { it.toInt() }.toMutableList())
    }
    li.forEachIndexed { x, ints ->
        ints.forEachIndexed { y, _ ->
            if (li[x][y] == 1) {
                cnt_1 = 0
                dfs(x,y)
                cnt += 1
                size = max(size, cnt_1)
            }
        }
    }
    println(cnt)
    println(size)
}


fun dfs(x: Int, y: Int) {
    cnt_1 += 1
    li[x][y] = 0
    for (i in 0..3){
        val nx = x+dx[i]
        val ny = y+dy[i]
        if (nx in 0 until  n && ny in 0 until m){
            if (li[nx][ny] == 1) {
                dfs(nx, ny)
            }
        }
    }
}