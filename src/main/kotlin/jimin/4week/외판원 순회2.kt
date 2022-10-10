package jimin.`4week`

/*
<문제>
https://www.acmicpc.net/problem/10971

<구현 방법>
dfs를 이용하여 풀었다.
checkList를 만들어서 해당 경로를 지났는지 판단하였다.
이때 다시 원래 도시로 돌아와야하므로 firstIndex를 넣어
마지막에 해당 경로의 비용도 더해 최소 비용을 구하도록 했다.
이때 경로의 비용이 0인 경우를 제외하였다.
리스트.toMutableList()를 이용하여 복사해 새로운 리스트를 넣어주도록 하여
다른 경로 조합에 영향을 주지 못하도록 했다.

<트러블 슈팅>
 */

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.min

val priceList = mutableListOf<MutableList<Int>>()
var minPrice = Int.MAX_VALUE
fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {

    val checkList = mutableListOf<Boolean>()
    val num = readLine().toInt()
    repeat(num) {
        priceList.add(readLine().split(" ").map { it.toInt() }.toMutableList())
        checkList.add(false)
    }

    for (i in 0 until num) {
        //println("<$i>")
        pickNum(checkList.toMutableList(), i, 0, i)
    }

    println(minPrice)

}

fun pickNum(checkList: MutableList<Boolean>, index: Int, price: Int, firstIndex: Int) {
    checkList[index] = true

    checkList.forEachIndexed { idx, check ->
        if (check.not() && priceList[index][idx] != 0) {
            //println("index:$index idx:$idx checkList:$checkList price:$price next:${priceList[index][idx]}")
            pickNum(checkList.toMutableList(), idx, price + priceList[index][idx], firstIndex)
        }
    }

    if (checkList.all { it } && priceList[index][firstIndex] != 0) {
        //println("all true - price:${price + priceList[index][firstIndex]} minPrice:$minPrice")
        minPrice = min(price + priceList[index][firstIndex], minPrice)
    }
}