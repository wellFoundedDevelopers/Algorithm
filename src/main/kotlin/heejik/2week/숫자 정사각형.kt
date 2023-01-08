package heejik.`2week`

import java.lang.Integer.max
import java.lang.Integer.min

fun main() {

    val (n, m) = readln().split(' ').map { it.toInt() }
    val rect = arrayListOf<String>()
    var answer = 1
    val standLength = min(n,m)

    repeat(n){
        rect.add(readln())
    }
    for (stand in 1..standLength) {
        for (i in 0..n - stand) {
            for (j in 0..m - stand) {
                val standIdx = stand - 1
                if (rect[i][j] == rect[i + standIdx][j] && rect[i + standIdx][j] == rect[i][j + standIdx] &&
                    rect[i][j + standIdx] == rect[i + standIdx][j + standIdx]
                ) {
                    answer = max(answer, stand*stand)
                }
            }
        }
    }
    println(answer)
}