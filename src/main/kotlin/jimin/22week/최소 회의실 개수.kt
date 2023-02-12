package jimin.`22week`

/*
<문제>
[최소 회의실 개수](https://www.acmicpc.net/problem/19598)

<구현 방법>
우선 회의들을 시작시간을 기준으로 오름차순해준다.
그런후 첫번째 회의의 종료시간을 pq에 넣고
1번 인덱스부터 끝까지 for문을 돌면서 시작시간이 pq의 top보다 같거나 큰 경우 pq를 pop해준다.
pq에 종료시간을 계속 추가해준다.

<트러블 슈팅>
https://ongveloper.tistory.com/586
여러번 도전했지만 실패하여,, 해당 블로그를 참고했다
*/

import java.util.*

class `최소 회의실 개수` {
    val meetings = mutableListOf<Meeting>()

    fun solve() {
        repeat(readln().toInt()) {
            readln().split(" ").map { it.toInt() }.apply {
                meetings.add(Meeting(first(), last()))
            }
        }
        meetings.sortWith(compareBy<Meeting> { it.start }.thenBy{ it.end })

        val pq = PriorityQueue<Int>()
        pq.add(meetings.first().end)
        meetings.drop(1).forEach{ meeting ->
            if (pq.peek() <= meeting.start) {
                pq.poll()
            }
            pq.add(meeting.end)
        }
        println(pq.size)
    }

    data class Meeting(
        val start: Int,
        val end: Int
    )
}

fun main() {
    `최소 회의실 개수`().solve()
}