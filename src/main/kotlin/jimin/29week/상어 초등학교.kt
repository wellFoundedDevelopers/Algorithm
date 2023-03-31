package jimin.`29week`

/*
<문제>
[상어 초등학교](https://www.acmicpc.net/problem/21608)

<구현 방법>
학생이 좋아하는 학생들 번호를 입력받으면서 setSeat() 함수를 이용해 자리를 구현하였다.
for문을 돌면서 동서남북을 보면서 like한 친구들 개수, empty한 자리 수를 구해서 기존 보다 크면 바꿔주었다.
어짜피 행 -> 열 순으로 for문을 돌기때문에 3번은 고려하지 않아도 된다.
자리를 만들어준 후 다시 for문을 돌면서 만족도를 구했다.

<트러블 슈팅>
초기화는 -1로 하자
*/

import kotlin.math.*

class `상어 초등학교` {
    private lateinit var seats: MutableList<MutableList<Int>>
    private val dx = mutableListOf(0, 0, -1, 1)
    private val dy = mutableListOf(1, -1, 0, 0)
    fun solve() {
        val n = readln().toInt()
        seats = MutableList(n) { MutableList(n) { 0 } }
        val students = mutableListOf<List<Int>>()
        var result = 0
        repeat(n * n) {
            var student = 0
            lateinit var likes: List<Int>
            readln().split(" ").map { it.toInt() }.apply {
                student = first()
                likes = this.drop(1)
                students.add(this)
            }

            setSeat(n, student, likes)
        }

        students.sortBy { it[0] }

        for (i in 0 until n) {
            for (j in 0 until n) {
                var num = 0
                for (k in 0 until 4) {
                    if (i + dx[k] in 0 until n && j + dy[k] in 0 until n &&
                            seats[i + dx[k]][j + dy[k]] in students[seats[i][j] - 1]){
                        num += 1
                    }
                }
                result += if (num == 0) 0 else 10.0.pow(num - 1).toInt()
            }
        }

        println(result)
    }

    private fun setSeat(n: Int, student: Int, likes: List<Int>) {
        var bestNum = Pair(-1, -1)
        var bestSeat = Pair(-1, -1)
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (seats[i][j] == EMPTY) {
                    var likeNum = 0
                    var emptyNum = 0
                    for (k in 0 until 4) {
                        if (i + dx[k] in 0 until n && j + dy[k] in 0 until n) {
                            if (seats[i + dx[k]][j + dy[k]] in likes) {
                                likeNum++
                            } else if (seats[i + dx[k]][j + dy[k]] == EMPTY) {
                                emptyNum++
                            }
                        }
                    }
                    if (bestNum.first < likeNum || (bestNum.first == likeNum && bestNum.second < emptyNum)) {
                        bestNum = Pair(likeNum, emptyNum)
                        bestSeat = Pair(i, j)
                    }
                }
            }
        }
        seats[bestSeat.first][bestSeat.second] = student
    }

    companion object {
        private const val EMPTY = 0
    }
}

fun main() {
    `상어 초등학교`().solve()
}