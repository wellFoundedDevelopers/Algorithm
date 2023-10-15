package byeonghee.week48

class 소병희_탑 {

    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val towers = IntArray(n)
            val st = ArrayDeque<Int>()
            val answer = IntArray(n)


            readLine().split(" ").forEachIndexed { i, v ->
                towers[i] = v.toInt()
            }

            for(reach in n-1 downTo 0) {
                while (st.isNotEmpty() && towers[reach] > towers[st.first()]) {
                    answer[st.removeFirst()] = reach + 1
                }

                st.addFirst(reach)
            }

            println(answer.joinToString(" "))
        }
    }
}