package hyunsoo.`49week`

/**
 *
 * <문제>
 * [할인 행사](https://school.programmers.co.kr/learn/courses/30/lessons/131127)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_할인_행사` {

    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {

        var answer = 0

        for (i in 0 .. discount.size - 10) {

            val wishList = mutableMapOf<String, Int>()

            want.forEachIndexed { index, item ->
                wishList[item] = number[index]
            }

            repeat(10) {
                val index = i + it
                wishList[discount[index]]?.let { pre ->
                    wishList[discount[index]] = pre - 1
                }
            }
            if (wishList.values.all { it <= 0 }) answer += 1
        }

        return answer
    }
}

fun main() {
    전현수_할인_행사().solution(
        arrayOf("banana", "apple", "rice", "pork", "pot"),
        intArrayOf(3, 2, 2, 2, 1),
        arrayOf(
            "chicken",
            "apple",
            "apple",
            "banana",
            "rice",
            "apple",
            "pork",
            "banana",
            "pork",
            "rice",
            "pot",
            "banana",
            "apple",
            "banana"
        )
    ).apply {
        println(this)
    }
}