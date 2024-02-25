package heejik.`51week`

class `주차 요금 계산` {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        val answer: MutableList<Pair<String, Int>> = mutableListOf()
        val carsInParkingLot = mutableMapOf<String, Int>()
        val carsOutParkingLot = mutableMapOf<String, Int>()

        val (defaultTime, defaultFee, unitTime, unitFee) = fees

        records.forEach {
            val (_time, carNumber, sort) = it.split(' ')
            val time = _time.timeToMinute()

            // 입차
            if (sort == "IN") {
                carsInParkingLot[carNumber] = time
            }
            // 출차
            else {
                val inTime = carsInParkingLot.remove(carNumber)!!
                val useTime = time - inTime
                carsOutParkingLot[carNumber] = (carsOutParkingLot[carNumber] ?: 0) + useTime

            }
        }

        // 주차장에 남은 차 계산
        carsInParkingLot.forEach { car ->
            carsOutParkingLot[car.key] = (carsOutParkingLot[car.key] ?: 0) + "23:59".timeToMinute() - car.value
        }

        carsOutParkingLot.forEach { car ->
            // 가격 구하기
            val useTime = car.value
            val price = if (useTime <= defaultTime) {
                defaultFee
            } else {
                val unitPrice = if ((useTime - defaultTime) % unitTime == 0) {
                    ((useTime - defaultTime) / unitTime) * unitFee
                } else {
                    (((useTime - defaultTime) / unitTime) + 1) * unitFee
                }
                defaultFee + unitPrice
            }
            answer.add(car.key to price)
        }


        return answer.sortedBy { it.first }.map { it.second }.toIntArray()
    }

    fun String.timeToMinute(): Int {
        val (hour, minute) = this.split(':').map { it.toInt() }

        return (hour * 60) + minute
    }
}


fun main() {
    `주차 요금 계산`().solution(
        fees = intArrayOf(180, 5000, 10, 600),
        records = arrayOf(
            "05:34 5961 IN",
            "06:00 0000 IN",
            "06:34 0000 OUT",
            "07:59 5961 OUT",
            "07:59 0148 IN",
            "18:59 0000 IN",
            "19:09 0148 OUT",
            "22:59 5961 IN",
            "23:00 5961 OUT"
        )
    ).also {
        println(it.contentToString())
    }

}