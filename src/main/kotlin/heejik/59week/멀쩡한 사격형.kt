package heejik.`59week`

class Solution {
    fun solution(w: Int, h: Int): Long {
        val gcd = getGcd(w, h)

        val smallW: Long = (w.toLong() / gcd)
        val smallH: Long = (h.toLong() / gcd)

        val liveRectCnt: Long = (smallH - 1) * (smallW - 1)
        val removeRectCnt: Long = (smallH * smallW) - liveRectCnt

        return (w.toLong() * h.toLong()) - (removeRectCnt * gcd)
    }

    private fun getGcd(a: Int, b: Int): Int {
        if (b == 0) return a
        return getGcd(b, a % b)
    }
}

fun main() {
    Solution().solution(100000000, 99999999).also {
        println(it)
    }
}