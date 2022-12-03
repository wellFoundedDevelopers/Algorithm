package jimin.`12week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[트럭](https://www.acmicpc.net/problem/13335)

<구현 방법>
다리 길이만큼의 list를 만들어서 그 리스트에 트럭을 끝에서 부터 넣고 앞으로 밀어주는 방식으로 풀었다.

<트러블 슈팅>
*/

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, w, l) = readLine().split(" ").map { it.toInt() }
    val tracks = readLine().split(" ").map { it.toInt() }.toMutableList()
    val bridge = MutableList(w) { 0 }

    var time = 1
    var next = 1
    bridge[w - 1] = tracks.first()
    while (bridge.sum() != 0) {
        bridge.removeFirst()
        if (next < n && bridge.sum() + tracks[next] <= l) {
            bridge.add(tracks[next])
            next += 1
        } else {
            bridge.add(0)
        }
        time += 1
    }
    println(time)
}
