package jimin.`13week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[DNA](https://www.acmicpc.net/problem/1969)

<구현 방법>
알파벳 26개를 담는 리스트를 만들어 각 자리수 알파벳이 몇개인지 담았다.
알파벳 최대인 것을 구하고 최대수를 n에서 뺀 값이 Hamming Distance이다.

<트러블 슈팅>
*/

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val DNAList = mutableListOf<String>()
    val alphabetList = MutableList(m) { MutableList(26) { 0 } }
    var DNA = ""
    var num = 0
    repeat(n) {
        DNAList.add(readLine())
        repeat(m) { idx ->
            alphabetList[idx][DNAList.last()[idx] - 'A'] += 1
        }
    }
    repeat(m) { idx ->
        DNA += (alphabetList[idx].indexOf(alphabetList[idx].maxOf { it }) + 'A'.code).toChar()
        num += n - alphabetList[idx].maxOf { it }
    }
    println(DNA)
    println(num)
}

