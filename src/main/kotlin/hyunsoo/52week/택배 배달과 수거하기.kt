package hyunsoo.`52week`

/**
 *
 * <문제>
 * []()
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_택배_배달과_수거하기` {

    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {

        var answer = 0L
        var lastFarthest = n - 1

        while (deliveries.any { it != 0 } || pickups.any { it != 0 }) {

            var farthestDeliverPos = -1
            var farthestPickupsPos = -1

            for (index in lastFarthest downTo 0) {

                if (farthestDeliverPos == -1 && deliveries[index] != 0) {
                    farthestDeliverPos = index
                }

                if (farthestPickupsPos == -1 && pickups[index] != 0) {
                    farthestPickupsPos = index
                }
            }

            var cntForDeliver = cap
            var cntForPickup = cap

            for (index in farthestDeliverPos downTo 0) {

                if (cntForDeliver == 0) break

                val neededBoxes = deliveries[index]

                if (neededBoxes > 0) {

                    if (neededBoxes > cntForDeliver) {
                        deliveries[index] -= cntForDeliver
                        cntForDeliver = 0
                    } else {
                        cntForDeliver -= neededBoxes
                        deliveries[index] = 0
                    }

                }

            }

            for (index in farthestPickupsPos downTo 0) {

                if (cntForPickup == 0) break

                val neededBoxes = pickups[index]

                if (neededBoxes > 0) {

                    if (neededBoxes > cntForPickup) {
                        pickups[index] -= cntForPickup
                        cntForPickup = 0
                    } else {
                        cntForPickup -= neededBoxes
                        pickups[index] = 0
                    }

                }

            }

            lastFarthest = maxOf(farthestDeliverPos, farthestPickupsPos)
            answer += ((lastFarthest + 1) * 2)

        }

        return answer
    }
}

fun main() {
    전현수_택배_배달과_수거하기()
        .solution(4, 5, intArrayOf(1, 0, 3, 1, 2), intArrayOf(0, 3, 0, 4, 0))
        .apply {
            println(this)
        }
}