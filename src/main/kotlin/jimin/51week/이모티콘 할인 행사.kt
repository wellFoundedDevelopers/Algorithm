package jimin.`51week`

class Solution {

    val discountCombi = mutableListOf<MutableList<Int>>()
    val discountPrices = mutableListOf<MutableList<Int>>()
    val discountType = mutableListOf(10, 20, 30 ,40)

    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        var plusCount = 0
        var maxi = 0

        getCombination(0, emoticons.size, mutableListOf<Int>())
        makeDiscountPrices(emoticons)

        for (i in 0 until discountCombi.size) {
            var result = 0
            var count = 0
            for (user in users) {
                var sumi = 0
                for (j in 0 until discountCombi[i].size) {
                    if (user[0] <= discountType[discountCombi[i][j]]) {
                        sumi += discountPrices[j][discountCombi[i][j]]
                    }
                }

                if (sumi >= user[1]) {
                    count += 1
                } else {
                    result += sumi
                }
            }

            if (count > plusCount) {
                plusCount = count
                maxi = result
            } else if (count == plusCount && result > maxi) {
                maxi = result
            }
        }


        return intArrayOf(plusCount, maxi)
    }

    fun getCombination(num: Int, end: Int, combis: MutableList<Int>) {
        if (num == end) {
            discountCombi.add(combis)
            return
        }

        for (i in 0 until 4) {
            combis.add(i)
            getCombination(num + 1, end, combis.toMutableList())
            combis.removeAt(combis.size - 1)
        }

    }

    fun makeDiscountPrices(emoticons: IntArray) {
        val prices = mutableListOf<Int>()
        for(i in 0 until emoticons.size) {
            for (j in 9 downTo 6) {
                prices.add((emoticons[i] * j * 0.1).toInt())
            }
            discountPrices.add(prices.toMutableList())
            prices.clear()
        }
    }
}