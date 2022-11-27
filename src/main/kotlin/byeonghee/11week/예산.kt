package byeonghee.`11week`

import java.io.*
import java.lang.Integer.min

class 소병희_예산 {

    companion object {
        val br = BufferedReader(InputStreamReader(System.`in`))
        lateinit var budget : List<Int>
        var totalBudget = 0

        fun solve() {
            val n = br.readLine().toInt()
            budget = br.readLine().split(" ").map { it.toInt() }
            totalBudget = br.readLine().toInt()

            if (totalBudget >= budget.sumOf { it }) {
                println(budget.maxOf { it })
            }
            else {
                println(binarySearch(budget.maxOf { it }))
            }

        }

        fun binarySearch(max: Int): Int {
            var lb = 0
            var ub = max + 1
            var mid = 0

            while (ub > lb + 1) {
                mid = (lb + (ub - lb) / 2)
                if (sumCut(mid) <= totalBudget) {
                    if (sumCut(mid + 1) > totalBudget) {
                        return mid
                    }
                    lb = mid
                }
                else {
                    if (sumCut(mid - 1) <= totalBudget) {
                        return mid - 1
                    }
                    ub = mid
                }
            }
            return lb
        }

        fun sumCut(cut: Int) : Int {
            var sum = 0
            for(b in budget) {
                sum += min(cut, b)
            }
            return sum
        }
    }
}

fun main() {
    소병희_예산.solve()
}