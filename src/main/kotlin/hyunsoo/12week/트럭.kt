package hyunsoo.`12week`

import java.util.ArrayDeque
import java.util.Queue

/**
 * <문제>
 * [트럭](https://www.acmicpc.net/problem/13335)
 *
 *
 */

data class Truck(var weight: Int, var pos: Int = 1) {
    fun move() {
        this.pos++
    }
}

fun main() {

    val waitQueue: Queue<Truck> = ArrayDeque()
    val processQueue: Queue<Truck> = ArrayDeque()
    var usedTime = 1

    val (_, bridgeLength, bridgeMaxWeight) = readln().split(" ").map { it.toInt() }

    readln().split(" ")
        .map { it.toInt() }
        .forEach { weight ->
            waitQueue.add(Truck(weight))
        }

    var isArrived = false
    while (waitQueue.isNotEmpty() || processQueue.isNotEmpty()) {

        usedTime++

        // 최대 하중 무게만큼 트럭 올리기
        if (waitQueue.isNotEmpty() &&
            processQueue.sumOf { it.weight } + waitQueue.peek().weight <= bridgeMaxWeight
        ) {
            processQueue.add(waitQueue.poll())
        }

        // 트럭들 이동
        processQueue.forEach { truck ->
            truck.apply {
                move()
                if (bridgeLength < this.pos) isArrived = true
            }
        }

        if (isArrived) {
            processQueue.poll()
            isArrived = false
        }
    }

    println(usedTime)

}