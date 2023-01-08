package heejik.`13week`

class `🐜 기적의 매매법 🐜` {

    fun solve() {
        val money = readln().toInt()
        val stocks = readln().split(' ').map { it.toInt() }

        val bnf = bnf(money, stocks)
        val timing = timing(money, stocks)

        println(
            if (bnf > timing) "BNP"
            else if (timing > bnf) "TIMING"
            else "SAMESAME"
        )
    }

    private fun bnf(_money: Int, stocks: List<Int>): Int {
        var money = _money
        var stock = 0

        stocks.forEach {
            val cnt = money / it
            money -= it * (cnt)
            stock += cnt
        }

        return money + (stock * stocks.last())
    }

    private fun timing(_money: Int, stocks: List<Int>): Int {
        var money = _money
        var stock = 0

        var upCnt = 0
        var downCnt = 0
        var preStock = stocks.first()

        stocks.forEach {
            if (it > preStock) {
                upCnt++
                downCnt = 0
            } else if (it < preStock) {
                downCnt++
                upCnt = 0
            } else {
                upCnt = 0
                downCnt = 0
            }

            if (upCnt >= 3) {
                money += stock * it
                stock = 0
            }
            if (downCnt >= 3) {
                val cnt = money / it
                money -= it * (cnt)
                stock += cnt
            }

            preStock = it
        }

        return money + (stock * stocks.last())
    }
}

fun main() {
    `🐜 기적의 매매법 🐜`().solve()
}