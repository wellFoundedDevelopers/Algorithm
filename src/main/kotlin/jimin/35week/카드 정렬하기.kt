package jimin.`35week`

/*
<문제>
[인구이동](https://www.acmicpc.net/problem/16234)

<구현 방법>
priorityQueue를 이용해서 가장 작은 2개를 뽑은 후,
그 두개를 더한 값을 sum에 누적시키고,
그 두개를 더한 값을 다시 priorityQueue에 넣어주었다.

<트러블 슈팅>
처음에는 그냥 정렬해서 작은 것부터 순차적으로 더해줬다.
*/

import java.util.*

class `카드 정렬` {
    fun solve() {
        val priorityQueue = PriorityQueue<Int>()
        repeat(readln().toInt()) {
            priorityQueue.add(readln().toInt())
        }
        var sum = 0
        while(priorityQueue.size >= 2) {
            val first = priorityQueue.poll()
            val second = priorityQueue.poll()
            sum += first + second
            priorityQueue.add(first + second)
        }

        println(sum)
    }
}

fun main() {
    `카드 정렬`().solve()
}