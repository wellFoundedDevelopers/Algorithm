package heejik.`42week`

class 로또 {
    var answer = StringBuilder()

    fun solve() {
        getInput()
    }

    private fun getInput() {
        while (true) {
            val numbers = readln().split(' ').map { it.toInt() }.drop(1)
            if (numbers.isEmpty()) break
            pickNumber(numbers)
            answer.appendLine()
        }
        print(answer)
    }

    private fun pickNumber(numbers: List<Int>, pickIndex: Int = 0, storeNumbers: List<Int> = emptyList()) {
        if (storeNumbers.size == 6) {
            answer.appendLine(storeNumbers.joinToString(" "))
            return
        }

        for (i in pickIndex until numbers.size) {
            pickNumber(numbers = numbers, pickIndex = i + 1, storeNumbers = storeNumbers.plus(numbers[i]))
        }
    }
}


fun main() {
    로또().solve()
}