package heejik.`45week`

import kotlin.math.max
import kotlin.math.min
import kotlin.properties.Delegates

class `연산자 끼워넣기` {

    var n by Delegates.notNull<Int>()
    lateinit var numbers: List<Int>
    lateinit var operators: List<Int>
    var maxAnswer = Int.MIN_VALUE
    var minAnswer = Int.MAX_VALUE
    var plusCount = 0
    var minusCount = 0
    var multiCount = 0
    var divideCount = 0

    fun solve() {
        n = readln().toInt()
        numbers = readln().split(' ').map { it.toInt() }
        operators = readln().split(' ').map { it.toInt() }
        plusCount = operators[0]
        minusCount = operators[1]
        multiCount = operators[2]
        divideCount = operators[3]

        cal(result = numbers[0], count = 1)
        println(maxAnswer)
        println(minAnswer)

    }


    private fun cal(result: Int, count: Int) {
        if (count == n) {
            maxAnswer = max(maxAnswer, result)
            minAnswer = min(minAnswer, result)
            return
        }

        if (plusCount > 0) {
            plusCount--
            cal(result = result + numbers[count], count = count + 1)
            plusCount++
        }
        if (minusCount > 0) {
            minusCount--
            cal(result = result - numbers[count], count = count + 1)
            minusCount++
        }
        if (multiCount > 0) {
            multiCount--
            cal(result = result * numbers[count], count = count + 1)
            multiCount++
        }
        if (divideCount > 0) {
            divideCount--
            if (result < 0) {
                cal(result = ((result * -1) / numbers[count]) * -1, count + 1)
            } else {
                cal(result = (result) / numbers[count], count + 1)
            }
            divideCount++
        }
    }
}


fun main() {
    `연산자 끼워넣기`().solve()
}