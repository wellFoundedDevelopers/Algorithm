package jimin.`42week`

/*
<문제>
[블로그](https://www.acmicpc.net/problem/21921)

<구현 방법>
먼저 처음 0을 시작으로 sum을 구한 다음, 앞에 한개를 빼고 뒤에 한개를 추가하는 식으로 sum을 갱신하여
max값인지 아닌지 판단해 결과를 구했다.

<트러블 슈팅>
처음에 maxi를 sum으로 사용하였다.
그 전값을 기준으로 - people[i - 1] + people[i + x - 1] 해주어야하는데
maxi 값을 기준으로 해서 틀렸다!
 */

class 블로그 {
    fun solve() {
        val (n, x) = readln().split(" ").map{ it.toInt() }
        val people = readln().split(" ").map{ it.toInt() }
        var sum = people.subList(0, x).sumOf{ it }
        var maxi = sum
        var maxiNum = 1

        for(i in 1 .. n - x){
            val new = sum - people[i - 1] + people[i + x - 1]
            if (maxi < new) {
                maxi = new
                maxiNum = 1
            } else if (maxi == new) {
                maxiNum += 1
            }
            sum = new
        }

        if (maxi == 0) {
            println("SAD")
        } else {
            println(maxi)
            println(maxiNum)
        }
    }
}

fun main() {
    블로그().solve()
}