package heejik.`42week`

class 바이러스 {

    fun solve() {
        val computerCount = readln().toInt()
        val network = List<MutableList<Int>>(size = computerCount) { mutableListOf() }

        repeat(readln().toInt()) {
            val (computer1, computer2) = readln().split(' ').map { it.toInt() }
            network[computer1 - 1].add(computer2 - 1)
            network[computer2 - 1].add(computer1 - 1)
        }

        val virusComputers = mutableSetOf(0)
        val queue = ArrayDeque<Int>()
        queue.add(0)

        while (queue.isNotEmpty()) {
            val virusComputer = queue.removeFirst()
            network[virusComputer].forEach {
                if (virusComputers.add(it)) queue.add(it)
            }
        }

        println(virusComputers.size - 1)
    }
}

fun main() {
    바이러스().solve()
}