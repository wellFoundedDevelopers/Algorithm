package jimin.week52

class `종이의 개수` {
    private val result = MutableList(3) { 0 }
    private val DIFFER = 2

    fun solve() {
        val n = readln().toInt()
        val papers = mutableListOf<List<Int>>()
        repeat(n) {
            papers.add(readln().split(" ").map { it.toInt() })
        }

        setRecursion(papers, 0, 0, n)

        result.forEach{
            println(it)
        }
    }

    private fun setRecursion(papers: MutableList<List<Int>>, x: Int, y: Int, divisor: Int) {
        val num = checkPaper(papers, x, y, divisor)
        if (num == DIFFER) {
            for (i in x until x + divisor step divisor / 3) {
                for (j in y until y + divisor step divisor / 3) {
                    setRecursion(papers, i, j, divisor / 3)
                }
            }
        } else {
            result[num + 1] += 1
        }
    }

    private fun checkPaper(papers: MutableList<List<Int>>, x: Int, y: Int, divisor: Int): Int {
        val num = papers[x][y]
        for (i in x until x + divisor) {
            for (j in y until y + divisor) {
                if (papers[i][j] != num) {
                    return 2
                }
            }
        }

        return num
    }
}

fun main() {
    `종이의 개수`().solve()
}