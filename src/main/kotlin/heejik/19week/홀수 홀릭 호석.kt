package heejik.`19week`

class `홀수 홀릭 호석` {

    lateinit var n: List<Int>
    private val answers = mutableListOf<Int>()

    fun solve() {
        setting()
        start()
    }

    private fun setting() {
        n = readln().map { it.digitToInt() }
    }

    private fun start() {
        rec(n, 0)
        println("${answers.min()} ${answers.max()}")
    }

    private fun rec(nums: List<Int>, cnt: Int) {
        when (nums.size) {
            1 -> {
                answers.add(cnt + getOddCount(nums))
                return
            }

            2 -> {
                rec(divide(nums), cnt + getOddCount(nums))
            }

            else -> {
                for (i in 1 until nums.size - 1) {
                    for (j in i + 1 until nums.size) {
                        val listOfNums = listOf(nums.subList(0, i), nums.subList(i, j), nums.subList(j, nums.size))
                        rec(cut(listOfNums), cnt + getOddCount(nums))
                    }
                }
            }
        }
    }


    private fun divide(nums: List<Int>): List<Int> {
        return nums.sum().toString().map { it.digitToInt() }
    }

    private fun cut(listOfNums: List<List<Int>>): List<Int> {
        return listOfNums.sumOf {
            it.joinToString("").toInt()
        }.toString().map { it.digitToInt() }
    }


    private fun getOddCount(numbers: List<Int>): Int {
        return numbers.count { it % 2 == 1 }
    }
}

fun main() {
    `홀수 홀릭 호석`().solve()
}