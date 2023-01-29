package hyunsoo.`20week`

import kotlin.math.absoluteValue
import kotlin.math.hypot
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sqrt

/**
 *
 * <문제>
 * [별자리 만들기](https://www.acmicpc.net/problem/4386)
 *
 * n개의 별들을 이어서 별자리를 하나 만들 것임.
 * 별자리의 조건은 다음과 같다.
 * - 별자리를 이루는 선은 서로 다른 두 별을 일직선으로 이은 형태이다.
 * - 모든 별들을 별자리 위의 선을 통해 서로 직/간접적으로 이어져 있어야 한다. <- 스패닝 트리!
 *
 * - 아이디어
 *
 * 모든 별들을 연결시키는 그래프를 만들고, 거기서 크루스칼 알고리즘을 사용하여 최소 스패닝 트리를 구하기..!!
 *
 * - 트러블 슈팅
 *
 */
class `전현수_별자리_만들기` {

    data class CostData(val a: Int, val b: Int, val cost: Double)
    data class Star(val x: Double, val y: Double)

    private val stars = mutableListOf<Star>()

    private val starCnt = readln().toInt()

    private val parent = IntArray(starCnt + 1) { it }

    private val costTable = mutableListOf<CostData>()
    private var leastCost = 0.0

    fun solution() {

        repeat(starCnt) {
            readln().split(" ").map { it.toDouble() }.apply {
                stars.add(
                    Star(this.first(), this.last())
                )
            }
        }

        // 비용 구해서 간선 만들기
        for (targetIndex in 0 until starCnt) {

            val target = stars[targetIndex]

            for (otherIndex in targetIndex + 1 until starCnt) {

                val other = stars[otherIndex]
                val cost = getDistance(target, other)

                costTable.add(
                    CostData(targetIndex, otherIndex, cost)
                )

            }

        }


        costTable
            .sortedBy { it.cost }
            .forEach { data ->
                val (a, b, cost) = data

                val aParent = findParent(a)
                val bParent = findParent(b)

                if (aParent != bParent) {
                    union(aParent, bParent)
                    leastCost += cost
                }

            }

        println(leastCost)

    }

    private fun findParent(target: Int): Int {
        return if (parent[target] == target) target
        else findParent(parent[target])
    }

    private fun union(aParent: Int, bParent: Int) {
        if (aParent < bParent) {
            parent[bParent] = aParent
        } else {
            parent[aParent] = bParent
        }
    }

    private fun getDistance(aStar: Star, bStar: Star): Double {

        val diffOfX = (bStar.x - aStar.x).absoluteValue
        val diffOfY = (bStar.y - aStar.y).absoluteValue

        return hypot(diffOfX, diffOfY).roundToHundredthsPlace()
    }

    private fun getDistanceWithPowAndSqrt(aStar: Star, bStar: Star): Double {

        val diffOfX = (bStar.x - aStar.x).absoluteValue
        val diffOfY = (bStar.y - aStar.y).absoluteValue

        return sqrt(diffOfX.pow(2.0) + diffOfY.pow(2.0)).roundToHundredthsPlace()
    }

    private fun Double.roundToHundredthsPlace(): Double {
        return round((this * 100)) / 100
    }
}

fun main() {
    전현수_별자리_만들기().solution()
}