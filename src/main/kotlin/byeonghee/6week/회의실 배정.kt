package byeonghee.`6week`

import java.io.*
import java.util.PriorityQueue

val br = BufferedReader(InputStreamReader(System.`in`))

data class Meeting(val s: Int, val e: Int)

var n = 0
val meetings = PriorityQueue(Comparator<Meeting> { a, b -> if (a.e == b.e) a.s - b.s else a.e - b.e })
var answer = 0
var curLastT = 0
lateinit var nxt : Meeting

fun main() {
    n = br.readLine().toInt()
    repeat(n) {
        br.readLine().split(" ").map{ it.toInt() }.run{
            meetings.add(Meeting(first(), last()))
        }
    }

    while(meetings.isNotEmpty()) {
        nxt = meetings.poll()
        if (curLastT <= nxt.s) {
            curLastT = nxt.e
            answer++
        }
    }

    println(answer)
}