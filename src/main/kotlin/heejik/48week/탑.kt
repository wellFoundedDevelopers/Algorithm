package heejik.`48week`

class 탑 {

    data class Top(
        val number: Int,
        val height: Int
    )

    fun solve() {
        val n = readln().toInt()
        val tops = readln().split(' ').mapIndexed { index, s -> Top(index + 1, s.toInt()) }
        val priorTops = ArrayDeque<Top>()
        val answer = MutableList(n) { 0 }

        tops.forEachIndexed { index, top ->
            if (priorTops.isEmpty()) {
                priorTops.add(top)
                return@forEachIndexed
            }
            while (priorTops.isNotEmpty()) {
                val lastPriorTop = priorTops.last()
                if (top.height >= lastPriorTop.height) {
                    priorTops.removeLast()
                } else {
                    answer[index] = lastPriorTop.number
                    break
                }
            }
            priorTops.add(top)
        }

        answer.joinToString(" ").run {
            println(this)
        }
    }
}

fun main() {
    탑().solve()
}
