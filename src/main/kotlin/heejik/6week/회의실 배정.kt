package heejik.`6week`


class `회의실 배정` {

    data class Time(
        val start : Int,
        val end : Int
    )

    fun solve() {
        var cnt = 0
        var time = 0
        val room = arrayListOf<Time>()

        repeat(readln().toInt()) {
            val (start, end) = readln().split(' ').map { it.toInt() }
            room.add(Time(start,end))
        }

        room.sortedWith(comparator = compareBy( {it.end}, {it.start} )).forEach {
            if (it.start >= time) {
                time = it.end
                cnt += 1
            }
        }
        println(cnt)
    }
}



fun main() {
    `회의실 배정`().solve()
}
