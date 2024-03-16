package heejik.`54week`

class `스카이라인 쉬운거` {

    fun solve() {
        val n = readln().toInt()
        val s = mutableListOf<Int>(0)
        var count = 0

        repeat(n) {
            val (_, y) = readln().split(' ').map { it.toInt() }
            if (y > s.last()) {
                count++
                s.add(y)
            } else {
                while (y < s.last()) {
                    s.removeLast()
                }
                if (y != s.last()) {
                    s.add(y)
                    count++
                }
            }
        }
        println(count)
    }
}

fun main() {
    `스카이라인 쉬운거`().solve()
}