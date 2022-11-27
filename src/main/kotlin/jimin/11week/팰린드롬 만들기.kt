package jimin.`11week`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.LinkedHashMap

/*
<문제>
[팰린드롬 만들기](https://www.acmicpc.net/problem/1213)

<구현 방법>
알파벳을 check하여 팰린드롬이 될 수 없는 경우 I'm Sorry Hansoo 출력 후 종료한다.
아닐 경우 알파벳을 LinkedHashMap에 담고 정렬한 후,
해당 Map을 돌면서 홀수 인 것을 remain에 담고
나머지는 result에 절반만 담아 result + remain + remain.reversed() 해준다.

<트러블 슈팅>

 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val name = readLine().chunked(1).toMutableList()
    var nameMap = LinkedHashMap<String, Int>(50)
    name.forEach {
        nameMap[it] = (nameMap[it] ?: 0) + 1
    }
    if (checkResult(nameMap) > 1) {
        println("I'm Sorry Hansoo")
        return@with
    }

    nameMap = sortMapByKey(nameMap)

    var result = ""
    var remain = ""
    nameMap.forEach { key, value ->
        if (value % 2 != 0) remain = key

        repeat(value / 2) {
            result += key
        }
    }
    println(result + remain + result.reversed())
}

fun sortMapByKey(map: LinkedHashMap<String, Int>): LinkedHashMap<String, Int> {
    val entries = LinkedList(map.entries)

    entries.sortBy { it.key }

    val result = LinkedHashMap<String, Int>()
    for (entry in entries) {
        result[entry.key] = entry.value
    }

    return result
}

fun checkResult(map: LinkedHashMap<String, Int>): Int {
    var result = 0
    map.forEach { _, u ->
        if (u % 2 == 1) result += 1
    }
    return result
}