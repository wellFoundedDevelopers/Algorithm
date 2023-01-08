package byeonghee.`1week`

class Solution_Carpet_byeonghee {
    fun solution(brown: Int, yellow: Int): IntArray {
        val whSum = (brown + 4) / 2
        for(h in 3..whSum/2) {
            val w = whSum - h

            if ((w-2) * (h-2) == yellow) {
                return intArrayOf(w, h)
            }
        }

        return intArrayOf()
    }
}

fun main() {
    val browns = arrayOf(10, 8, 24)
    val yellows = arrayOf(2, 1, 24)
    val ans = arrayOf(intArrayOf(4, 3), intArrayOf(3, 3), intArrayOf(8, 6))

    val sol = Solution_Carpet_byeonghee()
    for(i in ans.indices) {
        println(
            if (sol.solution(browns[i], yellows[i]).contentEquals(ans[i])) "O"
            else "X"
        )
    }
}