package jimin.`20week`

/*
<문제>
[별자리 만들기](https://www.acmicpc.net/problem/4386)

<구현 방법>
우선 먼저 각 노드의 distance 정보를 starInfo에 정리해 정렬한다.
부모를 찾는 find함수를 통해 부모가 다르면 간선을 연결한다.
이때 union함수를 통해 부모를 갱신해준다.

<트러블 슈팅>
처음에는 크루스칼 알고리즘을 몰라서 visited 방문 처리를 해주었는데,
그러니까 안되는 예외 사항이 많아서 결국 구글링을 통해 해결해주었다..^!^
크루스칼 알고리즘 https://chanhuiseok.github.io/posts/algo-33/
문제 풀이 방법 https://velog.io/@jini_eun/%EB%B0%B1%EC%A4%80-4386%EB%B2%88-%EB%B3%84%EC%9E%90%EB%A6%AC-%EB%A7%8C%EB%93%A4%EA%B8%B0-Java-Python
*/

import java.lang.Math.sqrt
import kotlin.math.pow

class `별자리 만들기` {
    var n = 0
    var starInfo = mutableListOf<StarInfo>()
    lateinit var parent: MutableList<Int>
    fun solve() {
        n = readln().toInt()
        val stars = mutableListOf<Point>()
        parent = MutableList(n){ it }
        repeat(n) {
            readln().split(" ").map { it.toDouble() }.apply {
                stars.add(Point(first(), last()))
            }
        }
        for (i in 0 until n - 1) for (j in i + 1 until n) {
            starInfo.add(StarInfo(sqrt((stars[i].x - stars[j].x).pow(2) + (stars[i].y - stars[j].y).pow(2)), i, j))
        }
        starInfo.sortBy{ it.distance }
        var result = 0.0
        starInfo.forEach { star ->
            if (find(star.x) != find(star.y)) {
                union(star.x, star.y)
                result += star.distance
            }
        }

        println(String.format("%.2f", result))
    }

    private fun find(x: Int): Int {
        if (x != parent[x]) {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }

    private fun union(x:Int, y: Int) {
        val xx = find(x)
        val yy = find(y)

        if (xx == yy) {
            return
        }else if (xx < yy) {
            parent[yy] = xx
        } else {
            parent[xx] = yy
        }
    }

    data class Point(
        val x: Double,
        val y: Double
    )

    data class StarInfo(
        val distance: Double,
        val x: Int,
        val y: Int
    )
}

fun main() {
    `별자리 만들기`().solve()
}