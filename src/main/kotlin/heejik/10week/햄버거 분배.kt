package heejik.`10week`

class `햄버거 분배` {

    fun solve() {

        val (n, k) = readln().split(' ').map { it.toInt() }
        val eat = MutableList(n+1) { false }
        var answer = 0
        val table = readln()

        table.forEachIndexed { index, c ->
            if (c == 'P') {
                for (i in index-k .. index+k){
                    if (i in 0 until n && table[i] == 'H' && eat[i].not()) {
                        eat[i] = true
                        answer+=1
                        break
                    }
                }
            }
        }
        println(answer)
    }
}

fun main() {
    `햄버거 분배`().solve()
}