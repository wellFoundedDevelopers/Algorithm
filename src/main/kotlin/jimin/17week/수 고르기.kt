package jimin.`17week`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Integer.min

/*
<문제>
[추월](https://www.acmicpc.net/problem/2230)

<구현 방법>
repeat으로 전체 인덱스를 돌면서 해당 인덱스부터 끝까지의 새로운 list를 만들어
이를 이분탐색해 해당 인덱스와의 차이를 구해 m이상이면서 가장 작은 수를 구했다.

<트러블 슈팅>
처음에는 등차수열인 줄 알고 그렇게 알고리즘짰다가 실패..
이분탐색 알고리즘 검색해봄
https://velog.io/@kimdukbae/%EC%9D%B4%EB%B6%84-%ED%83%90%EC%83%89-%EC%9D%B4%EC%A7%84-%ED%83%90%EC%83%89-Binary-Search
*/

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val numbers = mutableListOf<Int>()
    repeat(n) {
        numbers.add(readLine().toInt())
    }
    numbers.sort()
    var result = Int.MAX_VALUE
    repeat(n) {
        result = min(binarySearch(numbers.subList(it, n), m), result)
    }
    println(result)
}

fun binarySearch(numberList: MutableList<Int>, m: Int): Int {
    val num = numberList.first()
    var start = 0
    var end = numberList.size - 1
    var mid: Int
    var result = Int.MAX_VALUE
    while (start <= end) {
        mid = (start + end) / 2
        if (numberList[mid] - num == m) {
            result = m
            break
        } else if (numberList[mid] - num > m) {
            result = numberList[mid] - num
            end = mid - 1
        } else {
            start = mid + 1
        }
    }
    return result
}