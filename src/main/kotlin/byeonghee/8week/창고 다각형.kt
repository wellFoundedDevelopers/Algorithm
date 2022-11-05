package byeonghee.`8week`

import java.io.*
import java.util.PriorityQueue

class `소병희_창고 다각형` {

    data class Pillar(val d: Int, val h: Int)

    val br = BufferedReader(InputStreamReader(System.`in`))
    val pq = PriorityQueue(Comparator<Pillar>{ a, b -> b.h - a.h })
    val storage = ArrayDeque<Pillar>()
    var answer = 0

    fun solution() {
        val n = br.readLine().toInt()
        repeat(n) {
            br.readLine().split(" ").map{ it.toInt() }.let {
                pq.add(Pillar(it[0], it[1]))
            }
        }
        storage.add(pq.poll())

        while(pq.isNotEmpty()) {
            pq.poll().let { cur ->
                if (cur.d < storage.first().d) {
                    answer += cur.h * (storage.first().d - cur.d)
                    storage.addFirst(cur)
                }
                else if (cur.d > storage.last().d) {
                    answer += storage.last().h + cur.h * (cur.d - storage.last().d - 1)
                    storage.addLast(cur)
                }
            }
        }
        answer += storage.last().h

        println(answer)
    }
}

fun main() {
    `소병희_창고 다각형`().solution()
}