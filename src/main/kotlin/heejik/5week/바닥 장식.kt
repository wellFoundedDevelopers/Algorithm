package heejik.`5week`

fun main() {
    var answer = 0

    val (n, m) = readln().split(' ').map { it.toInt() }

    val floor = arrayListOf<String>().apply {
        repeat(n) {
            add(readln())
        }
    }

    repeat(n) { i ->
        answer += floor[i].split('|').filter { it.isNotEmpty() }.size
    }
    repeat(m) { j ->
        var tmp = ""
        repeat(n) { i ->
            tmp += floor[i][j]
        }
        answer += tmp.split('-').filter { it.isNotEmpty() }.size
    }

    println(answer)
}