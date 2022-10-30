package jimin.`7week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>

<구현 방법>

<트러블 슈팅>
 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val movementList = readLine().chunked(1)
    val direction = mapOf<String, Pair<Int, Int>>(
        "N" to Pair(-1, 0),
        "S" to Pair(1, 0),
        "W" to Pair(0, -1),
        "E" to Pair(0, 1)
    )
    var nowPosition = Pair(0, 0)
    var nowDirection = "S"

    var possibleZone = mutableListOf<Pair<Int, Int>>(nowPosition)
    movementList.forEach { m ->
        when (m) {
            "F" -> {
                nowPosition = Pair(
                    nowPosition.first + (direction[nowDirection]?.first ?: 0),
                    nowPosition.second + (direction[nowDirection]?.second ?: 0)
                )
                possibleZone.add(nowPosition)
            }
            "R" -> {
                when (nowDirection) {
                    "S" -> nowDirection = "W"
                    "N" -> nowDirection = "E"
                    "W" -> nowDirection = "N"
                    "E" -> nowDirection = "S"
                }
            }
            "L" -> {
                when (nowDirection) {
                    "S" -> nowDirection = "E"
                    "N" -> nowDirection = "W"
                    "W" -> nowDirection = "S"
                    "E" -> nowDirection = "N"
                }
            }
        }
    }

    val minFirst = possibleZone.minOf { it.first }
    val minSecond = possibleZone.minOf { it.second }
    if (minFirst < 0) {
        possibleZone = possibleZone.map { it.copy(first = it.first + minFirst * (-1)) } as MutableList<Pair<Int, Int>>
    } else if (minSecond < 0) {
        possibleZone =
            possibleZone.map { it.copy(second = it.second + minSecond * (-1)) } as MutableList<Pair<Int, Int>>
    }
    val result =
        MutableList(possibleZone.maxOf { it.first } - possibleZone.minOf { it.first } + 1)
        { MutableList(possibleZone.maxOf { it.second } - possibleZone.minOf { it.second } + 1) { "#" } }

    possibleZone.forEach {
        result[it.first][it.second] = "."
    }

    result.forEach {
        println(it.joinToString(""))
    }

}
