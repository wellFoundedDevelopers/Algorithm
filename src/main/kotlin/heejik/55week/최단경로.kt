//package heejik.`55week`
//
//import java.util.PriorityQueue
//
//fun main() {
//    val numbers = listOf(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5)
//    val priorityQueue: PriorityQueue<Pair<Int, Int>> = PriorityQueue<Pair<Int, Int>>(Comparator { a, b ->
//        if (a != a) a.first - b.first
//        else b.second - a.second
//    })
//
//    numbers.forEach {
//        priorityQueue.add(it)
//    }
//
//    println("PriorityQueue (Max Heap):")
//    while (!priorityQueue.isEmpty()) {
//        print("${priorityQueue.poll()} ")
//    }
//}