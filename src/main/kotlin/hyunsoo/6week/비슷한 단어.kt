package hyunsoo.`6week`

import kotlin.math.absoluteValue

/**
 * <문제>
 * [비슷한 단어](https://www.acmicpc.net/problem/2607)
 *
 * 영문 알파벳 대문자로 이루어진 두 단어가 다음의 두 가지 조건을 만족하면 같은 구성을 갖는다고 말한다.
 * - 두 개의 단어가 같은 종류의 문자열로 이루어져 있음.
 * - 같은 문자는 같은 개수 만큼 있다.
 *
 * 두 단어가 같은 구성을 갖는 경우,
 * 또는 한 단어에서 한 문자를 더하거나, 빼거나, 하나의 문자를 다른 문자로 바꾸어
 * 나머지 한 단어와 같은 구성을 갖게 되는 경우 서로 비슷한 단어라고 함.
 *
 * 아이디어
 * - 단어를 사전 순으로 정렬하고 하나씩 비교해볼까?
 * - 단어를 정렬하고 차이의 개수를 세보자..! 1이하이면 비슷한 단어!
 *      - DOG / DO 같은 것을 비교할 때 문제가됨.
 * - 하나씩 직접 비교 및 1로 치환 후 1 제거 / 그후 남은 갯수 판별
 *
 */

fun main() {

    val wordCnt = readln().toInt()
    val wordList = mutableListOf<MutableList<Char>>()
    var similarWordCnt = 0

    repeat(wordCnt) {
        val sortedWord = readln().toMutableList()
        wordList.add(sortedWord)
    }
    val baseWord = wordList.first()
    wordList.drop(1).forEach { compareWord ->

        val diffCnt = baseWord - compareWord
        if (diffCnt <= 1) similarWordCnt++

    }

    println(similarWordCnt)

}

operator fun MutableList<Char>.minus(compareList: MutableList<Char>): Int {

    var longString: MutableList<Char>
    var shortString: MutableList<Char>

    // 깊은 복사를 안해주면 객체 자체를 바꿔버림
    if (this.size >= compareList.size) {
        longString = this.toMutableList()
        shortString = compareList
    } else {
        longString = compareList
        shortString = this.toMutableList()
    }

    shortString.forEachIndexed { index, shortChar ->
        val longStringIndex = longString.indexOf(shortChar)
        shortString[index] = '1'
        if (longStringIndex != -1) longString[longStringIndex] = '1'
    }

    return longString.joinToString("").replace("1", "").length
}