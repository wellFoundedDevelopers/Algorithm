package byeonghee.`13week`

import java.io.*

class 소병희_기적의매매법 {

    companion object {
        const val BNP = 0
        const val TIMING = 1

        val br = BufferedReader(InputStreamReader(System.`in`))

        lateinit var restStock : MutableList<Int>
        lateinit var restCash : MutableList<Int>

        fun solve() {
            val cash = br.readLine().toInt()
            val priceList = br.readLine().split(" ").map { it.toInt() }
            restStock = MutableList(2) { 0 }
            restCash = MutableList(2) { cash }

            var upDown = 0

            for (i in 0 until priceList.size - 1) {
                val price = priceList[i]
                if (price <= restCash[BNP]) {
                    buyStock(BNP, price)
                }

                if (upDown == 3) {
                    restCash[TIMING] += restStock[TIMING] * price
                    restStock[TIMING] = 0
                }
                else if (upDown == -3) {
                    buyStock(TIMING, price)
                }

                if (priceList[i + 1] > price) {
                    upDown = (upDown + 1).coerceAtLeast(1)
                }
                else if (priceList[i + 1] < price) {
                    upDown = (upDown - 1).coerceAtMost(-1)
                }
                else upDown = 0
            }

            restCash[BNP] += restStock[BNP] * priceList.last()
            restCash[TIMING] += restStock[TIMING] * priceList.last()

            if (restCash[BNP] > restCash[TIMING]) println("BNP")
            else if (restCash[BNP] < restCash[TIMING]) println("TIMING")
            else println("SAMESAME")
        }

        fun buyStock(who: Int, price: Int) {
            restStock[who] += restCash[who] / price
            restCash[who] %= price
        }
    }
}

fun main() {
    소병희_기적의매매법.solve()
}