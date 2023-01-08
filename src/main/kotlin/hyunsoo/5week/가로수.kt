package hyunsoo.`5week`

/**
 * <문제>
 * [가로수](https://www.acmicpc.net/problem/2485)
 *
 * 이슈
 * - 완탐을 하면 시간 초과
 */
fun main() {

    val plantedTreeCnt = readln().toInt()
    var gcd = 0
    val plantedTreeLocationList = mutableListOf<Int>()
    val distanceDiffList = mutableListOf<Int>()

    repeat(plantedTreeCnt) {
        val curLocation = readln().toInt()
        plantedTreeLocationList.add(curLocation)
    }

    var lastLocation = plantedTreeLocationList.minOrNull()!!
    plantedTreeLocationList.sorted().drop(1).forEach { curLocation ->
        val diffLocation = curLocation - lastLocation
        lastLocation = curLocation
        distanceDiffList.add(diffLocation)

        gcd = if (gcd > diffLocation) {
            getGcd(gcd, diffLocation)
        } else {
            getGcd(diffLocation, gcd)
        }
    }

    println(distanceDiffList.sumOf { (it / gcd) - 1 })
}

fun getGcd(x: Int, y: Int): Int {
    return if (y == 0) x else getGcd(y, x % y)
}