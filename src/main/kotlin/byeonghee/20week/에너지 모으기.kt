package byeonghee.`20week`

import java.io.*
import java.util.PriorityQueue
import kotlin.math.max

data class Node(var v: Int, var pre: Node? = null, var nxt: Node? = null)
data class Pick(val maxNode: Node, val rm: Node)

fun main() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()

    val balls = readLine().split(" ").map{ Node(it.toInt()) }
    val maxV = balls.maxOf { it.v }
    val maxList = balls.filter { it.v == maxV }
    for(i in 0 until n - 1) {
        balls[i].nxt = balls[i + 1]
        balls[i + 1].pre = balls[i]
    }

    val pq = PriorityQueue(Comparator<Pick> { a, b -> b.rm.v - a.rm.v })
    var answer = 0

    for(i in maxList.indices) {
        if (maxList[i].pre != null) pq.add(Pick(maxList[i], maxList[i].pre!!))
        if (maxList[i].nxt != null) pq.add(Pick(maxList[i], maxList[i].nxt!!))
    }

    while(pq.isNotEmpty()) {
        val (maxNode, rm) = pq.poll()
        if (rm.pre == null || rm.nxt == null) continue

        answer += rm.pre!!.v * rm.nxt!!.v

        rm.pre?.nxt = rm.nxt
        rm.nxt?.pre = rm.pre
        rm.pre = null
        rm.nxt = null

        if (maxNode.pre != null) pq.add(Pick(maxNode, maxNode.pre!!))
        if (maxNode.nxt != null) pq.add(Pick(maxNode, maxNode.nxt!!))
    }

    answer += balls.first().v * balls.last().v
    println(answer)

}