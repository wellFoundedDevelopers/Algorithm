package jimin.`34week`

/*
<문제>
[저울](https://www.acmicpc.net/problem/10159)

<구현 방법>
[i][j]가 H라는 의미는 i > j이고, L는 i < j 이고, F는 초기값 상태이다.
[i][j]가 H라면 [j][k]가 H인 것인 [i][k]도 H가 되기 때문에 이 성질을 이용해 풀어주었다.

<트러블 슈팅>
안되길래 4중 for문을 했다..
*/

class 저울 {
    fun solve() {
        val n = readln().toInt()
        val m = readln().toInt()
        val weights = MutableList(n) { MutableList(n) { F } }
        repeat(m) {
            readln().split(" ").map { it.toInt() }.apply {
                weights[first() - 1][last() - 1] = H
                weights[last() - 1][first() - 1] = L
            }
        }

        repeat(n) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (i != j) {
                        for (k in 0 until n) {
                            if (weights[i][j] == H) {
                                if (weights[j][k] == H) {
                                    weights[i][k] = H
                                }
                            } else if (weights[i][j] == L) {
                                if (weights[j][k] == L) {
                                    weights[i][k] = L
                                }
                            }
                        }
                    }
                }
            }
        }

        weights.forEach { weight ->
            println(weight.count { it == F } - 1)
        }
    }

    companion object {
        const val H = 1
        const val F = 0
        const val L = -1
    }
}

fun main() {
    저울().solve()
}