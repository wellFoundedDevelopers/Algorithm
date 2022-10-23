package jimin.`6week`

import java.io.BufferedReader
import java.io.InputStreamReader
/*
<문제>
[비슷한 단어] (https://www.acmicpc.net/problem/2607)

<구현 방법>
알파벳 리스트를 만들어 알파벳이 몇개나 나왔는지 기록함.
이를 기반으로 master와 비교대상의 차이가 2또는 1이면 count를 증가시킴.
길이가 같으면 문자하나를 바꾸는 것이기 때문에 서로 다른 알파벳이 2개 있는 것이므로 차이가 2인 것이고,
길이가 다르면 문자하나를 더해주거나 빼주는 것이기 때문에 알파벳이 1개가 차이난다.

<트러블 슈팅>
알파벳 리스트를 만드는 것을 참고함
 */


fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val masterWord = readLine()
    val masterAlphabetList = MutableList(26) { i -> 0 }
    masterWord.forEach {
        masterAlphabetList[it - 'A'] += 1
    }

    val alphabetList = MutableList(n - 1) { MutableList(26) { i -> 0 } }
    repeat(n - 1) { n ->
        val word = readLine()
        word.forEach { w ->
            alphabetList[n][w - 'A'] += 1
        }
    }
    //println("master $masterAlphabetList")
    //println("alpha $alphabetList")

    var total = 0
    repeat(n - 1) { idx ->
        var num = 0
        if (masterWord.length == alphabetList[idx].sum()) { // 같은 길이 -> 하나 바꾸기 (2개가 다름)
            if (masterAlphabetList == alphabetList[idx]) {
                total += 1
            } else {
                masterAlphabetList.forEachIndexed { i, n ->
                    if (n > alphabetList[idx][i]) num += n - alphabetList[idx][i]
                    else num += alphabetList[idx][i] - n
                }
                if (num == 2) total += 1
            }
        } else { // 다른 길이 -> 하나 더하거나 빼기 (1개가 다름)
            masterAlphabetList.forEachIndexed { i, n ->
                if (n > alphabetList[idx][i]) num += n - alphabetList[idx][i]
                else num += alphabetList[idx][i] - n
            }
            if (num == 1) total += 1
        }

    }
    println(total)


}

