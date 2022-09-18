package hyunsoo.`1week`


/**
 *
 * 시간 제한 1초 == 약 10억회의 연산 가능
 *
 * 요구사항
 * - N개의 반지를 가짐.
 * - 각 반지는 대문자 10문자로 이루어진 문자열이 새겨져있음.
 * - 문자열은 시작과 끝이 연결되어있음.
 *
 * - 찾고자하는 문자열이 주어지면 그 문자열을 포함하는 반지가 몇 개인지를 발견하는지 찾기.
 *
 * 아이디어
 * - 반지의 문자열이 가능한 모든 경우를 찾아서 판별?
 *     - ex) ZAAAAXY 라면 AAAAXYZ, AAAXYZA ... 등
 *     1. 찾을 수 있는 모든 반지 문자열의 경우의 수 찾기
 *     2. 반지의 모든 경우의 수에 원하는 문자열을 확인해보기
 *
 */
fun main() {

    val wannaFind = readln()
    val ringCnt = readln().toInt()
    var ans = 0

    repeat(ringCnt) {
        val ring = readln()
        val cases = numberOfCases(ring)

        cases.forEach { ringString ->
            if (ringString.contains(wannaFind)) {
                ans++
                return@repeat
            }
        }
    }

    println(ans)
}

fun numberOfCases(string: String): MutableList<String> {
    val casesList = mutableListOf<String>()

    for (startIndex in 1..string.length) {
        val case = string.substring(startIndex, string.length) + string.substring(0, startIndex)
        casesList.add(case)
    }

    return casesList
}