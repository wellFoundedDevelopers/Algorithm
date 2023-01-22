package jimin.`19week`

/*
<문제>
[동전 1](https://www.acmicpc.net/problem/2293)

<구현 방법>
dp를 이용해서 풀었다.
0원을 만드는 경우도 1가지이니까 sumList[0]에 1을 넣어준다.
현재 것 + 지금 coin뺀 것 으로 하면 된다...

<트러블 슈팅>
dp 넘 어렵다.......
https://lotuslee.tistory.com/113 참고함
*/
fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val coins = mutableListOf<Int>()
    repeat(n) {
        coins.add(readln().toInt())
    }
    coins.sort()
    val sumList = MutableList(k + 1) { 0 }
    sumList[0] = 1
    coins.forEachIndexed { idx, coin ->
        for (i in 1..k) {
            if (i - coins[idx] >= 0) {
                sumList[i] = sumList[i] + sumList[i - coins[idx]]
            }
        }
    }
    println(sumList[k])
}