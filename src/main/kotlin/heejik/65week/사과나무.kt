package heejik.`65week`

import java.io.BufferedReader
import java.io.InputStreamReader

private class 사과나무 {

    fun solve() = with(BufferedReader(InputStreamReader(System.`in`))) {
        val n = this.readLine().toInt()
        val appleCounts = readLine().split(' ').map { it.toInt() } as MutableList
        val appleSum = appleCounts.sum()
        var count1 = 0
        var count2 = 0

        // 3 의 배수여야 짝수가 더 많이 남아도 커버가 가능하다.
        if (appleSum % 3 != 0) {
            println("NO")
            return
        }

        // 2의 배수가 나머지 1보다 많아야 1을 커버 가능하다.
        for (apple in appleCounts) {
            count2 += apple / 2
            count1 += apple % 2
        }

        if (count2 >= count1) {
            println("YES")
        } else {
            println("NO")
        }
    }
}

fun main() {
    사과나무().solve()
}