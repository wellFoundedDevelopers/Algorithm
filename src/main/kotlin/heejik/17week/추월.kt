package heejik.`14week`

class 추월 {

    fun solve() {

        val n = readln().toInt()
        var overtakenCarCnt = 0
        val carsWithFrontCarCount = mutableMapOf<String, Int>()

        repeat(n) {
            carsWithFrontCarCount[readln()] = it
        }

        repeat(n) {
            val expectFrontCarCnt = carsWithFrontCarCount[readln()]!! + overtakenCarCnt

            if (it > expectFrontCarCnt) {
                overtakenCarCnt += it - expectFrontCarCnt
            }
        }

        println(overtakenCarCnt)
    }
}

fun main() {
    추월().solve()
}
