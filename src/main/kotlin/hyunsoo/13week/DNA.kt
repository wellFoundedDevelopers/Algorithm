package hyunsoo.`13week`

/**
 *
 * <문제>
 * [DNA](https://www.acmicpc.net/problem/1969)
 *
 * Hamming Distance는 길이가 같은 DNA에서 각 위치의 문자가 다른 것
 * 길이가 M인 N개의 DNA 중 Hamming Distance이 가장 작은 DNA를 구하기
 *
 * 문제 이해를 못해서 고생했습니다...
 */

fun main() {

    val alphabetMap = hashMapOf<Char, Int>()
    val dnaList = mutableListOf<String>()
    val (dnaCnt, dnaLength) = readln().split(" ").map { it.toInt() }
    val dnaBuilder = StringBuilder()
    var hammingDistance = 0

    repeat(dnaCnt) {
        dnaList.add(readln())
    }

    for (i in 0 until dnaLength) {
        for (j in 0 until dnaCnt) {
            val curAlphabet = dnaList[j][i]
            alphabetMap[curAlphabet] =
                alphabetMap.getOrDefault(curAlphabet, 0) + 1
        }

        val mostFrequentAlphabet = alphabetMap
            .toSortedMap()
            .maxBy { it.value }.key

        hammingDistance +=
            alphabetMap.filter { it.key != mostFrequentAlphabet }
                .map { it.value }.sum()

        dnaBuilder.append(mostFrequentAlphabet)
        alphabetMap.clear()
    }

    println(dnaBuilder)
    println(hammingDistance)

}
