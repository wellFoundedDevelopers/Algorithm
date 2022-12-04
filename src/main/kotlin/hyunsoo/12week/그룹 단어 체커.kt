package hyunsoo.`12week`


/**
 * <문제>
 * [그룹 단어 체커](https://www.acmicpc.net/problem/1316)
 *
 * 문자열을 순차탐색하며 등장한 문자들을 list에 담는다.
 * 이전의 단어와 현재의 단어가 다를 때 바뀐단어가 기존에 등장했다면 그룹단어가 아님!!
 */
fun main() {

    val wordCnt = readln().toInt()
    var groupWordCnt = 0

    repeat(wordCnt) {

        val existedWordList = mutableListOf<Char>()
        val targetWord = readln()
        var lastChar = targetWord.first().apply {
            existedWordList.add(this)
        }

        targetWord.drop(1).forEach { curChar ->
            if (lastChar != curChar) {
                if (curChar in existedWordList) {
                    return@repeat
                } else {
                    existedWordList.add(curChar)
                    lastChar = curChar
                }
            }
        }
        groupWordCnt++
    }

    println(groupWordCnt)
}
