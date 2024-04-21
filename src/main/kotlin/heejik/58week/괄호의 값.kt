class `괄호의 값`() {
    fun solve() {
        val input = readln()
        val stack = ArrayDeque<Char>()
        var totalScore = 0
        var score = 1
        for (i in input.indices) {
            when (input[i]) {
                '(' -> {
                    score *= 2
                    stack.add(input[i])
                }

                '[' -> {
                    score *= 3
                    stack.add(input[i])
                }

                ')' -> {
                    if (stack.lastOrNull() != '(') {
                        totalScore = 0
                        break
                    }
                    if (input[i - 1] == '(') {
                        totalScore += score
                    }
                    score /= 2
                    stack.removeLast()
                }

                ']' -> {
                    if (stack.lastOrNull() != '[') {
                        totalScore = 0
                        break
                    }
                    if (input[i - 1] == '[') {
                        totalScore += score
                    }
                    score /= 3
                    stack.removeLast()
                }
            }
        }
        if (stack.isNotEmpty()) totalScore = 0
        print(totalScore)
    }
}

fun main() {
    `괄호의 값`().solve()
}