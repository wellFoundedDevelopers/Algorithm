package byeonghee.week49

class 소병희_할인행사 {
    var answer = 0
    val wantedMap = HashMap<String, Int>()
    val q = ArrayDeque<String>()

    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        var rest = number.sum()

        want.forEachIndexed { i, v ->
            wantedMap[v] = number[i]
        }

        discount.forEach {
            q.addLast(it)
            if (wantedMap[it] != null) {
                if (wantedMap[it]!! > 0) rest--
                wantedMap[it] = wantedMap[it]!! - 1
            }

            if (q.size == 10) {
                if (rest == 0) answer++
                val pop = q.removeFirst()
                if (wantedMap[pop] != null) {
                    wantedMap[pop] = wantedMap[pop]!! + 1
                    if (wantedMap[pop]!! > 0) rest++
                }
            }
        }

        return answer
    }
}