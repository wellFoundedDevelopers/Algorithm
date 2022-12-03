package jimin.`12week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[그룹 단어 체커](https://www.acmicpc.net/problem/1316)

<구현 방법>
앞에 단어와 비교하면서 앞에 단어와 다르면서 사용된 알파벳 set에 존재한다면
개수를 1개씩 줄인다.
존재하지 않으면 before을 업데이트해준다.

<트러블 슈팅>
*/

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    var num = n
    repeat(n) {
        val word = readLine()
        var before = word[0]
        val usedAlphabet = mutableSetOf<Char>()
        word.forEach {
            if (it != before) {
                if (it in usedAlphabet) {
                    num--
                    return@repeat
                }
                usedAlphabet.add(before)
                before = it
            }
        }
    }
    println(num)
}
