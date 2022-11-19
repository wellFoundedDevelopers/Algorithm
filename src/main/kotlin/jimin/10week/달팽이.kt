package jimin.`10week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[달팽이](https://www.acmicpc.net/problem/1913)

<구현 방법>
위와 오른쪽으로 가는 경우에 n만큼 증가한다고하면,
아래와 왼쪽으로 가는 경우는 n+1 만큼 증가한다.

<트러블 슈팅>
1의 위치를 알고자 할때는 while문 안에서 갱신이 안된다는 것을 미쳐 파악하지 못해서 틀렸었다.
처음에 초기화해줄때 1의 위치로 하니 맞았다.
 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    val findNum = readLine().toInt()
    val numList = MutableList(size) { MutableList(size) { 1 } }
    var num = 2
    var x = size / 2
    var y = size / 2
    var tmp = 1
    var pointX = size / 2
    var pointY = size / 2
    kotlin.run {
        while (num <= size * size) {
            //위
            repeat(tmp) {
                numList[--x][y] = num++
                if (num == findNum + 1){
                    pointX = x
                    pointY = y
                }
                if (num > size * size) {
                    return@run
                }
            }


            //오른쪽
            repeat(tmp++) {
                numList[x][++y] = num++
                if (num == findNum + 1){
                    pointX = x
                    pointY = y
                }
            }


            //아래
            repeat(tmp) {
                numList[++x][y] = num++
                if (num == findNum + 1){
                    pointX = x
                    pointY = y
                }
            }

            //왼쪽
            repeat(tmp++) {
                numList[x][--y] = num++
                if (num == findNum + 1){
                    pointX = x
                    pointY = y
                }
            }

        }
    }

    println(numList.map { it.joinToString(" ") }.joinToString("\n"))
    println("${pointX + 1} ${pointY + 1}")
}