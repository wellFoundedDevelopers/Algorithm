package jimin.`7week`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.system.exitProcess

/*
<문제>

<구현 방법>
우선, 웅덩이 시작점을 기준으로 정렬함.
그리고 poolInfo를 돌면서 웅덩이를 덮을 만큼의 판자 개수를 구해서 더해줌.
이때 이미 웅덩이에 판자가 걸쳐져있다면 index를 기준으로 length를 세줌.
근데 웅덩이가 겹쳐져있는 경우가 있을 수도 있으니 예외처리해줌.

<트러블 슈팅>
처음에 poolList를 만들어서 웅덩이 정보를 더 넣어줬는데 이러니까 NZEC라는 런타임 에러가 뜸..
 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, l) = readLine().split(" ").map { it.toInt() }
    val poolInfo = mutableListOf<Pair<Int, Int>>()
    repeat(n) {
        val (p1, p2) = readLine().split(" ").map { it.toInt() }
        poolInfo.add(Pair(p1, p2))
    }
    poolInfo.sortWith(compareBy { it.first })
    var boardNum = 0
    var index = 0
    poolInfo.forEach { pool ->
        if (index < pool.first){
            val length = pool.second - pool.first
            val newBoard= if (length % l == 0 ) length / l else length / l + 1
            index = pool.first + newBoard * l
            boardNum += newBoard
        } else if (index > pool.second) {
            return@forEach
        } else {
            val length = pool.second - index
            val newBoard= if (length % l == 0 ) length / l else length / l + 1
            index = index + newBoard * l
            boardNum += newBoard
        }
    }
    println(boardNum)

}
