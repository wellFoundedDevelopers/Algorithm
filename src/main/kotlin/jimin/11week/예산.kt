package jimin.`11week`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val request = readLine().split(" ").map { it.toInt() }.toMutableList()
    val maximum = readLine().toInt()

    request.sort()

    if (request.sum() <= maximum) {
        println("${request.maxOf { it }}")
        return@with
    }

    val shared = maximum / n
    val sharedList = IntArray(n)
    var over = mutableListOf<Int>()
    request.forEachIndexed { idx, re ->
        if (re > shared) {
            sharedList[idx] = shared
            over.add(idx)
        } else {
            sharedList[idx] = re
        }
    }
    var remainMoney = maximum - sharedList.sum()
    var plusMoney = remainMoney / over.size
    var newOverNum = over.size
    //println("shared $shared remain $remainMoney plus $plusMoney")
    over.forEachIndexed { index, it ->
        if (request[it] - shared <= plusMoney) {
            remainMoney -= request[it] - shared
            newOverNum--
            plusMoney = (remainMoney) / newOverNum
        } else {
            remainMoney -= plusMoney
        }
        //println("$index ${request[it] - shared} remain $remainMoney plus $plusMoney")
    }
    println(shared + plusMoney)

}