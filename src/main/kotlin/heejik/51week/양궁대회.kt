package heejik.`51week`

class 양궁대회 {
    var maxDifference = 0

    // 점수: 과녁 갯수
    private val answerList = MutableList(11) { 0 }
    var answer: Map<Int, Int> = mapOf()
    fun solution(n: Int, info: IntArray): IntArray {
        shot(n, mutableMapOf(), 0, info)

        answer.forEach { (key, value) ->
            answerList[10 - key] = value
        }
        return answerList.toIntArray().run {
            if (this.all { it == 0 }) intArrayOf(-1) else this
        }
    }

    private fun shot(remainArrow: Int, scoreWithCount: MutableMap<Int, Int>, idx: Int, info: IntArray) {
        if (idx == 10 && remainArrow > 0) {
            scoreWithCount[0] = remainArrow
            shot(remainArrow = 0, scoreWithCount = scoreWithCount, idx = -1, info = info)
            scoreWithCount.remove(0)
        }
        if (remainArrow == 0) {
            var apeachScore = 0
            info.withIndex().filter { it.value != 0 }.forEach {
                if (it.index !in scoreWithCount.keys.map { key -> 10 - key }) {
                    apeachScore += (10 - it.index)
                }
            }
            val difference = scoreWithCount.keys.sum() - apeachScore
            if (difference <= 0) return
            if (difference > maxDifference) {
                maxDifference = difference
                answer = scoreWithCount.toMap()
            }
            else if (difference == maxDifference) {
                for (score in 0..10) {
                    val isAnswerContain = answer.containsKey(score)
                    val isScoreWithCountContain = scoreWithCount.containsKey(score)
                    if (isAnswerContain && isScoreWithCountContain) {
                        if (scoreWithCount[score]!! > answer[score]!!) {
                            maxDifference = difference
                            answer = scoreWithCount.toMap()
                        }
                    } else if (isAnswerContain) {
                        break
                    } else if (isScoreWithCountContain) {
                        maxDifference = difference
                        answer = scoreWithCount.toMap()
                    }
                }
            }
            return
        }
        for (i in idx..9) {
            if (remainArrow - (info[i] + 1) >= 0) {
                scoreWithCount[10 - i] = info[i] + 1
                shot(
                    remainArrow = remainArrow - (info[i] + 1),
                    scoreWithCount = scoreWithCount,
                    idx = i + 1,
                    info = info
                )
                scoreWithCount.remove(10 - i)
            }
        }
    }
}


fun main() {
    양궁대회().solution(
        n = 10,
        info = intArrayOf(0,0,0,0,0,0,0,0,3,4,3)
    ).also {
        println(it.contentToString())
    }
}