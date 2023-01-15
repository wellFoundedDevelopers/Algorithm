package jimin.`18week`

/*
<문제>
[레이저빔은 어디로](https://www.acmicpc.net/problem/3709)

<구현 방법>
거울과 레이저를 입력받고, 레이저를 보고 방향을 알아 d를 구한다.
d 방향으로 한칸씩 이동하면서 거울을 만나면 오른쪽 방향으로 회전한다.

<트러블 슈팅>
*/

fun main() {
    val m = readln().toInt()
    val dx = mutableListOf(-1,0,1,0)
    val dy = mutableListOf(0,1,0,-1)
    var d = 0
    repeat(m) {
        val (n, r) = readln().split(" ").map { it.toInt() }
        val mirrors = mutableListOf<Pair<Int,Int>>()
        repeat(r) {
            readln().split(" ").map { it.toInt() }.apply{
                mirrors.add(Pair(first(), last()))
            }
        }
        var (x, y) = readln().split(" ").map { it.toInt() }
        if (x == 0) {
            x = 1
            d = 2
        } else if (x == n + 1) {
            x = n
            d = 0
        } else if (y == 0) {
            y = 1
            d = 1
        } else if (y == n + 1) {
            y = n
            d = 3
        }
        while(x in 1 .. n && y in 1 .. n) {
            if (Pair(x, y) in mirrors) {
                d = (d + 1) % 4
            }
            x += dx[d]
            y += dy[d]
        }
        println("$x $y")
    }
}