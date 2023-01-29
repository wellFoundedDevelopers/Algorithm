package hyunsoo.`20week`

/**
 *
 * <문제>
 * [에너지 모으기](https://www.acmicpc.net/problem/16198)
 *
 *
 * - 아이디어
 *
 * 1 ~ n개의 구슬이 있다면 2 ~ n-1의 구슬들을 뺄 수 있음.
 * 2 ~ n-1을 뽑을 수 있는 경우들의 순열을 구해보자..!
 * 경우의 수가 많지는 않으니까
 *
 * - 트러블 슈팅
 * 사탄이 둘렸을까?... 왜 2개부터 뽑는다고 생각했지???
 *
 */
class `전현수_에너지_모으기` {

    private val marbleCnt = readln().toInt()
    private val pickOrder = mutableListOf<Int>()
    private val pickedCheck = BooleanArray(marbleCnt)
    private var maxEnergy = 0
    private lateinit var energyWeightData: List<Int>

    fun solution() {

        energyWeightData = readln().split(" ").map { it.toInt() }

        for (depth in 1 until marbleCnt - 1) {
            makePermutations(0, depth)
        }

        println(maxEnergy)
    }

    private fun makePermutations(cnt: Int, depth: Int) {

        if (cnt == depth) {
            calculateEnergy()
            return
        }

        for (index in 1 until marbleCnt - 1) {

            if (pickedCheck[index]) continue

            pickedCheck[index] = true
            pickOrder.add(index)
            makePermutations(cnt + 1, depth)
            pickedCheck[index] = false
            pickOrder.removeLast()

        }
    }

    private fun calculateEnergy() {
        var curEnergy = 0
        val isRemove = BooleanArray(marbleCnt)

        pickOrder.forEach { pickIndex ->

            isRemove[pickIndex] = true

            var leftIndex = pickIndex - 1
            while (isRemove[leftIndex]) {
                leftIndex--
            }

            var rightIndex = pickIndex + 1
            while (isRemove[rightIndex]) {
                rightIndex++
            }

            curEnergy += energyWeightData[leftIndex] * energyWeightData[rightIndex]

        }

        if (maxEnergy < curEnergy) maxEnergy = curEnergy
    }
}

fun main() {
    전현수_에너지_모으기().solution()
}