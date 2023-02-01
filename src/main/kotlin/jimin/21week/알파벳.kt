package jimin.`21week`

/*
<문제>
[알파벳](https://www.acmicpc.net/problem/1244)

<구현 방법>
dfs를 이용하였다.
이때 visitedAlphabet을 26개의 크기의 리스트로 만들어주어
알파벳을 방문했는지를 index로 찾을 수 있게끔 하였다.

<트러블 슈팅>
처음에는 visitedAlphabet에 방문한 알파벳을 추가하고
in을 이용해 방문했는지 판별하였더니 시간초과가 떴다.
그래서 visitedAlhabet을 26개의 정해진 크기로 만들고 index로 바로 접근해
방문했는지 boolean으로 판별하니 해결되었다.
*/

import java.lang.Integer.max

class 알파벳 {
    private val dx = mutableListOf(0, 0, 1, -1)
    private val dy = mutableListOf(1, -1, 0, 0)
    private var result = 1
    private lateinit var board: MutableList<MutableList<Char>>
    private val ASCII_A = 65

    fun solve() {
        val (c, r) = readln().split(" ").map { it.toInt() }
        board = MutableList(c + 2) { MutableList(r + 2) { '.' } }
        for (i in 0 until c) {
            readln().forEachIndexed { j, alphabet ->
                board[i + 1][j + 1] = alphabet
            }
        }
        dfs(1, 1, 1, MutableList(26) { false })
        println(result)
    }

    fun dfs(x: Int, y: Int, sum: Int, visitedAlphabet: MutableList<Boolean>) {
        visitedAlphabet[board[x][y].code - ASCII_A] = true
        result = max(result, sum)

        for (i in 0 until 4) {
            if (board[x + dx[i]][y + dy[i]] != '.' && visitedAlphabet[board[x + dx[i]][y + dy[i]].code - ASCII_A].not()) {
                dfs(x + dx[i], y + dy[i], sum + 1, visitedAlphabet.toMutableList())
            }
        }
    }
}

fun main() {
    알파벳().solve()
}