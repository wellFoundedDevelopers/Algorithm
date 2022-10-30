package heejik.`7week`

import kotlin.math.abs


class `차이를 최대로` {
    private var n = 0
    private var sumList = mutableListOf<Int>()
    private var a = listOf<Int>()
    private var check = booleanArrayOf()

    fun solve() {
        n = readln().toInt()
        a = readln().split(' ').map { it.toInt() }
        check = BooleanArray(n) { false }
        dfs(0, 0, 0)
        println(sumList.maxOrNull())
    }

    fun dfs(s: Int, cnt: Int, tmp: Int) {
        if (cnt == n) {
            sumList.add(s)
            return
        }
        for (i in a.indices) {
            if (check[i].not()) {
                check[i] = true
                if (cnt == 0) {
                    dfs(s, 1, a[i])
                } else {
                    dfs(s + abs(tmp - a[i]), cnt + 1, a[i])
                }
                check[i] = false
            }
        }
    }
}


fun main() {

    `차이를 최대로`().solve()
}