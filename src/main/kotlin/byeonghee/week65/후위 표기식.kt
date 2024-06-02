package byeonghee.week65

class 소병희_후위표기식 {
    companion object {
        val op = charArrayOf('+', '-', '*', '/', '(', ')')

        fun solve() = with(System.`in`.bufferedReader()) {
            val st = ArrayDeque<Char>()
            val sb = StringBuilder()
            val pri = HashMap<Char, Int>()
            pri.put('+', 1)
            pri.put('-', 1)
            pri.put('*', 2)
            pri.put('/', 2)
            pri.put('(', 0)
            pri.put(')', 0)

            readLine().forEach { i ->
                if (i !in op) sb.append(i)
                else if (i == '(') st.add(i)
                else if (i == ')') {
                    while (st.isNotEmpty() && st.last() != '(') {
                        sb.append(st.removeLast())
                    }
                    if (st.isNotEmpty()) st.removeLast()
                }
                else {
                    while(st.isNotEmpty() && pri[st.last()]!! >= pri[i]!!) {
                        sb.append(st.removeLast())
                    }
                    st.add(i)
                }
            }

            while(st.isNotEmpty()) {
                sb.append(st.removeLast())
            }

            println(sb)
        }
    }
}

fun main() {
    소병희_후위표기식.solve()
}