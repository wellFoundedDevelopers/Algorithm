package hyunsoo.`3week`

/**
 * 단어에서 임의의 두 부분을 골라 단어를 쪼개고,
 * 쪼개진 단어들의 앞뒤를 뒤집고 다시 원래의 순서대로 합치기
 * 단, 각각은 적어도 길이가 1 이상인 단어여야함.
 *
 */
fun main() {

    val madeWordList = mutableListOf<String>()
    val word = readln()

    val wordLen = word.length

    for (i in 0 until wordLen) {

        for (j in i until wordLen) {

            val startIndex = word.substring(0, i)
            val middleIndex = word.substring(i, j)
            val endIndex = word.substring(j, wordLen)

            if (startIndex.length == 0 ||
                middleIndex.length == 0 ||
                endIndex.length == 0
            ) continue

            val newString = "${startIndex.reversed()}${middleIndex.reversed()}${endIndex.reversed()}"

            madeWordList.add(newString)

        }
    }

    println(madeWordList.minOf { it })

}