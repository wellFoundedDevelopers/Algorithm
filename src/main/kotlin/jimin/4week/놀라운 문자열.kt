package jimin.`4week`

/*
<문제>
https://www.acmicpc.net/problem/1972

<구현 방법>
거리 D를 for문을 돌려 i로 구하고, 이를 0부터 문자열의 길이 - i까지 for문을 2중으로 돌린다.
set인 duplicationCheckList를 이용하여 해당 리스트에 존재하면
duplication을 true로 주고, 안쪽 for문을 탈출하게 한다.
만약 duplication이 true일 경우에는 바깥 for문을 탈출해 Not을 출력한다.

<트러블 슈팅>
 */

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val strList = arrayListOf<String>()

    while (true){
        val newStr = readLine()
        if (newStr == "*") break
        strList.add(newStr)
    }
    strList.forEach { str ->
        var duplication = false
        for (i in 1 until str.length){
            val duplicationCheckList = mutableSetOf<String>()
            for (j in 0 until str.length - i){
                val word = "${str[j]}${str[j + i]}"
                if (word in duplicationCheckList) {
                    duplication = true
                    break
                } else {
                    duplicationCheckList.add(word)
                }
            }
            if (duplication) break
        }
        if (duplication) println("$str is NOT surprising.")
        else println("$str is surprising.")
    }
}