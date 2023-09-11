package heejik.`44week`


/**
https://wogud6792.tistory.com/11
https://www.youtube.com/watch?v=3GsBaTqxcjM&t=12s
**/

class CCW {

    data class Vector(
        val x :Int,
        val y: Int
    )
    fun solve() {
        val (p1X, p1Y) = readln().split(' ').map { it.toInt() }
        val (p2X, p2Y) = readln().split(' ').map { it.toInt() }
        val (p3X, p3Y) = readln().split(' ').map { it.toInt() }

        val vectorP1P2 = Vector(p2X - p1X, p2Y - p1Y)
        val vectorP1P3 = Vector(p3X - p1X, p3Y - p1Y)

        (vectorP1P2.x * vectorP1P3.y - vectorP1P3.x * vectorP1P2.y).run {
            if (this < 0) print(-1)
            if (this == 0) print(0)
            if (this > 0) print(1)
        }
    }
}


fun main() {
    CCW().solve()
}