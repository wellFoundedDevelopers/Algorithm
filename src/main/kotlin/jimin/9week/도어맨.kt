package jimin.`9week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[도어맨](https://www.acmicpc.net/problem/5002)

<구현 방법>
사람들 정보가 담긴 문자열을 돌면서
W와 M개수의 차이를 구했고, 이것이 기억할 수 있는 차이보다 작으면 계속 더해줬고,
크면 다음 사람과 바꿨을 때 차이보다 적다면 바꿔주고, 아니라면 for문을 탈출시켰다.

<트러블 슈팅>
indexOutOfBounds가 나왔었다. 다음 위치 사람을 확인할 때 index 범위를 안잡아줬기 때문이다.
 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val maxDiff = readLine().toInt()
    val people = readLine().chunked(1).toMutableList()
    var man = 0
    var woman = 0
    var result = 0

    run {
        people.forEachIndexed { index, s ->
            if (man - woman >= maxDiff) {
                if (s == "M" && index + 1 < people.size && people[index + 1] == "W") {
                    people[index] = "W"
                    people[index + 1] = "M"
                    woman += 1
                } else if (s == "W"){
                    woman += 1
                } else {
                    return@run
                }
            } else if (woman - man >= maxDiff) {
                if (s == "W" && index + 1 < people.size && people[index + 1] == "M") {
                    people[index] = "M"
                    people[index + 1] = "W"
                    man += 1
                } else if (s == "M"){
                    man += 1
                } else {
                    return@run
                }
            } else {
                if (s == "M") {
                    man += 1
                } else {
                    woman += 1
                }
            }
            result += 1
            //println("man $man woman $woman result $result")
        }
    }
    println(result)

}
