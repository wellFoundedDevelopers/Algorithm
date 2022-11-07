package hyunsoo.`8week`

/**
 * <문제>
 * [단축키 지정](https://www.acmicpc.net/problem/1283)
 *
 * N개의 옵션이 존재.
 * 위에서부터 차례대로 각 옵션에 단축키를 의미하는 대표 알파벳을 지정함.
 * - 왼쪽부터 오른쪽으로 단어들의 첫 글자를 순차 탐색. 만약 해당 알파벳이 단축키로 지정되어있지 않으면 단축키로 등록
 * - 모든 단어의 첫 글자가 이미 지정되어있다면, 알파벳을 기준으로 순차탐색
 * - 다 안되면 그냥 냅두기
 *
 *
 */

val shortcutMap = mutableSetOf<String>(" ")

fun main() {
    val optionCnt = readln().toInt()
    val optionList = Array(optionCnt) {
        readln().trim()
    }

    optionList.forEach { option ->
        checkWord(option)
    }
}

fun checkWord(option: String) {

    var isOptioned = false
    val shortCutCommand = mutableListOf<String>()
    val wordList = option.split(" ")

    wordList.forEach { word ->
        val firstAlphabet = word.first()
        // 없다면 - 옵션 등록 및 출력
        if (isOptioned.not() &&
            shortcutMap.add(firstAlphabet.uppercase())
        ) {
            isOptioned = true
            shortCutCommand.add(word.toShortCut(0))
        } else {
            shortCutCommand.add(word)
        }

    }

    if (isOptioned) {
        println(shortCutCommand.joinToString(" "))
    } else {
        checkAlphabet(option)
    }
}

fun checkAlphabet(option: String) {
    var isOptioned = false
    val shortCutCommand = mutableListOf<String>()

    option.forEachIndexed { index, alphabet ->
        if (isOptioned.not() &&
            shortcutMap.add(alphabet.uppercase())
        ) {
            shortCutCommand.add("[$alphabet]")
            isOptioned = true
        } else {
            shortCutCommand.add("$alphabet")
        }
    }

    if (isOptioned) {
        println(shortCutCommand.joinToString(""))
    } else {
        println(option)
    }
}

fun String.toShortCut(index: Int): String {
    val tempMutableChar = this.toMutableList()
    tempMutableChar.add(index + 1, ']')
    tempMutableChar.add(if (index == 0) 0 else index, '[')
    return tempMutableChar.joinToString("")
}
