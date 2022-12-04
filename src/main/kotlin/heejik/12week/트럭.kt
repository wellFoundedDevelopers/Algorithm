package heejik.`12week`

class 트럭 {

    fun solve() {
        val (n, w, l) = readln().split(' ').map { it.toInt() }
        val trucks = readln().split(' ').map { it.toInt() }.toMutableList()
        var bridge = mutableListOf<Truck>()
        var cnt = 0

        while (trucks.isNotEmpty() || bridge.isNotEmpty()) {
            bridge.forEach { it.time++ }
            bridge = bridge.filter { it.time <= w }.toMutableList()

            if (trucks.isNotEmpty() && bridge.size < w && bridge.sumOf { it.weight } < l) {
                if (trucks.first() + bridge.sumOf { it.weight } <= l) {
                    val truck = trucks.removeFirst()
                    bridge.add(Truck(truck, 1))
                }
            }
            cnt++
        }
        println(cnt)
    }
}

data class Truck(
    val weight: Int, var time: Int
)

fun main() {
    트럭().solve()
}