package heejik.`8week`

class `단축키 지정` {

    fun solve() {
        val shortKeys = mutableListOf<Char>()
        val wordsList = mutableListOf<String>()
        val n = readln().toInt()

        repeat(n) {
            wordsList.add(readln())
        }

        wordsList.forEach { words ->
            val wordList = words.split(' ')
            var answer = ""
            var isApplied = false
            wordList.forEach wordList@ { word ->
                if (isApplied) {
                    answer += "$word "
                    return@wordList
                }
                if (shortKeys.contains(word.first().uppercaseChar()).not()) {
                    shortKeys.add(word.first().uppercaseChar())
                    answer += "[${word.first()}]"+word.substring(startIndex = 1)+" "
                    isApplied = true
                } else{
                    answer += "$word "
                }
            }
            if (isApplied) {
                println(answer)
                return@forEach
            }
            answer = ""
            words.forEachIndexed { index, char ->
                if (char == ' ' || shortKeys.contains(char.uppercaseChar())) {
                    return@forEachIndexed
                } else {
                    shortKeys.add(char.uppercaseChar())
                    println(words.substring(startIndex = 0, endIndex = index)+"[$char]"+words.substring(startIndex = index+1))
                    return@forEach
                }
            }
            println(words)
        }
    }
}

fun main() {
    `단축키 지정`().solve()
}