package heejik.`55week`

class `섬 연결하기` {
    fun solution(n: Int, costs: Array<IntArray>): Int {
        val sortedCosts = costs.sortedBy { it[2] }
        var answer = 0

        val visited = mutableSetOf(0)
        while (visited.size != n) {
            run {
                sortedCosts.forEach {
                    val (x, y, cost) = it
                    if (x in visited || y in visited) {
                        if (x in visited && y in visited) return@forEach
                        visited.add(x)
                        visited.add(y)
                        answer += cost
                        return@run
                    }
                }
            }
        }
        return answer
    }
}


fun main() {
    run lit@ {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@lit // local return to the caller of the lambda, i.e. the forEach loop
            print(it)
        }
        print(" done with explicit label")
    }
}