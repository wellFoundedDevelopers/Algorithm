package jimin.`3week`

/*
<문제>
https://www.acmicpc.net/problem/5766

<구현 방법>
map을 이용해 key를 player 번호로, value를 등장한 횟수로 하여
value를 기준으로 정렬하고, 가장 큰 숫자 다음 것들만 filter한 뒤 정렬하여 출력했다.

<트러블 슈팅>
2등이 없을 수도 있다는 것을 고려해야한다.
 */

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    while (true){
        val (n, m) = readLine().trim().split(" ").map { it.toInt() }
        if (n == 0 && m == 0) break
        val playerInfo = mutableMapOf<Int, Int>()
        for (i in 0 until n) {
            readLine().trim().split(" ").map {
                if (playerInfo.containsKey(it.toInt())) {
                    playerInfo[it.toInt()] = playerInfo[it.toInt()]!! + 1
                } else {
                    playerInfo.put(it.toInt(), 1)
                }
            }
        }

        var sortedPlayerInfo = playerInfo.toList().sortedBy { it.second }.reversed()
        val secondPlayer = sortedPlayerInfo.filter { sortedPlayerInfo[0].second > it.second }
        var secondPlayerList = sortedPlayerInfo.filter { it.second == secondPlayer[0].second }
        if (secondPlayerList.isEmpty().not()){
            secondPlayerList = secondPlayerList.sortedBy { it.first }
            secondPlayerList.forEach { print("${it.first} ") }
        }
        println()
    }


}