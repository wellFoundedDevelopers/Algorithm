package heejik.`18week`

class 거짓말 {

    val queue = ArrayDeque<Int>()
    private lateinit var knowTruths: MutableList<Boolean>
    private val relations = mutableMapOf<Int, MutableList<Int>>()
    fun solve() {
        val (n, m) = readln().split(' ').map { it.toInt() }
        knowTruths = MutableList(n + 1) { false }

        readln().split(' ').map { it.toInt() }.run {
            if (this.first() > 0) {
                this.drop(1).forEach {
                    knowTruths[it] = true
                    queue.add(it)
                }
            }
        }

        val parties = mutableListOf<List<Int>>()

        repeat(m) {
            readln().split(' ').map { it.toInt() }.run {
                val party = this.drop(1)
                parties.add(party)
                party.forEach { key ->
                    relations.getOrPut(key) { mutableListOf<Int>() }.addAll(party.filter { it != key })
                }
            }
        }

        bfs()

        var answer = 0

        parties.forEach { party ->
            if (party.all { knowTruths[it].not() }) answer++
        }

        println(answer)
    }

    private fun bfs() {
        while (queue.isNotEmpty()) {
            val knowTruth = queue.removeFirst()
            (relations[knowTruth] ?: listOf()).forEach {
                if (knowTruths[it].not()) {
                    knowTruths[it] = true
                    queue.add(it)
                }
            }
        }
    }

}

fun main() {
    거짓말().solve()
}