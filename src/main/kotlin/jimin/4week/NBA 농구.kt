package jimin.`4week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
https://www.acmicpc.net/problem/2852

<구현 방법>
현재 득점 시간에서 다음 득점 시간을 빼서 현재 이기고 있는 팀에게 시간을 더해주었다.
마지막 득점 시간은 게임이 끝나는 시간에서 빼주었다.

<트러블 슈팅>
 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val scoreInfo = mutableListOf<MutableList<String>>()
    var team1Score = 0
    var team1Time = 0
    var team2Score = 0
    var team2Time = 0
    val lastTime = 48 * 60
    repeat(readLine().toInt()) {
        scoreInfo.add(readLine().split(" ").toMutableList())
    }
    scoreInfo.forEachIndexed { index, strings ->
        val (m, s) = scoreInfo[index][1].split(":").map { it.toInt() }
        val totalTime = m * 60 + s
        if (scoreInfo[index][0] == "1") team1Score += 1
        else team2Score += 1

        if (index == scoreInfo.size - 1) {
            if (team1Score > team2Score) {
                team1Time += lastTime - totalTime
            } else if (team1Score < team2Score) {
                team2Time += lastTime - totalTime
            }
        } else {
            val(nextM, nextS) = scoreInfo[index + 1][1].split(":").map { it.toInt() }
            val nextTime = nextM * 60 + nextS
            if (team1Score > team2Score) {
                team1Time += nextTime - totalTime
            } else if (team1Score < team2Score) {
                team2Time += nextTime - totalTime
            }
        }
    }
    println("${(team1Time/60).toString().padStart(2,'0')}:${(team1Time%60).toString().padStart(2,'0')}")
    println("${(team2Time/60).toString().padStart(2,'0')}:${(team2Time%60).toString().padStart(2,'0')}")
}