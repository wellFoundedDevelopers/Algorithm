package heejik.`11week`

class `팰린드롬 만들기` {

    fun solve() {
        val name = readln().map { it.toString() }.toMutableList()
        println(changePalindrome(name))
    }

    private fun changePalindrome(name: MutableList<String>): String {
        var solo = ""
        var haveSolo = false
        name.toSet().forEach { a ->
            if (name.count { b -> a == b } % 2 == 1) {
                if (haveSolo) return "I'm Sorry Hansoo"
                solo = a
                haveSolo = true
            }
        }

        var answer = ""

        name.remove(solo)
        answer += solo

        name.sortedDescending().forEachIndexed { index, s ->
            if (index % 2 == 1) {
                answer = s + answer
            } else {
                answer += s
            }
        }

        return answer
    }
}

fun main() {
    `팰린드롬 만들기`().solve()
}