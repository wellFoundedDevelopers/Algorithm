package byeonghee.`7week`

/** 입력이 [s, e)로 주어짐에 주의해야 했다 **/

import java.io.*
import java.util.PriorityQueue

class `소병희_흙길 보수하기` {

    data class Water(val s: Int, val e: Int)

    val br = BufferedReader(InputStreamReader(System.`in`))
    val pq = PriorityQueue(Comparator<Water>{ a, b -> a.s - b.s })
    var n = 0
    var l = 0

    lateinit var cur : Water
    var mergeCnt = 0
    var prevEnd = 0
    var answer = 0

    fun solution() {
        br.readLine().split(" ").map{ it.toInt() }.let {
            n = it[0]
            l = it[1]
        }

        repeat(n) {
            br.readLine().split(" ").map{ it.toInt() }.sorted().let {
                pq.add(Water(it[0], it[1] - 1))
            }
        }

        while(pq.isNotEmpty()) {
            cur = pq.poll()
            prevEnd = Integer.max(prevEnd, cur.s)
            mergeCnt = getCount(prevEnd, cur.e)
            answer += mergeCnt
            prevEnd +=  l * mergeCnt
        }

        println(answer)
    }

    fun getCount(s: Int, e: Int) : Int {
        return (e - s + l ) / l
    }
}

fun main() {
    `소병희_흙길 보수하기`().solution()
}