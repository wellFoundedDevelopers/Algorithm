package heejik.`25week`

import kotlin.properties.Delegates

class 선수과목 {

    var n by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()
    lateinit var subject: MutableList<MutableList<Int>>
    lateinit var prerequisiteCount: MutableList<Int>

    fun solve() {
        setting()
        goToSchool()
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }

        subject = MutableList(n + 1) { mutableListOf() }
        prerequisiteCount = MutableList(n + 1) { 0 }

        repeat(m) {
            val (a, b) = readln().split(' ').map { it.toInt() }
            subject[a].add(b)
            prerequisiteCount[b]++
        }
    }

    private fun goToSchool() {

        var semester = 1
        val learnedSubject = mutableListOf<Int>()
        val subjectBySemester = MutableList(n + 1) { 0 }

        for (i in 1..n) {
            if (prerequisiteCount[i] == 0) {
                subjectBySemester[i] = semester
                learnedSubject.add(i)
            }
        }

        semester++

        while (learnedSubject.size != 0) {
            val tmpLearned = mutableListOf<Int>()
            learnedSubject.forEach { recentSubject ->
                subject[recentSubject].forEach {
                    prerequisiteCount[it]--
                    if (prerequisiteCount[it] == 0) {
                        tmpLearned.add(it)
                    }
                }
            }
            learnedSubject.clear()
            tmpLearned.forEach {
                subjectBySemester[it] = semester
                learnedSubject.add(it)
            }
            semester++
        }


        println(subjectBySemester.drop(1).joinToString(" "))
    }
}


fun main() {
    선수과목().solve()
}