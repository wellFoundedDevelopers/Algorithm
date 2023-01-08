package hyunsoo.`6week`


/**
 * <문제>
 * [회의실 배정](https://www.acmicpc.net/problem/1931)
 *
 * 한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회으ㅔㅇ 대하여 회의실 사용표를 만드려고 함.
 * 각 회의 I에 대해 시작시간과 끝나는 시간이 주어져있고, 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수 찾기
 *
 * 아이디어
 * - 회의의 수가 100,000개라 완탐은 불가능
 */

data class MeetingData(val start: Int, val end: Int)

fun main() {

    val meetingCnt = readln().toInt()
    val meetingList = mutableListOf<MeetingData>()
    var canMeetingCnt = 0
    var endTime = 0

    repeat(meetingCnt) {
        val (start, end) = readln().split(" ").map { it.toInt() }
        meetingList.add(MeetingData(start, end))
    }

    // 정렬
    meetingList.sortWith(compareBy<MeetingData> { it.end }.thenBy { it.start })

    // 정렬된 첫 미팅은 반드시 해야지!
    endTime = meetingList.first().end
    canMeetingCnt++

    meetingList.drop(1).forEach { meetingData ->
        if (endTime <= meetingData.start) {
            endTime = meetingData.end
            canMeetingCnt++
        }
    }

    println(canMeetingCnt)
}