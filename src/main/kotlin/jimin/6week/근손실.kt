package jimin.`6week`

import java.io.BufferedReader
import java.io.InputStreamReader

var loss: Int = 0
val defaultWeight = 500
var time = 0
fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    loss = k
    val exerciseList = readLine().split(" ").map { it.toInt() }.toMutableList()

    repeat(n) {
        dfs(exerciseList.toMutableList(), it, defaultWeight)
        //println("time: $time")
    }
    println(time)

}

fun dfs(exerciseList: MutableList<Int>, index: Int, weight: Int) {
    val todayWeight = weight - loss + exerciseList[index]
    if (todayWeight < defaultWeight) {
        return
    } else {
        exerciseList[index] = -1
        if (exerciseList.all { it == -1 }) time += 1
        exerciseList.forEachIndexed { idx, it ->
            if (it != -1) {
                //println("index $index, todayWeight $todayWeight $exerciseList")
                dfs(exerciseList.toMutableList(), idx, todayWeight)
            }
        }

    }
}