package jimin.`17week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[추월](https://www.acmicpc.net/problem/2002)

<구현 방법>
터널 전 후 차 리스트를 beforeCars와 afterCars에 입력받는다.
afterCars에 입력받을 때 해당 car가 beforeCars에서의 index를 찾고
0부터 해당 인덱스까지의 차가 afterCars에 없다면 result를 1개 늘려줬다.

<트러블 슈팅>
처음에는 그냥 index만 비교해줬는데 그렇게 하면 안되고
전체 차를 다 비교해줘야한다.
*/

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val beforeCars = mutableListOf<String>()
    val afterCars = mutableListOf<String>()
    var result = 0
    repeat(n) {
        beforeCars.add(readLine())
    }
    repeat(n) {
        val car = readLine()
        run {
            repeat(beforeCars.indexOf(car)) {
                if (beforeCars[it] !in afterCars) {
                    result += 1
                    return@run
                }
            }
        }
        afterCars.add(car)
    }
    println(result)
}
