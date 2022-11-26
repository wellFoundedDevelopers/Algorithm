package hyunsoo.`11week`

/**
 * <문제>
 * [팰린드롬 만들기](https://www.acmicpc.net/problem/1213)
 *
 * - 알파벳 인덱스 배열을 만들고 거기에 알파벳의 개수들을 저장하기
 * -
 */

val alphabetCntArray = IntArray(26)

fun main() {

    readln().toList().forEach { alphabet ->
        alphabetCntArray[alphabet - 'A']++
    }

    when (oddCntAlphabetCnt()) {
        in 0..1 -> {
            val palindrome = StringBuilder()
            var delimeter = ""
            alphabetCntArray.forEachIndexed { alphabetIndex, cnt ->

                if (cnt % 2 != 0) delimeter = ('A' + alphabetIndex).toString()

                repeat(cnt / 2) {
                    palindrome.append('A' + alphabetIndex)
                }

            }
            println(palindrome.toString() + delimeter + palindrome.reversed())
        }

        else -> {
            println("I'm Sorry Hansoo")
        }
    }

}

fun oddCntAlphabetCnt(): Int {
    var oddCnt = 0
    alphabetCntArray.forEach { cnt ->
        if (cnt % 2 != 0) oddCnt++
    }
    return oddCnt
}