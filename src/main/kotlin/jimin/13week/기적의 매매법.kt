package jimin.`13week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[기적의 매매법](https://www.acmicpc.net/problem/20546)

<구현 방법>
준현이는 가능한 첫번째꺼를 기준으로 계산하고,
성민이는 up과 down을 계산하여 자산을 계산한다.

<트러블 슈팅>
money가 주식 어느 하나보다 크지 않을 때 first함수를 사용하면 runTime 에러가 난다.
*/

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val money = readLine().toInt()
    val stockPrices = readLine().split(" ").map { it.toInt() }
    val junStock = if (stockPrices.minOf { it } < money) stockPrices.first{ it <= money } else 0
    val junPrice = if (junStock == 0) money else (money / junStock) * stockPrices.last() + money % junStock
    var up = 0
    var down = 0
    var stockNum = 0
    var cash = money
    stockPrices.forEachIndexed { idx, price ->
        if (idx != 0 && stockPrices[idx - 1] < price) {
            up += 1
            down = 0
        } else if (idx != 0 && stockPrices[idx - 1] > price) {
            down += 1
            up = 0
        } else {
            down = 0
            up = 0
        }
        if (up == 3) {
            cash += price * stockNum
            stockNum = 0
        }
        if (down >= 3) {
            val num = cash / price
            cash -= price * num
            stockNum += num
        }
    }
    val sungPrice = stockNum * stockPrices.last() + cash
    if (junPrice > sungPrice) {
        println("BNP")
    } else if (junPrice < sungPrice) {
        println("TIMING")
    } else {
        println("SAMESAME")
    }
}
