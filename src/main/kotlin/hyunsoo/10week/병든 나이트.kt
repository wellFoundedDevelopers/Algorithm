package hyunsoo.`10week`

import java.lang.Math.min

/**
 * <문제>
 * [병든 나이트](https://www.acmicpc.net/problem/1783)
 *
 * 병든 나이트가 N * M 크기 체스판의 가장 왼쪽 아래 칸에 위치해 있음.
 * 병든 나이트는 건강한 보통 체스의 나이트와 다르게 4가지로만 이동이 가능
 * - 2칸 위로, 1칸 오른쪽
 * - 1칸 위로, 2칸 오른쪽
 * - 1칸 아래로, 2칸 오른쪽
 * - 2칸 아래로, 1칸 오른쪽
 *
 * 만약 병든 나이트의 이동 횟수가 4번보다 적지 않다면, 이동 방법을 모두 한 번씩 사용해야 함.
 */
fun main() {

    val (n, m) = readln().split(" ").map { it.toInt() }

    val canVisitCnt = when (n) {

        // 세로가 1이면 이동 불가
        1 -> 1

        // 세로가 2라면 2, 3번 방법으로 이동 가능
        2 -> min(4, (m + 1) / 2)

        // 세로가 3이상이면
        else -> if (m < 7) min(4, m) else m - 2

    }

    println(canVisitCnt)
}

