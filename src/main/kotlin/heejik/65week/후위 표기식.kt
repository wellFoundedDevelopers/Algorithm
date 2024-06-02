package heejik.`65week`

private class `후위 표기식` {

    val stack = mutableListOf<String>()
    val sb = StringBuilder()

    fun solve() {
        val expression = readln().toList().map { it.toString() }
        expression.forEach {
            if (it !in "+-*/()") {
                sb.append(it)
                return@forEach
            }
            if (it == "(") {
                stack.add(it)
                return@forEach
            }
            if (it == ")") {
                while (true) {
                    val last = stack.removeLast()
                    if (last == "(") {
                        break
                    } else {
                        sb.append(last)
                    }
                }
            }
            if (it in "*/") {
                if (stack.isNotEmpty() && stack.last() in "*/") {
                    sb.append(stack.removeLast())
                }
                stack.add(it)
                return@forEach
            }
            if (it in "+-") {
                while (true) {
                    if (stack.isEmpty() || stack.last() == "(") {
                        break
                    }
                    sb.append(stack.removeLast())
                }
                stack.add(it)
            }
        }
        while (stack.isNotEmpty()) {
            sb.append(stack.removeLast())
        }
        println(sb.toString())
    }
}

fun main() {
    `후위 표기식`().solve()
}