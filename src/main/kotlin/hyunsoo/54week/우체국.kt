package hyunsoo.`54week`

/**
 *
 * <문제>
 * [우체국](https://www.acmicpc.net/problem/2141)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_우체국` {

    private data class VillageInfo(val villageNum: Long, val peopleCnt: Long)

    private val villageInfoList = mutableListOf<VillageInfo>()

    fun solution() {

        var totalPeopleCnt = 0L
        var prefixSumOfPeopleCnt = 0L
        val n = readln().toInt()

        repeat(n) {

            val (villageNum, peopleCnt) = readln().split(" ").map { it.toLong() }
            villageInfoList.add(VillageInfo(villageNum, peopleCnt))
            totalPeopleCnt += peopleCnt

        }

        val mid = if (totalPeopleCnt % 2 == 0L) totalPeopleCnt / 2 else totalPeopleCnt / 2 + 1

        villageInfoList
            .sortedBy { it.villageNum }
            .forEach {

                val (vil, curPeopleCnt) = it

                prefixSumOfPeopleCnt += curPeopleCnt

                if (mid <= prefixSumOfPeopleCnt) {
                    println(vil)
                    return
                }
            }
    }
}

fun main() {
    전현수_우체국().solution()
}