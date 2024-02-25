package heejik.`51week`

class `민겸 수` {

    fun solve() {
        readln().run {
            getMaxDecimal().also { println(it) }
            getMinDecimal().also { println(it) }
        }
    }

    private fun String.getMaxDecimal(): String {
        var mStackCount = 0
        val decimal = StringBuilder()

        this.forEach {
            if (it == 'M') mStackCount += 1
            if (it == 'K') {
                decimal.append(5)
                repeat(mStackCount) {
                    decimal.append(0)
                }
                mStackCount = 0
            }
        }
        repeat(mStackCount) {
            decimal.append(1)
        }
        return decimal.toString()
    }

    private fun String.getMinDecimal(): String {
        var mStackCount = 0
        val decimal = StringBuilder()

        this.forEach {
            if (it == 'M') mStackCount += 1
            if (it == 'K') {
                if (mStackCount > 0) {
                    decimal.append(1)
                }
                repeat(mStackCount - 1) {
                    decimal.append(0)
                }
                decimal.append(5)
                mStackCount = 0
            }
        }

        if (mStackCount != 0) {
            decimal.append(1)
            repeat(mStackCount - 1) {
                decimal.append(0)
            }
        }
        return decimal.toString()
    }
}

fun main() {
    `민겸 수`().solve()
}