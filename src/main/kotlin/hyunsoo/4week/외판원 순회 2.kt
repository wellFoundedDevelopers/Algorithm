package hyunsoo.`4week`

/**
 *
 *  <문제>
 *  [외판원 순회 2](https://www.acmicpc.net/problem/10971)
 *
 * TSP...
 * 1 - n 번호를 가진 도시들이 있고, 도시들 사이에는 길이 있을 수도 없을 수도 있음.
 * 외판원이 한 도시에서 출발해 n개의 도시를 모두 거쳐 다시 원래의 도시로 돌아오는 순회 여행 경로를 계획
 * 한 번 갔던 도시로는 다시 갈 수 없음.(처음으로 돌아오는 것은 예외)
 * 가장 적은 비용을 들이는 여행 계획을 세우고자 한다.
 * 도시간에 이동하는데 드는 비용은 행렬 W[i][j] 형태로 주어짐 i -> j로 가는 비용
 * 비용은 대칭적이지 않음. w[i][j] != w[j][i]
 *
 */

data class Root<T>(val start: T, val end: T)

val cityCnt = readln().toInt()
val distanceData = mutableListOf<List<Int>>()
val tempRoot = mutableListOf<Int>()
val checkVisitCities = BooleanArray(cityCnt) { false }
var theLeastRootExpenses = Int.MAX_VALUE
fun main() {


    repeat(cityCnt) {
        val rowData = readln().split(" ").map { it.toInt() }
        distanceData.add(rowData)
    }

    findRoot(0, cityCnt)

    println(theLeastRootExpenses)
}

fun findRoot(cnt: Int, depth: Int) {

    if (cnt == depth) {
        var curExpenses = 0

        tempRoot.getRoot().forEach { root ->
            val expenses = distanceData[root.start][root.end]
            if (expenses == 0) return
            curExpenses += expenses

        }
        if (curExpenses < theLeastRootExpenses) {
            theLeastRootExpenses = curExpenses
        }
        return
    }
    for (index in 0 until cityCnt) {
        if (checkVisitCities[index]) continue

        checkVisitCities[index] = true
        tempRoot.add(index)
        findRoot(cnt + 1, depth)
        tempRoot.removeAt(tempRoot.lastIndex)
        checkVisitCities[index] = false
    }

}

fun <T> MutableList<T>.getRoot(): List<Root<T>> {
    val rootList = mutableListOf<Root<T>>()

    this.forEachIndexed { index, num ->

        val newRoot = if (index + 1 < this.size) {
            Root(num, this[index + 1])
        } else {
            Root(num, this.first())
        }
        rootList.add(newRoot)
    }

    return rootList.toList()
}
