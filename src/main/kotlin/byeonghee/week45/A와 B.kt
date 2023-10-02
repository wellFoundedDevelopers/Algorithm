package byeonghee.week45

class 소병희_A와B {

    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val s = readLine().toList()
            val t = readLine().toList()

            var front = 0
            var back = t.lastIndex
            var dir = 1

            while(back - front >= s.size) {
                val compare = if (dir == 1) back else front
                if (dir == 1) back-- else front++

                if(t[compare] == 'B') {
                    dir *= -1
                }
                else if (t[compare] != 'A') {
                    println(0)
                    return@with
                }
            }

            val start = if (dir == 1) front else back
            for(i in s.indices) {
                if (s[i] != t[start + dir * i]) {
                    println(0)
                    return@with
                }
            }

            println(1)
        }
    }
}

fun main() {
    소병희_A와B.solve()
}