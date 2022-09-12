package hyunsoo.`1week`

/*

    - 사전에는 A E I O U 만을 사용하여 만들 수 있는 길이 5이하의 모든 단어가 수록
    - A가 첫 번째 AA가 두 번째 UUUUU가 마지막
    - A E I O U를 각 1, 2, 3, 4, 5로 변환 시킨 후 1씩 증가시키며 하나씩 탐색해보자.
        - 탐색하기위한 규칙을 찾아보자.
        - 규칙 ㄴㄴ 걍 브루트포스로

    아이디어
    - 타겟이랑 동일해질때까지 12345 관련 문자열 더하기 로직을 구현

    이슈
    - 로직을 잘못 생각해서 U(5)가 등장하면 숫자처럼 자릿수를 다 올려버렸다...
    - 올림하는 로직만 해결하면 풀릴듯

    으아... 올림하는 로직을 숫자처럼 생각해서 진짜 2시간을 버렸네...
    문제 이해 및 설계를 꼼꼼히 해야할 필요가 있겠다.
        - 한 번 꼬이니까 즉시 해결이 안된다...

*/

class Solution {

    fun solution(word: String) = search(converter(word))

    fun converter(word: String): String {
        return word.replace("A", "1")
            .replace("E", "2")
            .replace("I", "3")
            .replace("O", "4")
            .replace("U", "5")

    }

    fun search(target: String): Int {

        var cnt = 0
        var curWord = ""

        while (curWord != target) {
            // 올림을 해야할 경우
            if (haveToRoundUP(curWord)) {

                // 올림로직에 따라 올림처리
                while (curWord[curWord.lastIndex] == '5') {
                    curWord = curWord.substring(0, curWord.length - 1)
                }
                curWord = customPlus(curWord)

                // 올림이 필요없을 경우
            } else {
                // 길이가 5라면 +1씩 증가
                if (isLenFive(curWord)) {
                    curWord = customPlus(curWord)
                    // 길이가 5보다 작다면 뒤에 1을 붙여줌.
                } else {
                    curWord += "1"
                }
            }
            cnt++
        }
        return cnt
    }

    fun isLenFive(word: String) = word.length == 5

    fun haveToRoundUP(word: String) = word.length == 5 && word[word.lastIndex] == '5'

    fun customPlus(word: String) = (word.toInt() + 1).toString()

}

fun main() {
    val test = Solution()

    test.solution("AAAAE").apply{println(this)}
    test.solution("AAAE").apply { println(this) }
    test.solution("I").apply{println(this)}
    test.solution("EIO").apply{println(this)}
}