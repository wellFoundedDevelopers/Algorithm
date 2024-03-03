package heejik.`52week`

import kotlin.math.max

class `택배 배달과 수거하기` {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {

        var distance = 0L
        var saveDelivery = 0
        var savePickup = 0

        for (i in n - 1 downTo 0) {
            var visit = 0
            while (saveDelivery < deliveries[i] || savePickup < pickups[i]) {
                visit++
                saveDelivery += cap
                savePickup += cap
            }

            saveDelivery -= deliveries[i]
            savePickup -= pickups[i]

            distance += 2 * visit * (i + 1)
        }

        return distance
    }
}


fun main() {
    `택배 배달과 수거하기`().solution(1, 5, intArrayOf(0, 0, 1, 0, 0), intArrayOf(0, 0, 0, 0, 0)).also {
        println(it)
    }
}