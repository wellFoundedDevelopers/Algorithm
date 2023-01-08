package heejik.`14week`

class `별 찍기 - 19` {

    private lateinit var paper: MutableList<MutableList<Char>>

    fun solve() {
        val n = readln().toInt()
        val size = (4 * (n - 1)) + 1
        paper = MutableList(size) { MutableList(size) { ' ' } }

        draw(rule = n, cnt = 0)

        print(paper.joinToString("\n") {
            it.joinToString("")
        })
    }

    private fun draw(rule: Int, cnt: Int) {
        if (rule == 0) return

        val star = (4 * (rule - 1)) + 1

        for (i in (cnt * 2) until (cnt * 2) + star) {
            paper[cnt * 2][i] = '*' // 위
            paper[i][cnt * 2] = '*' // 왼
            paper[(cnt * 2) + star - 1][i] = '*' //아래
            paper[i][(cnt * 2) + star - 1] = '*' // 오른
        }

        draw(rule - 1, cnt + 1)
    }
}

fun main() {
    `별 찍기 - 19`().solve()
}