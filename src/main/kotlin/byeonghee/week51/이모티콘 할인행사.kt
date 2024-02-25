package byeonghee.week51

class 소병희_이모티콘할인행사 {

    val answer = IntArray(2)
    val discounts = 10..40 step 10

    lateinit var users: Array<IntArray>
    lateinit var emoticons: IntArray

    fun solution(_users: Array<IntArray>, _emoticons: IntArray): IntArray {
        users = _users
        emoticons = _emoticons

        recursiveDiscount(0, IntArray(users.size))

        return answer
    }

    fun recursiveDiscount(idx: Int, shopped: IntArray) {
        if (idx == emoticons.size) {
            var plus = 0
            var gain = 0

            for(bill in shopped) {
                if (bill == -1) plus++
                else gain += bill
            }

            if (answer[0] < plus) {
                answer[0] = plus
                answer[1] = gain
            }
            else if (answer[0] == plus && answer[1] < gain) {
                answer[1] = gain
            }
            return
        }

        for(discount in discounts) {
            var newPrice = emoticons[idx] / 100 * (100 - discount)
            val newShopped = shopped.clone()

            for((user, data) in users.withIndex()) {
                val (ratio, budget) = data
                if (shopped[user] == -1) continue
                if (discount >= ratio) {
                    newShopped[user] += newPrice
                    if (newShopped[user] >= budget) {
                        newShopped[user] = -1
                    }
                }
            }

            recursiveDiscount(idx + 1, newShopped)
        }
    }
}