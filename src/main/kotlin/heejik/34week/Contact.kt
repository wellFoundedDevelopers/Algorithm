package heejik.`34week`

class Contact {

    val regex = Regex("(100+1+|01)+")

    fun solve() {
        repeat(readln().toInt()) {
            val wave = readln()
            println(if (regex.matches(wave)) "YES" else "NO")
        }
    }
}

fun main() {
    Contact().solve()
}