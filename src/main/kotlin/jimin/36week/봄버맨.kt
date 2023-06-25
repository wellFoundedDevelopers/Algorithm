package jimin.`36week`

/*
<문제>


<구현 방법>
매 시간 spentTime으로 각 폭탄의 남은 시간을 줄여주고,
it % 2 가 0이면 시간이 0이 된 폭탄을 터트리고(이때 it != 0),
it % 1 가 1이면 새로운 폭탄을 추가한다.

<트러블 슈팅>
출력할 때 0으로 했더니 틀림.. 영어 O로 해야한다!
*/

class 봄버맨 {
    private val ground = mutableListOf<MutableList<Int>>()
    private val dx = mutableListOf(0, 0, -1, 1)
    private val dy = mutableListOf(1, -1, 0, 0)
    fun solve() {
        val (r, c, n) = readln().split(" ").map { it.toInt() }

        repeat(r) {
            ground.add(readln().chunked(1).map { if (it == ".") -1 else 3 } as MutableList)
        }

        repeat(n) {
            spentTime()
            if (it != 0) {
                when (it % 2) {
                    0 -> explodeBoom(r, c)
                    1 -> putNewBoom()
                }
            }
        }

        ground.forEach { g ->
            g.forEach {
                if (it == -1) {
                    print(".")
                } else {
                    print("O")
                }
            }
            println()
        }
    }


    private fun spentTime() {
        ground.forEachIndexed { i, g ->
            g.forEachIndexed { j, it ->
                if (ground[i][j] > 0) {
                    ground[i][j] = it - 1
                }
            }
        }
    }

    private fun putNewBoom() {
        ground.forEachIndexed { i, g ->
            g.forEachIndexed { j, it ->
                if (it == -1) {
                    ground[i][j] = 3
                }
            }
        }
    }

    private fun explodeBoom(r: Int, c: Int) {
        for (i in 0 until r) {
            for (j in 0 until c) {
                if (ground[i][j] == 0) {
                    ground[i][j] = -1
                    for (k in 0 until 4) {
                        if (i + dx[k] in 0 until r && j + dy[k] in 0 until c) {
                            if (ground[i + dx[k]][j + dy[k]] != 0) {
                                ground[i + dx[k]][j + dy[k]] = -1
                            }
                        }
                    }
                }
            }
        }
    }
}


fun main() {
    봄버맨().solve()
}