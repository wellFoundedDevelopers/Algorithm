package heejik.`45week`

class `A와 B` {

    private lateinit var origin: StringBuilder
    private lateinit var goal: StringBuilder

    fun solve() {
        setting()
        change().run {
            println(this)
        }
    }

    private fun setting() {
        origin = StringBuilder(readln())
        goal = StringBuilder(readln())
    }

    private fun change(): Int {
        while (origin.length != goal.length) {
            if (goal.last() == 'A') {
                goal.deleteAt(goal.lastIndex)
            } else {
                goal.deleteAt(goal.lastIndex)
                goal.reverse()
            }
        }

        return if (origin.contentEquals(goal)) 1 else 0
    }
}

fun main() {
    `A와 B`().solve()
}