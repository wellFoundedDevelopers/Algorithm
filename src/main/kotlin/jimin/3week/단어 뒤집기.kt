package jimin.`3week`

/*
<문제>
https://www.acmicpc.net/problem/1257

<구현 방법>
모든 경우의 수를 다 탐방했다!

<트러블 슈팅>
처음에는 공식을 찾으려 했는데 실패했다.
그냥 부르트포스 문제였다.. 완전탐색!
 */

import java.io.BufferedReader
import java.io.InputStreamReader


fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val word = readLine().chunked(1)
    var minWord = word.subList(0, 2).joinToString("") + word.subList(2, word.size).joinToString("").reversed()
    for (i in 1 until word.size - 1) {
        for (j in i + 1 until word.size) {
            val reversedWord =
                word.subList(0, i).joinToString("").reversed() + word.subList(i, j).joinToString("")
                    .reversed() + word.subList(j, word.size)
                    .joinToString("").reversed()
            minWord = minString(minWord, reversedWord)
        }

    }
    println(minWord)

}

fun minString(s1: String, s2: String): String {
    return if (s1 <= s2) s1
    else s2
}