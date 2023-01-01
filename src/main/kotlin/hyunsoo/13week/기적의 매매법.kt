package hyunsoo.`13week`

/**
 *
 * <문제>
 * [기적의 매매법](https://www.acmicpc.net/problem/20546)
 *
 * - 준현이는 살 수 있을 때 최대한 산다.
 *
 * - 성민이는 33 매매법을 사용한다.
 * - 전량 매수, 전량 매도
 * - 3일 연속 가격이 전일 대비 상승하면 전량 매도
 * - 3일 연속 가격이 전일 대히 하락하면 전량 매수
 */

data class Wallet(
    var remainedCash: Int = 0,
    var ownedStockCnt: Int = 0,
) {

    fun sellStock(price: Int) {
        remainedCash += ownedStockCnt * price
        ownedStockCnt = 0
    }

    fun buyStock(price: Int) {
        ownedStockCnt += remainedCash / price
        remainedCash %= price
    }

    fun getStockWorth(price: Int) = ownedStockCnt * price + remainedCash
}

fun main() {

    val juneWallet = Wallet()
    val sungWallet = Wallet()

    readln().toInt().apply {
        juneWallet.remainedCash = this
        sungWallet.remainedCash = this
    }

    val stockList = readln().split(" ").map { it.toInt() }
    var lastStockPrice = stockList.first().apply {
        if (this <= juneWallet.remainedCash) {
            juneWallet.buyStock(this)
        }
    }

    var continuouslyIncreasedCnt = 0
    var continuouslyDecreasedCnt = 0

    stockList.drop(1).forEach { todayStockPrice ->

        // 준은 그냥 무지성 풀매수
        if (todayStockPrice <= juneWallet.remainedCash) {
            juneWallet.buyStock(todayStockPrice)
        }

        if (lastStockPrice < todayStockPrice) {
            continuouslyIncreasedCnt++
            continuouslyDecreasedCnt = 0
        } else if (todayStockPrice < lastStockPrice) {
            continuouslyIncreasedCnt = 0
            continuouslyDecreasedCnt++
        }

        if (3 <= continuouslyIncreasedCnt) {
            sungWallet.sellStock(todayStockPrice)
        } else if (3 <= continuouslyDecreasedCnt) {
            sungWallet.buyStock(todayStockPrice)
        }

        lastStockPrice = todayStockPrice

    }

    if (sungWallet.getStockWorth(stockList.last()) < juneWallet.getStockWorth(stockList.last())) {
        println("BNP")
    } else if (juneWallet.getStockWorth(stockList.last()) < sungWallet.getStockWorth(stockList.last())) {
        println("TIMING")
    } else {
        println("SAMESAME")
    }


}