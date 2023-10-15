package heejik.`48week`


import kotlin.math.absoluteValue
import kotlin.properties.Delegates


class 주차장 {

    private var n by Delegates.notNull<Int>()
    private var m by Delegates.notNull<Int>()

    private lateinit var parkingLotsPrice: MutableList<Int>
    private lateinit var parkingLotsCars: MutableList<Int>
    private lateinit var carsWeight: MutableList<Int>
    private val waitCars = ArrayDeque<Int>()

    private val parkingOrders = mutableListOf<Int>()

    fun solve() {
        setting()
        operateParkingLot().also { income ->
            println(income)
        }
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
        }
        parkingLotsPrice = MutableList(n + 1) { 0 }
        parkingLotsCars = MutableList(n + 1) { 0 }
        parkingLotsCars[0] = -1
        carsWeight = MutableList(m + 1) { 0 }

        repeat(n) {
            parkingLotsPrice[it + 1] = readln().toInt()
        }

        repeat(m) {
            carsWeight[it + 1] = readln().toInt()
        }

        repeat(m * 2) {
            parkingOrders.add(readln().toInt())
        }
    }

    private fun operateParkingLot(): Int {
        var income = 0

        parkingOrders.forEach { order ->
            if (order > 0) {        // 주차 or 대기
                if (parkingLotsCars.all { it != 0 }) {// 주차할 공간이 없다면
                    waitCars.add(order)     // 대기
                } else {
                    val parkingLot = parkingLotsCars.indexOfFirst  { it == 0 }  // 주차할 자리 찾기
                    parkingLotsCars[parkingLot] = order // 주차
                }
            } else {        // 주차 빼내기
                val outCar = order.absoluteValue
                val parkingLot = parkingLotsCars.indexOfFirst { it == outCar } // 주차 뺄 자리 찾기
                income += parkingLotsPrice[parkingLot] * carsWeight[outCar]     // 수입!
                parkingLotsCars[parkingLot] = 0      // 주차 빼기

                if (waitCars.isNotEmpty()) {    // 뺀 자리에 기다리는 애 넣기
                    parkingLotsCars[parkingLot] = waitCars.removeFirst()
                }
            }
        }

        return income
    }
}

fun main() {

    주차장().solve()
}