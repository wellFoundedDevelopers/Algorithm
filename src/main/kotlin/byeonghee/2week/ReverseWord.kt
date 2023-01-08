package byeonghee.`2week`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val S = readLine()
    val regex = Regex("<[a-z\\d\\s]+>?")
    val split = regex.split(S)
    val tags = regex.findAll(S).map { it.value }.toList()

    val sb = StringBuilder()
    var tagIndex = 0

    split.forEach { word ->
        if (word.isNotBlank()) {
            sb.append(word.split(" ").map{ it.reversed() }.joinToString(" "))
        }
        if (tagIndex < tags.size) {
            sb.append(tags[tagIndex++])
        }
    }

    println(sb)
}
