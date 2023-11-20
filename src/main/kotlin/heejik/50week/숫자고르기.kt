package heejik.`50week`

class 숫자고르기 {

    fun solve() {
        val n = readln().toInt()
        val numbers = MutableList(size = n + 1) { Int.MAX_VALUE }
        repeat(n) {
            numbers[it + 1] = readln().toInt()
        }
        val answers = mutableListOf<Int>()
        for (i in 1..n) {
            val tmp = mutableListOf(i)
            var now = i
            while (true) {
                now = numbers[now]
                if (now == i) {
                    answers.addAll(tmp)
                    break
                }
                if (now in tmp || now in answers) break
                tmp.add(now)
            }
        }
        println(answers.size)
        for(number in answers.sorted()) {
            println(number)
        }
    }
}

fun main() {
    숫자고르기().solve()
}