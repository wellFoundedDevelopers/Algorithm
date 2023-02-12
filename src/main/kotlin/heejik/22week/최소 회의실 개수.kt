package heejik.`22week`

import java.util.PriorityQueue
import kotlin.properties.Delegates


class `최소 회의실 개수` {

    data class Meeting(
        val startTime: Long,
        val endTime: Long,
    )

    data class Room(
        var meeting: Meeting?
    ) : Comparable<Room> {
        override fun compareTo(other: Room): Int {
            val nullMeeting = Meeting(0, Long.MAX_VALUE)
            val myMeeting = this.meeting ?: nullMeeting
            val otherMeeting = other.meeting ?: nullMeeting

            return if (myMeeting.endTime > otherMeeting.endTime) {
                1
            } else if (myMeeting.endTime == otherMeeting.endTime) {
                0
            } else {
                -1
            }
        }

        fun isEmpty(time: Long): Boolean {
            if ((meeting ?: return true).endTime <= time) {
                removeMeeting()
            }

            return meeting == null
        }

        fun removeMeeting() {
            this.meeting = null
        }
    }

    var n by Delegates.notNull<Int>()
    val meetings = mutableListOf<Meeting>()

    fun setting() {
        n = readln().toInt()

        repeat(n) {
            readln().split(' ').map { it.toLong() }.run {
                meetings.add(
                    Meeting(
                        startTime = this.first(),
                        endTime = this.last()
                    )
                )
            }
        }

        meetings.sortBy { it.startTime }

        solve()
    }

    fun solve() {
        val rooms = PriorityQueue<Room>()
        meetings.forEach { meeting ->
            rooms.peek().run {
                if (this != null && this.isEmpty(meeting.startTime)) {
//                    this.meeting = meeting
                    rooms.poll()
                }
//                } else {
                rooms.add(Room(meeting))
            }
        }

        println(rooms.size)
    }

}

fun main() {
    `최소 회의실 개수`().setting()
}
