package hyunsoo.`8week`

/**
 * <문제>
 * [문자열 집합](https://www.acmicpc.net/problem/14425)\
 *
 * n개의 문자열로 이루어진 집합 S가 주어짐
 * 입력으로 들어오는 M개의 문자열 중에서 집합 Sㅇ에 포함되어있는 것이 총 몇 개인지 구해라
 *
 * 이슈
 * - n개의 문자열 집합은 종복없이 들어오고 m개의 확인할 문자열은 중복으로 들어온다
 * - 첨에는 m개의 문자열들을 set에 add하는 식으로 구현했더니 m개의 문자열이 중복되면 ans가 증가하게 되었음
 * - 그래서 contains로 확인하도록 수정했는데... 이럴거면 Set을 사용할 이유가 있나?
 */
fun main() {

    val (setCnt, stringCnt) = readln().split(" ").map { it.toInt() }
    val stringSet = mutableSetOf<String>()
    var ans = 0

    repeat(setCnt) {
        stringSet.add(readln())
    }
    repeat(stringCnt) {
        stringSet.contains(readln()).apply {
            if (this) ans++
        }
    }
    println(ans)
}
