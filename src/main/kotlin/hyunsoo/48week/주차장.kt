package hyunsoo.`48week`

import java.util.LinkedList
import java.util.Queue

/**
 *
 * <문제>
 * [주차장](https://www.acmicpc.net/problem/5464)
 *
 * - 아이디어
 * 차가 주차장에 도착했을 때
 * - 비어있는 공간이 있으면 번호가 가장 작은 곳에 주차
 * - 비어있는 공간이 없으면 큐로 대기
 *
 * - 트러블 슈팅
 *
 */
class `전현수_주차장` {

    fun solution() {

        val (parkingLotSize, carCnt) = readln().split(" ").map { it.toInt() }

        val parkingLot = IntArray(10001)

        val priceInfo = IntArray(10001)
        val weightInfo = IntArray(10001)

        var totalFee = 0

        val queue: Queue<Int> = LinkedList()

        repeat(parkingLotSize) {
            priceInfo[it + 1] = readln().toInt()
        }

        repeat(carCnt) {
            weightInfo[it + 1] = readln().toInt()
        }

        repeat(2 * carCnt) {

            val input = readln().toInt()

            // 들어오는 경우
            if (0 < input) {

                val carNum = input

                var allOccupied = true

                for (index in 1..parkingLotSize) {
                    if (parkingLot[index] == 0) {
                        allOccupied = false
                        parkingLot[index] = carNum
                        break
                    }
                }

                if (allOccupied) {
                    queue.add(carNum)
                }

            } else {
                val carNum = input.toPositive()
                val parkingLotNum = parkingLot.indexOf(carNum)

                totalFee += priceInfo[parkingLotNum] * weightInfo[carNum]
                parkingLot[parkingLotNum] = 0
                // 큐 처리

                if (queue.isNotEmpty()) {
                    val carNum = queue.poll()
                    parkingLot[parkingLotNum] = carNum
                }
            }
        }

        parkingLot.forEachIndexed { index, carNum ->
            if (index == 0 || carNum == 0) return@forEachIndexed
            totalFee += priceInfo[index] * weightInfo[carNum]
        }

        println(totalFee)

    }

    private fun Int.toPositive() = this * -1
}

fun main() {
    전현수_주차장().solution()
}