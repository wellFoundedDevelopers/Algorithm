package heejik.`3week`

val dx = listOf(1,1,1,0,0,-1,-1,-1)
val dy = listOf(1,-1,0,1,-1,1,-1,0)
val banner = arrayListOf<MutableList<Int>>()
var m = 0
var n = 0
fun main() {
    var answer = 0
    readln().split(' ').map { it.toInt() }.run {
        m = this[0]
        n = this[1]
    }


    repeat(m) {
        banner.add(readln().split(' ').map { it.toInt() } as MutableList<Int>)
    }

    for (i in 0 until m) {
        for (j in 0 until n) {
            if (banner[i][j] == 1) {
                bfs(i,j)
                answer += 1
            }
        }
    }
    println(answer)
}

fun bfs(x:Int, y:Int) {
    val queue = ArrayDeque<Pair<Int,Int>>()
    queue.add(Pair(x,y))
    while (queue.isNotEmpty()) {
        val (x, y) = queue.removeFirst()
        banner[x][y] = 0
        for (i in 0..7){
            val nx = x+dx[i]
            val ny = y+dy[i]
            if (nx in 0 until m && ny in 0 until n){
                if (banner[nx][ny] == 1){
                    banner[nx][ny] = 0
                    queue.add(Pair(nx,ny))
                }
            }
        }
    }
}