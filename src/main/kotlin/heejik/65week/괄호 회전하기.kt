package heejik.`65week`

private class `괄호 회전하기` {
    private val open = listOf('[', '(', '{')
    private val close = listOf(']', ')', '}')

    fun solution(_s: String): Int {
        var answer = 0
        var s = _s
        for (i in s.indices) {
            s.run {
                if (check(this)) answer += 1
                s = leftRotate(s)
            }
        }

        return answer
    }

    private fun leftRotate(s: String): String {
        return s.substring(1) + s.first()
    }

    private fun check(s: String): Boolean {
        val stack = StringBuilder()

        s.forEach { c ->
            if (c in open) {
                stack.append(c)
            } else {
                if (close.indexOf(c) == open.indexOf(stack.lastOrNull())) {
                    stack.deleteAt(stack.length - 1)
                } else {
                    return false
                }
            }
        }
        return stack.isEmpty()
    }
}
