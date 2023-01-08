package heejik.`6week`

class `비슷한 단어` {

    private val wordList = mutableListOf<String>()

    fun solve() {

        val n = readln().toInt()
        val word = readln()

        repeat(n-1) {
            wordList.add(readln())
        }

        println(wordList.filter { isSame(word,it) || isSimilar(word,it) }.size)

    }

    private fun isSame(a: String, b: String): Boolean {
        a.toSet().forEach { char ->
            if (b.contains(char).not() || (a.count { it == char } == b.count { it == char }).not()) {
                return false
            }
        }
        b.toSet().forEach { char ->
            if (a.contains(char).not() || (a.count { it == char } == b.count { it == char }).not()) {
                return false
            }
        }
        return true
    }

    private fun isSimilar(a:String, b:String): Boolean {
        val upper = 'A'..'Z'

        // 하나 빼기
        a.forEachIndexed { i, c ->
            if (isSame(a.filterIndexed { j, c -> i!=j },b)) {
                return true
            }
        }

        // 하나 더하기
        upper.forEach {
            if (isSame(a.plus(it),b)) return true
        }
        // 하나 변화
        upper.forEach {
            a.forEachIndexed { index, _ ->
                if (isSame(a.replaceRange((index..index), it.toString()),b)) return true
            }
            b.forEachIndexed { index, _ ->
                if (isSame(b.replaceRange((index..index), it.toString()),a)) return true
            }
        }

        return false
    }
}

fun main() {
    `비슷한 단어`().solve()

}
