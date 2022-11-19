package jimin.`10week`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

/*
<문제>
[햄버거 분배](https://www.acmicpc.net/problem/19941)

<구현 방법>
H일 때, 앞뒤를 살피며 P가 있을 경우 X로 바꾸고 num을 갱신한다.

<트러블 슈팅>
처음에는 앞 뒤 양쪽 다 1부터 size까지 증가하면서 살펴봤는데, 이렇게 하니 틀렸다.
앞 쪽은 size부터 1까지 줄여나가며 확인했어야했다.

앞에서 부터 확인하니까, 앞은 size부터 1을 뺀 수로 멀리서 부터 확인해야하고,
뒤는 H내에서 앞쪽이니까 1부터 size까지로 확인했어야한다.

그리고 size가 line보다 클 수 있다는 예외처리를 생각해줬어야한다.
 */

var num = 0
fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (_, size) = readLine().split(" ").map { it.toInt() }
    val line = readLine().chunked(1).toMutableList()
    line.forEachIndexed { index, s ->
        if (s == "H") {
            when (index) {
                0 -> {
                    checkBack(line, index, min(size, line.size - 1))
                }
                line.size - 1 -> {
                    checkFront(line, index, min(size, line.size - 1))
                }
                else -> {
                    if (checkFront(line, index, min(size, index)).not()){
                        checkBack(line, index, min(size, line.size - 1 - index))
                    }
                }
            }
            //println("num $num index $index")
            //println("line $line index $index num $num")
        }

    }
    println(num)
}

fun checkBack(line: MutableList<String>, index: Int, size: Int): Boolean {
    for (i in 1 .. size){
        if (line[index + i] == "P") {
            line[index + i] = "X"
            num += 1
            return true
        }
    }
    return false
}

fun checkFront(line: MutableList<String>, index: Int, size: Int): Boolean {
    for (i in size downTo 1){
        if (line[index - i] == "P") {
            line[index - i] = "X"
            num += 1
            return true
        }
    }
    return false
}