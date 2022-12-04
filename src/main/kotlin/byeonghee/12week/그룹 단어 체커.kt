package byeonghee.`12week`

import java.io.*

class 소병희_그룹단어체커 {

    companion object {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var answer = 0
        var curC = '.'
        var isGroupWord = false
        val letterMap = HashMap<Char, Boolean>()

        fun solve() {
            val n = br.readLine().toInt()

            repeat(n) {
                letterMap.clear()
                isGroupWord = true
                curC = '.'

                br.readLine().forEach {  c ->
                    if (curC != c) {
                        if (letterMap.containsKey(c)) {
                            isGroupWord = false
                            return@forEach
                        }
                        letterMap[c] = true
                        curC = c
                    }
                }
                if (isGroupWord) answer++
            }

            println(answer)
        }
    }
}

fun main() {
    소병희_그룹단어체커.solve()
}