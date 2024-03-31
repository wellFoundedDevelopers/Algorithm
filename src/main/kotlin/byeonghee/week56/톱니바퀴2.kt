package byeonghee.week56

class 소병희_톱니바퀴2 {
    companion object {
        const val RIGHT = 2
        const val LEFT = 6

        val bm = 1 shl 8

        fun solve() = with(System.`in`.bufferedReader()) {
            val t = readLine().toInt()
            val wheels = IntArray(t) { readLine().reversed().toInt(2) }
            val k = readLine().toInt()
            val q = ArrayDeque<IntArray>()

            fun getBit(i: Int, x: Int) : Int {
                return (wheels[i] shr x) % 2
            }

            fun rotate(i: Int, d: Int) {
                if (d == -1) wheels[i] = getBit(i, 7) shl 7 + wheels[i] shr 1
                else wheels[i] = getBit(i, 7) + (wheels[i] shl 1) % bm
            }

            repeat(k) {
                val (_i, _d) = readLine().split(" ").map { it.toInt() }

                if (_i > 1) q.add(intArrayOf(_i-2, _d, RIGHT, getBit(_i-1, LEFT)))
                if (_i < t) q.add(intArrayOf(_i, _d, LEFT, getBit(_i-1, RIGHT)))

                rotate(_i-1, _d)

                while(q.isNotEmpty()) {
                    val (i, d, from, cog) = q.removeFirst()

                    if (from != RIGHT && getBit(i, LEFT) != cog) {
                        if (i < t-1) q.add(intArrayOf(i+1, -1 * d, from, getBit(i, RIGHT)))
                        rotate(i, d * -1)
                    }
                    if (from != LEFT && getBit(i, RIGHT) != cog) {
                        if (i > 0) q.add(intArrayOf(i-1, -1 * d, from, getBit(i, LEFT)))
                        rotate(i, d * -1)
                    }
                }
            }

            println(wheels.count { it % 2 == 1 })
        }
    }
}

fun main() {
    소병희_톱니바퀴2.solve()
}