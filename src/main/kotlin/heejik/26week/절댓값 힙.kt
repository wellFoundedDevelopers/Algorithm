import java.util.PriorityQueue
import kotlin.math.abs

fun main() {
    val pq = PriorityQueue(compareBy<Int> { abs(it) }.thenComparator { a, b -> a - b })
    repeat(readln().toInt()) {
        val x = readln().toInt()
        if (x == 0) {
            println(pq.poll() ?: 0)
        } else {
            pq.add(x)
        }
    }
}