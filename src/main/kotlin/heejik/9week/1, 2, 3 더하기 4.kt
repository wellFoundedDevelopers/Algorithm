package heejik.`9week`

// 답보풀: https://ku-hug.tistory.com/m/209

class `1, 2, 3 더하기 4` {

    fun solve() {

        val cnt = MutableList(10001) { 1 }

        for (i in 2..10000) {
            cnt[i] += cnt[i-2]
        }

        for (i in 3..10000) {
            cnt[i] += cnt[i-3]
        }
        repeat(readln().toInt()) {
            println(cnt[readln().toInt()])
        }
    }
}

fun main() {
    `1, 2, 3 더하기 4`().solve()
}