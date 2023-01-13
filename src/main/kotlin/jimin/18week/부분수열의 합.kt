package jimin.`18week`

/*
<문제>
[부분수열의 합](https://www.acmicpc.net/problem/1182)

<구현 방법>
수열을 정렬한 후 dfs를 돌면서 수열의 합 sum을 구했다.
이때 sum과 s를 곱했을 때 음수이면 서로의 부호가 다른 것이니
이때의 sum이 s보다 클 경우 s를 만들 수 없게 되기 때문에 return을 해서 백트래킹이 되도록 하였다.

<트러블 슈팅>
처음에는 visited라는 list를 두어 방문한 것을 체크했다.
하지만 순서가 중요하지 않은 조합이기 때문에 굳이 필요없다는 것을 깨닫고
sum만 가지고 있도로 하였다.
*/

var n = 0
var s = 0
var result = 0
fun main() {
    readln().split(" ").map { it.toInt() }.apply {
        n = first()
        s = last()
    }
    val numbers = readln().split(" ").map { it.toInt() } as MutableList
    numbers.sort()
    getNumber(numbers, 0, 0)
    println(result)
}

fun getNumber(numbers: MutableList<Int>, idx: Int, sum: Int) {
    if (idx != 0) {
        if (sum * s <= 0 && sum > s) {
            return@getNumber
        } else if (sum == s) {
            result += 1
        }
    }

    for (i in idx until n) {
        getNumber(numbers, i + 1, sum + numbers[i])
    }
}