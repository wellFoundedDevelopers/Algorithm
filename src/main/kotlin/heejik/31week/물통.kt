package heejik.`31week`

import kotlin.math.min

class 물통 {

    data class Buckets(
        val a: Int,
        val b: Int,
        val c: Int
    )

    private val visited = mutableListOf<Buckets>()
    private val answers = mutableListOf<Int>()
    private var maxA = 0
    private var maxB = 0
    private var maxC = 0

    fun solve() {
        getInput()
        pourWater(Buckets(0, 0, maxC))
        answers.sorted().forEach {
            print("$it ")
        }
    }

    private fun getInput() {
        readln().split(' ').map { it.toInt() }.run {
            maxA = this[0]
            maxB = this[1]
            maxC = this[2]
        }
    }

    private fun pourWater(buckets: Buckets) {
        if (buckets in visited) return
        visited.add(buckets)

        if (buckets.a == 0) {
            answers.add(buckets.c)
        }

        if (buckets.a != 0) {
            val canB = min(buckets.a + buckets.b, maxB)
            val minusAOfB = canB - buckets.b
            pourWater(buckets.copy(a = buckets.a - minusAOfB, b = canB, c = buckets.c))

            val canC = min(buckets.a + buckets.c, maxC)
            val minusAOfC = canC - buckets.c
            pourWater(buckets.copy(a = buckets.a - minusAOfC, b = buckets.b, c = canC))
        }

        if (buckets.b != 0) {
            val canA = min(buckets.b + buckets.a, maxA)
            val minusBOfA = canA - buckets.a
            pourWater(buckets.copy(a = canA, b = buckets.b - minusBOfA, c = buckets.c))

            val canC = min(buckets.b + buckets.c, maxC)
            val minusBOfC = canC - buckets.c
            pourWater(buckets.copy(a = buckets.a, b = buckets.b - minusBOfC, c = canC))
        }

        if (buckets.c != 0) {
            val canA = min(buckets.c + buckets.a, maxA)
            val minusCOfA = canA - buckets.a
            pourWater(buckets.copy(a = canA, b = buckets.b, c = buckets.c - minusCOfA))

            val canB = min(buckets.c + buckets.b, maxB)
            val minusCOfB = canB - buckets.b
            pourWater(buckets.copy(a = buckets.a, b = canB, c = buckets.c - minusCOfB))
        }
    }
}

fun main() {
    물통().solve()
}