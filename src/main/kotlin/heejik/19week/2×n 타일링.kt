package heejik.`19week`

class `2×n 타일링` {

    lateinit var tileCountStore: MutableList<Long>

    fun solve() {
        val n = readln().toInt()

        tileCountStore = MutableList(1001) { 0L }
        tileCountStore[1] = 1L
        tileCountStore[2] = 2L

        for (i in 3..1000) {
            tileCountStore[i] = (tileCountStore[i - 1] + tileCountStore[i - 2]) % 10007
        }

        println(tileCountStore[n] % 10007)
    }
}

fun main() {
    `2×n 타일링`().solve()
}