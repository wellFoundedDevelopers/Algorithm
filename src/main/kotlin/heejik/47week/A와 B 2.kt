package heejik.`47week`

class `A와 B 2` {

    fun solve() {
        val s = readln()
        val t = readln()
        val queue = ArrayDeque<String>()
        queue.add(t)

        while (queue.isNotEmpty()) {
            val nowString = queue.removeFirst()
            if (nowString.length == s.length) {
                if (nowString == s) {
                    println(1)
                    return
                }
                continue
            }

            if (nowString.last() == 'A') {
                queue.add(nowString.dropLast(1))
            }

            if (nowString.first() == 'B') {
                queue.add(nowString.reversed().dropLast(1))
            }
        }


        println(0)
    }
}

fun main() {
    `A와 B 2`().solve()
}