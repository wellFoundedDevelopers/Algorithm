package hyunsoo.`9week`

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * <문제>
 * [배열 복원하기](https://www.acmicpc.net/problem/16967)
 *
 * 크기가 H * W 인 배열 A와 두 정수 X와 Y가 있을 때,
 *
 * 크기가 (H + X) * (W + Y) 인 배열 B는 배열 A와 배열 A를
 * 아래로 X칸, 오른쪽을 Y칸 이동시킨 배열을 겹쳐 만들 수 있음.
 * - 수가 겸쳐지면 수가 합쳐짐.
 *
 */

val br = BufferedReader(InputStreamReader(System.`in`))

fun main() {

    val arrayB = mutableListOf<MutableList<Int>>()
    val (h, w, x, y) = readln().split(" ").map { it.toInt() }

    repeat(h + x) {
        val row = readln().split(" ").map { it.toInt() }.toMutableList()
        arrayB.add(row)
    }

    val baseArrayA = arrayB
        .subList(0, h)
        .map { list ->
            list.subList(0, w)
        }

    val originArrayA = baseArrayA.toMutableList()

    baseArrayA.forEachIndexed { i, list ->
        list.forEachIndexed { j, num ->
            if (i + x < h && j + y < w) originArrayA[i + x][j + y] -= num
        }
    }

    originArrayA.forEach{
        println(it.joinToString(" "))
    }
}