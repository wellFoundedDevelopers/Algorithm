package heejik.`12week`

class `수리공 항승` {

    fun solve() {
        var cnt = 0
        var pos = 0.0

        val (n, l) = readln().split(' ').map { it.toInt() }

        val water = readln().split(' ').map { it.toInt() }.sorted()

        water.forEach {
            if (pos < it + 0.5) {
                cnt++
                pos = it - 0.5 + l
            }
        }
        println(cnt)
    }
}

fun main() {
    `수리공 항승`().solve()
}