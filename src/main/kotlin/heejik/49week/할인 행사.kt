package heejik.`49week`

data class Goods(
    val name: String,
    val count: Int
)

class Solution {
    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        var answer = 0
        val shoppingBasket = mutableListOf<String>()
        val wantGoods = mutableListOf<Goods>()
        val totalCount = number.sum()

        want.forEachIndexed { index, goods ->
            wantGoods.add(Goods(name = goods, count = number[index]))
        }

        discount.forEach first@{ goods ->
            if (shoppingBasket.size == totalCount) {
                shoppingBasket.removeFirst()
            }
            shoppingBasket.add(goods)

            wantGoods.forEach { goods ->
                if (shoppingBasket.count { it == goods.name } != goods.count) return@first
            }
            answer++
        }

        return answer
    }
}
