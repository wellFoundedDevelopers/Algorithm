package heejik.`13week`

class 크로스워드 {

    fun solve() {

        val (r, c) = readln().split(' ').map { it.toInt() }

        val crossWord = mutableListOf<String>()
        val words = mutableListOf<String>()


        repeat(r) {
            val row = readln()
            crossWord.add(row)
            row.split('#').forEach {
                if (it.length > 1) words.add(it)
            }
        }

        repeat(c) { n ->
            crossWord.joinToString(separator = "") { it[n].toString() }.split('#').forEach {
                if (it.length > 1) words.add(it)
            }
        }

        println(words.minOrNull())
    }
}


fun main() {
    크로스워드().solve()
}