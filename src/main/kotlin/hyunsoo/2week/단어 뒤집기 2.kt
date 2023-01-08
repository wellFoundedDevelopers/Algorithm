package hyunsoo.`2week`

import java.io.*

/**
 * 문자열 S가 주어졌을 때, S에서 단어만 뒤집자.
 * S는 아래와 같은 규칙을 가진다.
 * - 알파벳 소문자, 숫자, 공백, <, > 로만 이루어져있다.
 * - 문자열의 시작과 끝은 공백이 아님
 * - < 와 > 가 등장하는 경우 무조건 열림 닫힘 처리가 됨 - 태그
 *
 *
 * 아이디어
 * - < > 태그와 그 안의 문자만 남겨두고 나머지는 다 뒤집어야함.
 * - 그냥 인덱스에 접근해서 뒤집어버리기?
 */

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val s = br.readLine()
    val answer = StringBuilder()
    val tempString = StringBuilder()
    var isOpen = false

    s.forEach { curChar ->

        if (curChar == '>') {

            answer.append(tempString.append(curChar).toString())
            tempString.clear()
            isOpen = false

        } else if (curChar == '<') {

            answer.append(tempString.toString().reversed())
            tempString.clear()
            tempString.append(curChar)
            isOpen = true

        } else if (curChar == ' ' && !isOpen) {

            answer.append(tempString.toString().reversed())
            answer.append(" ")
            tempString.clear()

        } else tempString.append(curChar)

    }

    if (tempString.isNotEmpty()) {
        answer.append(tempString.toString().reversed())
    }

    answer.toString().apply { println(this) }
}