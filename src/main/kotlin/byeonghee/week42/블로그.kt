package byeonghee.week42

import java.io.BufferedReader
import java.io.InputStreamReader

val ans = StringBuilder()
val kSet = ArrayDeque<Int>()

lateinit var s : List<Int>
var k = 0

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    var tc : String = readLine()

    while(tc != "0") {
        val nums = tc.split(" ").map { it.toInt() }
        k = nums[0]
        s = nums.drop(1).sorted()

        pickNum(0, 0)
        ans.appendLine()

        tc = readLine()
    }

    println(ans)
}

fun pickNum(depth: Int, idx: Int) {
    if (idx == s.size || depth == k) {
        pickLotto(StringBuilder(), 0, 0)
        return
    }

    for(i in idx .. s.size - k + depth) {
        kSet.addLast(s[i])
        pickNum(depth + 1, i + 1)
        kSet.removeLast()
    }
}

fun pickLotto(sb: StringBuilder, depth: Int, idx: Int) {
    if (idx == kSet.size || depth == 6) {
        ans.appendLine(sb)
        return
    }

    for(i in idx .. kSet.size - 6 + depth) {
        pickLotto(StringBuilder(sb).apply { append("${kSet[i]} ") }, depth + 1, i + 1)
    }
}