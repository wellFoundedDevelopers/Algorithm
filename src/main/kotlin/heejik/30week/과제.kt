package heejik.`30week`

import kotlin.properties.Delegates

class 과제 {

    data class Homework(
        val deadline: Int,
        val score: Int,
    )

    var n by Delegates.notNull<Int>()
    val homeworks = mutableListOf<Homework>()

    fun solve() {
        setting()
        println(getScore())
    }

    private fun setting() {
        n = readln().toInt()
        repeat(n) {
            homeworks.add(readln().split(' ').map { it.toInt() }.run {
                Homework(this[0], this[1])
            })
        }
        homeworks.sortWith(comparator = compareBy({ it.deadline }, { it.score }))
    }


    private fun getScore(): Int {
        var day = 1
        val doneHomeworks = mutableListOf<Homework>()
        while (homeworks.isNotEmpty()) {
            val homework = homeworks.removeFirst()
            if (homework.deadline >= day) {
                doneHomeworks.add(homework)
                day++
            } else {
                val minScoreHomework = doneHomeworks.find { it.score == doneHomeworks.minOf { done -> done.score } }!!
                if (minScoreHomework.score < homework.score) {
                    doneHomeworks.remove(minScoreHomework)
                    doneHomeworks.add(homework)
                }

            }
        }
        return doneHomeworks.sumOf { it.score }
    }
}

fun main() {
    과제().solve()
}