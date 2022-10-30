package jimin.`7week`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import kotlin.math.abs

/*
<문제>
[최소 힙] https://www.acmicpc.net/problem/1927

<구현 방법>
PriorityQueue를 사용함

<트러블 슈팅>
그냥 MutableList로 하고 minOf로 찾는 방법으로하니 시간초과가 났다.

 */


fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val queue = PriorityQueue<Int>()
    repeat(readLine().toInt()) {
        val x = readLine().toInt()
        if (x == 0){
            if (queue.isEmpty()) {
                println("0")
            } else {
                println(queue.poll())
            }
        } else {
            queue.add(x)
        }
    }

}

