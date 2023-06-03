package jimin.`35week`

/*
<문제>
[토마토](https://www.acmicpc.net/problem/7569)

<구현 방법>
토마토 상자를 3차원으로 하여 bfs를 돌렸다.
이때 토마토 상자를 입력받을 때 beforeTomato 변수를 만들어
토마토가 다 익었는지 판별하였다.

<트러블 슈팅>
bfs 돌기전에 beforeTomato가 0이면 0을 출력하게 했는데 틀렸다. 왜지?

*/


import java.lang.Integer.max

class 토마토 {
    private val boxes = mutableListOf<MutableList<MutableList<Int>>>()
    private val dx = mutableListOf(0, 0, 1, -1, 0, 0)
    private val dy = mutableListOf(-1, 1, 0, 0, 0, 0)
    private val dh = mutableListOf(0, 0, 0, 0, 1, -1)
    private var beforeTomato = 0

    fun solve() {
        val (n, m, h) = readln().split(" ").map { it.toInt() }

        val afterTomatoes = mutableListOf<List<Int>>()

        repeat(h) { hh ->
            val singleBox = mutableListOf<MutableList<Int>>()
            repeat(m) { mm ->
                readln().split(" ").map { it.toInt() }.apply {
                    singleBox.add(this as MutableList)
                    this.forEachIndexed { nn, tomato ->
                        when (tomato) {
                            BEFORE -> beforeTomato += 1
                            AFTER -> afterTomatoes.add(listOf(hh, mm, nn, 0))
                        }
                    }
                }
            }
            boxes.add(singleBox)
        }

//        if(beforeTomato == 0) {
//            println(0)
//        } 왜 이걸쓰면 틀리지?

        val time = bfs(afterTomatoes, m, n, h)

        if (beforeTomato > 0) {
            println(-1)
        } else {
            println(time)
        }

    }

    fun bfs(afterTomatoes: MutableList<List<Int>>, m: Int, n: Int, h: Int): Int {
        val queue = ArrayDeque<List<Int>>()
        afterTomatoes.forEach {
            queue.add(it)
        }
        var maxTime = 0

        while (queue.isNotEmpty()) {
            val (nH, nM, nN, nNum) = queue.removeFirst()

            for (i in 0 until 6) {
                if (nH + dh[i] in 0 until h && nM + dx[i] in 0 until m && nN + dy[i] in 0 until n &&
                        boxes[nH + dh[i]][nM + dx[i]][nN + dy[i]] == BEFORE) {
                    boxes[nH + dh[i]][nM + dx[i]][nN + dy[i]] = AFTER
                    queue.addLast(listOf(nH + dh[i], nM + dx[i], nN + dy[i], nNum + 1))
                    maxTime = max(maxTime, nNum + 1)
                    beforeTomato -= 1
                }
            }
        }

        return maxTime
    }

    companion object {
        const val BEFORE = 0
        const val AFTER = 1
    }
}

fun main() {
    토마토().solve()
}