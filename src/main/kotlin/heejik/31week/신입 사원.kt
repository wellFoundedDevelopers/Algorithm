package heejik.`31week`

class `신입 사원` {

    data class Rank(
        val document: Int,
        val interview: Int
    )

    fun solve() {
        runByTestCase()
    }

    private fun runByTestCase() {
        repeat(readln().toInt()) { t ->
            val applicants = mutableListOf<Rank>()
            repeat(readln().toInt()) {
                val (document, interview) = readln().split(' ').map { it.toInt() }
                applicants.add(Rank(document, interview))
            }
            hire(applicants)
        }
    }

    private fun hire(applicants: MutableList<Rank>) {
        var hiredManCount = 0
        var minInterviewRank = Int.MAX_VALUE
        applicants.sortedBy { it.document }.forEach { rank ->
            if (rank.interview < minInterviewRank) {
                hiredManCount++
                minInterviewRank = rank.interview
            }
        }
        println(hiredManCount)
    }
}


fun main() {
    `신입 사원`().solve()
}
