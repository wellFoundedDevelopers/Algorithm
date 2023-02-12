package hyunsoo.`22week`

import java.util.*

/**
 *
 * <문제>
 * [최소 회의실 개수](https://www.acmicpc.net/problem/19598)
 *
 * - 아이디어
 *
 * 우선 순위 큐 + 순회 1번하면(스위핑?)
 * 시간 복잡도 통과 가능할 듯?
 * 대충.. log 100000 * 100000 ?
 *
 * - 트러블 슈팅
 *
 */
class `전현수_최소_회의실_개수` {

    private data class MeetingData(val start: Int, val end: Int)

    private val meetings = mutableListOf<MeetingData>()

    fun solution() {

        val arraySize = readln().toInt()
        repeat(arraySize) {
            readln().split(" ").map { it.toInt() }.apply {
                meetings.add(
                    MeetingData(this.first(), this.last())
                )
            }
        }

        val meetingsPq = PriorityQueue<MeetingData> { a, b ->
            a.end - b.end
        }

        meetings.apply {
            sortBy { it.start }
            meetingsPq.add(this.first())
        }

        meetings.drop(1).forEach { curMeetingInfo ->
            if (meetingsPq.peek().end <= curMeetingInfo.start) meetingsPq.poll()
            meetingsPq.add(curMeetingInfo)
        }

        println(meetingsPq.size)
    }
}

fun main() {
    전현수_최소_회의실_개수().solution()
}