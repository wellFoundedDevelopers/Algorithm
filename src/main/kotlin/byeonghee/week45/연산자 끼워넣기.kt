package byeonghee.week45

class 소병희_연산자끼워넣기 {

    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val nums = readLine().split(" ").map { it.toInt() }

            var min = 1_000_000_000
            var max = -1_000_000_000

            fun dfs(calc: Int, i: Int, restAdd: Int, restSub: Int, restMul: Int, restDiv: Int) {
                if (i == n) {
                    min = minOf(min, calc)
                    max = maxOf(max, calc)
                    return
                }

                if (restAdd > 0) dfs(calc + nums[i], i + 1,restAdd - 1, restSub, restMul, restDiv)

                if (restSub > 0) dfs(calc - nums[i], i + 1, restAdd, restSub - 1, restMul, restDiv)

                if (restMul > 0) dfs(calc * nums[i], i + 1, restAdd, restSub, restMul - 1, restDiv)

                if (restDiv > 0) dfs(calc / nums[i], i + 1, restAdd, restSub, restMul, restDiv - 1)
            }

            val (add, sub, mul, div) = readLine().split(" ").map { it.toInt() }
            dfs(nums[0], 1, add, sub, mul, div)

            println(max)
            println(min)
        }

    }
}

fun main() {
    소병희_연산자끼워넣기.solve()
}