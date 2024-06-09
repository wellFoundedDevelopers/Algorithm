package heejik.`66week`

import java.util.PriorityQueue

private class 순회강연 {

    data class Lecture(
        val fee: Int,
        val deadline: Int
    )

    fun solve() {
        val n = readln().toInt()
        val lectures = mutableListOf<Lecture>()
        val storeLectures = PriorityQueue<Lecture> { l1, l2 ->
            l1.fee - l2.fee
        }

        repeat(n) {
            val lecture = readln().split(' ').map { it.toInt() }.run {
                Lecture(fee = this[0], deadline = this[1])
            }
            lectures.add(lecture)
        }

        lectures.sortedBy { it.deadline }.forEach { lecture ->
            if (lecture.deadline > storeLectures.size) {
                storeLectures.add(lecture)
            } else {
                val smallFeeLecture = storeLectures.peek()
                if (lecture.fee > smallFeeLecture.fee) {
                    storeLectures.poll()
                    storeLectures.add(lecture)
                }
            }
        }

        println(storeLectures.sumOf { it.fee })
    }
}

fun main() {
    순회강연().solve()
}