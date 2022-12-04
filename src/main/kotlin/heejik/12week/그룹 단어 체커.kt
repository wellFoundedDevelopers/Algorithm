package heejik.`12week`

class `그룹 단어 체커` {

    fun solve() {
        val n = readln().toInt()
        var cnt = 0
        repeat(n) {
            if (check(readln())) cnt++
        }
        println(cnt)
    }

    fun check(word: String): Boolean {
        var store = ""
        word.forEach {
            if (store.isEmpty()) store += it
            if (it != store.last()) {
                if (it in store) return false
                store += it
            }
        }
        return true
    }
}

fun main() {
    `그룹 단어 체커`().solve()
}