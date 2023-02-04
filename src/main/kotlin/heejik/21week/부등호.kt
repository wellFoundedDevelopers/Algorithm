package heejik.`21week`

class 부등호 {

    lateinit var a: List<String>
    val answers = mutableListOf<String>()

    fun solve() {
        val k = readln().toInt()
        a = readln().split(' ')


        rec(intArrayOf())

        println(answers.max())
        println(answers.min())
    }

    fun rec(nums: IntArray) {

        if (nums.size == a.size + 1) {
            if (check(nums)) {
                answers.add(nums.joinToString(""))
            }
            return
        }

        for (i in 0..9) {
            if (i in nums) continue
            rec(nums.plus(i))
        }
    }

    fun check(nums: IntArray): Boolean {
        a.forEachIndexed { index, s ->
            if (s == ">") {
                if ((nums[index] > nums[index + 1]).not()) return false
            } else {
                if ((nums[index] < nums[index + 1]).not()) return false
            }
        }
        return true
    }
}


fun main() {
    부등호().solve()
}