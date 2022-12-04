package heejik.`12week`

import java.lang.Integer.max

class 안녕 {

    data class Greeting(
        val loseHp: Int,
        val getHappy: Int,
    )

    fun solve() {
        var hp = 100
        var happy = 0
        val n = readln().toInt()
        val l = readln().split(' ').map { it.toInt() }
        val j = readln().split(' ').map { it.toInt() }
        val store = MutableList(100) { 0 }

        val greetings = mutableListOf<Greeting>()

        repeat(n) { i ->
            greetings.add(Greeting(loseHp = l[i], getHappy = j[i]))
        }

        greetings.forEach { greeting ->
            if (greeting.loseHp >= 100) return@forEach
            val tmp = mutableListOf<Int>()
            store.forEachIndexed { index, i ->
                if (i != 0) { tmp.add(index) }
            }
            store[greeting.loseHp] = max(store[greeting.loseHp], greeting.getHappy)
            tmp.forEach {
                if (it + greeting.loseHp < 100) {
                    store[it + greeting.loseHp] = max(store[it + greeting.loseHp], store[it] + greeting.getHappy)
                }
            }
        }
        println(store.maxOrNull())
    }
}

fun main() {
    안녕().solve()
}
