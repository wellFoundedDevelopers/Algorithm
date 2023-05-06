package jimin.`34week`

/*
<문제>
[행복 유치원](https://www.acmicpc.net/problem/13164)

<구현 방법>
원생들의 i 번째와 i + 1번째의 차이를 priorityQueue를 이용해서 저장한다.
이를 차이, idx 순으로 우선순위를 정한다.
k - 1만큼 pq에서 poll한 것을 idxes에 저장해서 해당 구역만큼 비용을 계산한다.

<트러블 슈팅>
*/


import java.util.*

class `행복 유치원` {
    fun solve() {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val students = readln().split(" ").map { it.toInt() }
        val pq = PriorityQueue(Comparator<Pair<Int, Int>> { a, b -> if (a.first != b.first) b.first - a.first else b.second - a.second })

        for (i in 0 until n - 1) {
            pq.add(Pair(students[i + 1] - students[i], i))
        }
        var tmp = 0
        val idxes = mutableListOf<Int>()
        while(pq.isNotEmpty() && tmp < k - 1) {
            idxes.add(pq.poll().second)
            tmp += 1
        }
        //println(idxes)
        var first = 0
        var result = 0
        idxes.forEach{ idx ->
            result += students[idx] - students[first]
            first = idx + 1
        }
        result += students.last() - students[first]

        println(result)
    }
}

fun main() {
    `행복 유치원`().solve()
}