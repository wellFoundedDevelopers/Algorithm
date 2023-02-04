package jimin.`21week`

/*
<문제>
[부등호](https://www.acmicpc.net/problem/2529)

<구현 방법>
재귀를 이용해 완전탐색을 하였다.

<트러블 슈팅>
*/

class 부등호 {
    var n = 0
    lateinit var signs: List<String>
    val result = mutableListOf<String>()
    fun solve() {
        n = readln().toInt()
        signs = readln().split(" ")

        repeat(10) {
            getNumber(mutableListOf(it))
        }

        println(result.maxOf{ it })
        println(result.minOf{ it })
    }

    fun getNumber(numbers: MutableList<Int>) {
        if (numbers.size == n + 1) {
            result.add(numbers.joinToString(""))
            return@getNumber
        }

        for (i in 0..9) {
            if (i !in numbers &&
                ((signs[numbers.size - 1] == ">" && numbers[numbers.size - 1] > i) ||
                (signs[numbers.size - 1] == "<" && numbers[numbers.size - 1] < i))
            ) {
                numbers.add(i)
                getNumber(numbers)
                numbers.removeLast()
            }
        }
    }

}

fun main() {
    부등호().solve()
}