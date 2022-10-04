package hyunsoo.`4week`

/**
 * 대문자 알파벳으로만 이루어져 있는 문자열이 있음.
 * D-쌍 이라는 것을 정의할 수 있는데,
 * 거리가 D인 두 문자를 순서대로 나열한 것을 D-쌍이라고함.
 * 즉 문자열의 길이가 N이라고할 때 0-쌍부터 N-2쌍까지 정의됨.
 *
 * 어떤 문자열의 모든 D쌍들이 서로 다르면 D 유일하다고 한다.
 *
 * 아이디어
 * - 입력받은 모든 문자열의 쌍을 완탐
 * - set을 이용하여 중복처리하기
 *
 */
fun main() {

    val stringPairList = mutableSetOf<String>()
    loop@ while (true) {

        val string = readln().also {
            if (it == "*") return
        }
        val stringPairBuilder = StringBuilder()

        for (diffCnt in 1..string.length - 2) {
            stringPairList.clear()
            for (startIndex in string.indices) {
                if (diffCnt + startIndex >= string.length) continue

                stringPairBuilder.append(string[startIndex])
                stringPairBuilder.append(string[startIndex + diffCnt])

                if (stringPairList.add(stringPairBuilder.toString()).not()) {
                    printWithResult(string, false)
                    continue@loop
                }
                stringPairBuilder.clear()
            }
        }
        printWithResult(string, true)
    }
}

fun printWithResult(string: String, isSurprise: Boolean) {
    if (isSurprise) {
        println("$string is surprising.")
    } else println("$string is NOT surprising.")
}
