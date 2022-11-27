package heejik.`11week`

class 예산 {

    fun solve() {
        val n = readln().toInt()

        val province = readln().split(' ').map { it.toInt() }

        val m = readln().toDouble()
        val answer = mutableListOf<Int>()
        var rest = m
        var badProvince = false
        var cnt = 0

        province.sorted().forEach {
            val average = rest / (n - cnt)
            if (badProvince) {
                answer.add(average.toInt())
                return@forEach
            }
            if (it < average) {
                answer.add(it)
                rest -= it
                cnt++
            } else {
                badProvince = true
                answer.add(average.toInt())
            }
        }
        println(answer.maxOrNull())
    }
}

fun main() {
    예산().solve()
}