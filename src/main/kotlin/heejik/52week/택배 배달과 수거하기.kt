package heejik.`52week`

import kotlin.math.max

class `택배 배달과 수거하기` {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var distance = 0L
        var boxCount = 0

        while (true) {
            val deliveryPoint = deliveries.indexOfLast { it != 0 }
            val pickupPoint = pickups.indexOfLast { it != 0 }

            if (deliveryPoint == -1 && pickupPoint == -1) break

            distance += max(deliveryPoint + 1, pickupPoint + 1) * 2

            boxCount = cap
            for (i in deliveryPoint downTo 0) {
                if (deliveries[i] == 0) continue

                if (deliveries[i] > boxCount) {
                    deliveries[i] -= boxCount
                    break
                } else {
                    boxCount -= deliveries[i]
                    deliveries[i] = 0
                }
            }
            boxCount = 0

            for (i in pickupPoint downTo 0) {
                if (pickups[i] == 0) continue

                if (pickups[i] > cap - boxCount) {
                    pickups[i] -= cap - boxCount
                    break
                } else {
                    boxCount += pickups[i]
                    pickups[i] = 0
                }
            }
        }

        return distance
    }
}


fun main() {
    `택배 배달과 수거하기`().solution(1, 5, intArrayOf(0, 0, 1, 0, 0), intArrayOf(0, 0, 0, 0, 0)).also {
        println(it)
    }
}