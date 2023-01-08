package byeonghee.`8week`

import java.io.*

class `소병희_단축키 지정` {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val keySet = HashSet<Char>()

    fun solution() {
        val n = br.readLine().toInt()

        repeat(n) {
            br.readLine().let {  words ->
                words.split(" ").firstOrNull { word ->
                    keySet.add(word[0].lowercaseChar())
                }?.let { key ->
                    println(words.replaceFirst(key, "[${key[0]}]${key.drop(1)}"))
                } ?: words.replace(" ", "").firstOrNull { char ->
                    keySet.add(char.lowercaseChar())
                }?.let { key ->
                    println(words.replaceFirst("$key", "[$key]"))
                } ?: println(words)
            }
        }
    }
}

fun main() {
    `소병희_단축키 지정`().solution()
}