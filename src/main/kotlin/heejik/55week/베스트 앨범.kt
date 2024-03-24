package heejik.`55week`

class `베스트 앨범` {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {

        return genres.indices.groupBy { genres[it] }
            .toList()
            .sortedByDescending { it.second.sumOf { plays[it] } }
            .map { it.second.sortedByDescending { plays[it] }.take(2) }
            .flatten()
            .toIntArray()
    }
}