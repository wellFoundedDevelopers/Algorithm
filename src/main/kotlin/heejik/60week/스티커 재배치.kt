package heejik.`60week`

import kotlin.math.min

class `스티커 재배치` {

    fun solve() {
        var minPrice = Int.MAX_VALUE

        val (n, m, k) = readln().split(' ').map { it.toInt() }
        // 각 인덱스마다 번호에 따른 알맞은 값 존재
        val stickerAlpha = MutableList(size = m) { 'a' }
        val stickerOut = MutableList(size = m) { 0 }
        val stickerPrice = MutableList(size = m) { 0 }
        val priceOfAlpha = mutableMapOf<Char, Int>()
        val outOfAlpha = mutableMapOf<Char, MutableList<Int>>()

        repeat(m) { id ->
            val (s, d, j) = readln().split(' ')
            stickerAlpha[id] = s.first()
            stickerOut[id] = d.toInt()
            stickerPrice[id] = j.toInt()
            // 각 알파벳마다 최소 가격 리스트업
            priceOfAlpha[s.first()] = min(priceOfAlpha.getOrDefault(s.first(), Int.MAX_VALUE), j.toInt())
            // 각 알파벳마다 최소 떼기 비용 리스트업
            outOfAlpha[s.first()]?.add(d.toInt()) ?: run { outOfAlpha[s.first()] = mutableListOf(d.toInt()) }
        }
//        println(outOfAlpha)
//        println(outOfAlpha)
//        println(cheapestPrice)

        val board = readln().split(' ').map { it.toInt() - 1 }
        val s = readln()

        for (i in 0..board.size - s.length) {
            val outOfAlphaInRange = mutableMapOf<Char, MutableList<Int>>()
            for ((alpha, outPrice) in outOfAlpha) {
                outOfAlphaInRange[alpha] = outPrice.toMutableList()
            }
//            println(outOfAlphaInRange)
            var price = 0
            val trash = mutableListOf<Char>()
            // 먼저 쓰레기통에 다 넣는다.
            for (j in i until i + s.length) {
                val indexOfS = j - i
                val findSticker = s[indexOfS]
                val currentSticker = stickerAlpha[board[j]]
                // s 의 부분과 보드판 위의 알파벳의 부분과 다르면
                if (findSticker != currentSticker) {
                    // 현재 스티커 떼기
                    price += stickerOut[board[j]]
                    // 쓰레기통에 넣기
                    trash.add(currentSticker)
                }
            }

            // 쓰레기통에 넣은 걸 기반으로 재활용 하거나 구매
            for (j in i until i + s.length) {
                val indexOfS = j - i
                val findSticker = s[indexOfS]
                val currentSticker = stickerAlpha[board[j]]
//                println("$i, $j, findSticker: $findSticker, currentSticker, $currentSticker")
                // s 의 부분과 보드판 위의 알파벳의 부분과 다르면
                if (findSticker != currentSticker) {
                    // 쓰레기통에 있다면 재활용
                    if (findSticker in trash) {
                        trash.remove(findSticker)
//                        println("$i, $j, $price, 34343")
                    } else {    // 쓰레기통에 없다면 해당 알파벳 최소 가격으로 구매
                        if (findSticker !in priceOfAlpha) {
                            print(-1)
                            return
                        }
                        if (outOfAlphaInRange[findSticker]!!.min() < priceOfAlpha[findSticker]!!) {
                            val minOutPrice = outOfAlphaInRange[findSticker]!!.min()
                            outOfAlphaInRange[findSticker]!!.remove(minOutPrice)
                            price += minOutPrice
                        } else {
                            price += priceOfAlpha[findSticker]!!
                        }
                    }
                }
            }
//            println(price)
            if (price < minPrice) {
                minPrice = price
            }
        }

        print(if (minPrice == Int.MAX_VALUE) -1 else minPrice)
    }
}

fun main() {
    `스티커 재배치`().solve()
}