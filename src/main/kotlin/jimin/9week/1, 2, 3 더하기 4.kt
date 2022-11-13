package jimin.`9week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[색종이 만들기](https://www.acmicpc.net/problem/15989)

<구현 방법>
n의 최대인 10000에서 1을 더한 만큼의 크기인 리스트를 만들고 1로 채워줬다.
1로 채워준 이유는 무조건 1로만 더한 경우는 1가지이기 때문에.
2의 합으로 나타낸 것을 구하기 위해 dp[i]에 dp[i-2]를 더했다.
이와 마찬가지로 3도 구했다.

<트러블 슈팅>
처음에는 getTwo와 getThree라는 함수를 만들어서 2로 구할 수 있는 경우의 수와
3으로 구할 수 있는 경우의 수를 나눴다. 이때 getThree에서 getTwo를 호출하면서
모든 경우의 수를 구할 수 있었는데, 오류가 났다.
그래서 구글링의 힘을 빌렸다..ㅎ
 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val dp = MutableList(10001){ 1 }
    for (i in 2 .. 10000){
        dp[i] = dp[i] + dp[i - 2]
    }
    for (i in 3 .. 10000){
        dp[i] = dp[i] + dp[i - 3]
    }
    repeat(readLine().toInt()){
        println(dp[readLine().toInt()])
    }
}

//fun getTwo(num: Int): Int {
//    var number = num
//    var total = 1
//    while (number > 0) {
//        number -= 2
//        if (number >= 0) {
//            total += 1
//        }
//        println("2 total $total number $number")
//    }
//    return total
//}
//
//fun getThree(num: Int): Int {
//    var number = num
//    var total = getTwo(number)
//    while (number > 0) {
//        number -= 3
//        if (number > 0) {
//            total += getTwo(number)
//        }
//        println("3 total $total number $number")
//    }
//    return total
//}