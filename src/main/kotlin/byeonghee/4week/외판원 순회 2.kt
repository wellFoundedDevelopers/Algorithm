package byeonghee.`4week`

import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))

var n = 0
var complete = 0
var adjacency = Array(0){ IntArray(0) }
val dp = Array(1 shl 10){ IntArray(10) { -1 } }

data class Travel(val city: Int, val route: Int)

fun getBit(city: Int) : Int {
    return 1 shl city
}

fun getOnes(bits: Int) : Int {
    var num = bits
    var ans = 0
    while(num > 0) {
        ans += num and 1
        num /= 2
    }
    return ans
}

fun getBeforeList(route: Int, first: Int) : List<Travel> {
    val list = mutableListOf<Travel>()
    for(i in 0 until n) {
        if (i == first) continue
        getBit(i).run {
            if ((route and this) != 0) {
                list.add(Travel(i, route xor this))
            }
        }
    }
    return list
}

fun dfs(route: Int, first: Int, last: Int) : Int {
    if (getOnes(route) == 1) return adjacency[first][last]
    if (dp[route][last] > -1) return dp[route][last]

    var minLocalCost = Int.MAX_VALUE

    getBeforeList(route, first).forEach { before ->
        if (dp[before.route][before.city] == -1) {
            dp[before.route][before.city] = dfs(before.route, first, before.city)
        }

        if (dp[before.route][before.city] > 0 && adjacency[before.city][last] > 0) {
            minLocalCost = Integer.min(
                minLocalCost,
                dp[before.route][before.city] + adjacency[before.city][last]
            )
        }
    }

    minLocalCost = minLocalCost.let { if (it == Int.MAX_VALUE) 0 else it }
    println("START ${getBit(first).toString(2).padStart(n, '0').reversed()} MOVE ${route.toString(2).padStart(n, '0').reversed()} TO ${(route or getBit(last)).toString(2).padStart(n, '0').reversed()}" +
            " COST $minLocalCost")
    return minLocalCost
}

fun main() {
    n = br.readLine().toInt()
    complete = (1 shl n) - 1

    adjacency = Array(n) { br.readLine().split(" ").map{ it.toInt() }.toIntArray() }

    var answer = Int.MAX_VALUE
    for(i in 0 until n) {
        dp[complete][i] = dfs(complete, i, i)
        answer = if (dp[complete][i] == 0) answer else Integer.min(answer, dp[complete][i])
        dp.forEach { it.fill(-1) }
    }
    answer = answer.let { if (it == Int.MAX_VALUE) 0 else it }

    println(answer)
}