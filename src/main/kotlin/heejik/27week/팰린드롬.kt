package heejik.`27week`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.properties.Delegates

class 팰린드롬 {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var n by Delegates.notNull<Int>()
    lateinit var numList: List<Int>
    lateinit var dp: MutableList<MutableList<Boolean>>

    fun solve() {
        n = readln().toInt()
        numList = readln().split(' ').map { it.toInt() }
        dp = MutableList(n) { MutableList(n) { true } }
        getDp()

        repeat(readln().toInt()) {
            val (start, end) = br.readLine().split(' ').map { it.toInt() }
            bw.write("${if (dp[start - 1][end - 1]) 1 else 0}\n")
        }

        bw.flush()
    }

    private fun getDp() {
        repeat(n) { i ->
            for (j in i+1 until n) {
                dp[i][j] = isPalindrome(i, j)
            }
        }
    }

    private fun isPalindrome(start: Int, end: Int): Boolean {
        val offset = (end - start) / 2

        repeat(offset+1) {
            if (numList[start + it] != numList[end - it]) return false
        }

        return true
    }
}

fun main() {
    팰린드롬().solve()
}