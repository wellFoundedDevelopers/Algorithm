package jimin.`11week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[왕복](https://www.acmicpc.net/problem/18311)

<구현 방법>
코스와 코스 뒤집은 걸 합쳐서 for문을 돈다.
돌면서 해당 코스 영역이라면 idx를 계산해서 출력한다.

<트러블 슈팅>
int로 하면 NumberFormat 에러가 뜬다. Long을 사용해야한다.

 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, k) = readLine().trim().split(" ").map{ it.toLong() }
    val course = readLine().trim().split(" ").map{ it.toLong() }

    var distance = 0L
    kotlin.run {
        (course + course.reversed()).forEachIndexed { idx, it ->
            if (k in distance until  distance + it){
                println(
                    if (idx < n) idx + 1
                    else 2*n - idx
                )
                return@run
            }
            distance += it
        }
    }

}
