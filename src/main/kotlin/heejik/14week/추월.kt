package heejik.`14week`

/*
* 앞에 있는 차 중에 원래 앞에 있던 애들이 아니고 이미 추월한 애가 아닌
애의 숫자들의 합
*
*/
class 추월 {

    fun solve() {

        val n = readln().toInt()
        val carsWithFrontCars = mutableMapOf<String, List<String>>()
        val inFrontCars = mutableListOf<String>()
        val outFrontCars = mutableListOf<String>()
        val overtakenCars = mutableListOf<String>()

        repeat(n) {
            val inCar = readln()
            carsWithFrontCars[inCar] = inFrontCars.toMutableList()
            inFrontCars.add(inCar)
        }

        repeat(n) {
            val outCar = readln()
            overtakenCars.addAll(outFrontCars.filter { it !in carsWithFrontCars[outCar]!! && it !in overtakenCars })
            outFrontCars.add(outCar)
        }

        println(overtakenCars.size)
    }
}

fun main() {
    추월().solve()
}
