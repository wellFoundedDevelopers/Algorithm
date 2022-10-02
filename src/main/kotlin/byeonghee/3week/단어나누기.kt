package byeonghee.`3week`

import java.io.BufferedReader
import java.io.InputStreamReader

val br = BufferedReader(InputStreamReader(System.`in`))

val revStrBuilder = StringBuilder()
var firstSubWord = ""
var secondSubWord = ""

var length = 0

fun main() {
    val word = br.readLine().trim().also { length = it.length }

    revStrBuilder.append(
        word.substring(0, length - 2).map { c ->
            firstSubWord = c + firstSubWord
            firstSubWord
        }.minOf { it }
    )

    revStrBuilder.append(
        word.substring(revStrBuilder.length, length - 1).map { c ->
            secondSubWord = c + secondSubWord
            secondSubWord
        }.minOf { it }
    )

    revStrBuilder.append(
        word.substring(revStrBuilder.length).reversed()
    )

    println(revStrBuilder)
}