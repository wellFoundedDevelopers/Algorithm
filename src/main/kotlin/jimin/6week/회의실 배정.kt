package jimin.`6week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[회의실 배정] (https://www.acmicpc.net/problem/1931)

<구현 방법>
meetingList를 회의시작시간, 회의종료시간 기준으로 정렬하였다.
정렬 기준을 여러개로 지정하기 : meetingList.sortWith(compareBy({ it.first }, {it.second}))
정렬 후 max값을 회의 시작 시간으로 없데이트 해주었다.

<트러블 슈팅>
처음에는 index로 접근하려했는데 그렇게 하니 메모리 초과가 났다.
최댓값의 index 구하는 방법 : val maxIdx = nums.indices.maxBy { nums[it] } ?: -1
 */



fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    var meetingList = mutableListOf<Pair<Int, Int>>()
    repeat(readLine().toInt()) {
        val input = readLine().split(" ").map { it.toInt() }
        meetingList.add(Pair(input[0], input[1]))
    }

    meetingList.sortWith(compareBy({ it.first }, {it.second})) //compareByDescending //PriorityQueue도 써보기! 입력받은걸 정렬하려고 하면 더 빠르다고 한다.
    //compareBy{}.thenBy{} 도 있다.
    meetingList.reverse()

    var max = meetingList[0].first
    var num = 1

    for (i in 1 until meetingList.size) {
        if (meetingList[i].second <= max){
            max = meetingList[i].first
            num += 1
        }
    }
    println(num)
}

