package heejik.`61week`

class `하노이 탑` {
    val answers = mutableListOf<Pair<Int, Int>>()

    fun solve() {
        val n = readln().toInt()
        if (n <= 20) {
            hanoi(n, start = 1, tmp = 2, goal = 3)
            println(answers.size)
            for ((s, g) in answers) {
                println("$s $g")
            }
        } else {
            var preCount = "2"
            for (i in 0 until n - 1) {
                val sub = StringBuilder()
                val beyond = mutableListOf<Int>()
//                println("i: $i, preCount: $preCount")
                for (idx in preCount.indices) {
                    val realIdx = preCount.length - 1 - idx
//                    println("i: $i, readlIdx: $realIdx, pre: ${preCount[realIdx]}")
                    val sum = preCount[realIdx].digitToInt() * 2
                    if (sum > 9) {
                        beyond.add(idx + 1)
                    }
                    sub.insert(0, sum.toString().last())
                }
//                println(beyond)
//                println("sub: $sub")
                while (beyond.isNotEmpty()) {
                    val removeIdx = beyond.removeFirst()
                    if (sub.length == removeIdx) {
                        sub.insert(0, 1)
                        continue
                    }
                    val stringIdx = sub.length - 1 - removeIdx
                    if (sub[stringIdx].digitToInt() == 9) {
                        sub[stringIdx] = '0'
                        beyond.add(removeIdx + 1)
                    } else {
                        sub[stringIdx] = sub[stringIdx] + 1
                    }
                }
                preCount = sub.toString()
            }
            for ((idx, c) in preCount.withIndex()) {
                if (idx == preCount.lastIndex) {
                    print(c.digitToInt() - 1)
                } else {
                    print(c)
                }
            }
//            println(preCount)
        }

    }

    private fun hanoi(n: Int, start: Int, tmp: Int, goal: Int) {
        if (n == 1) {
            answers.add(start to goal)
            return
        }
        hanoi(n - 1, start, goal, tmp)
        hanoi(1, start, tmp, goal)
        hanoi(n - 1, tmp, start, goal)
    }
}


fun main() {
    `하노이 탑`().solve()
}

//fun main() {
//    val a = StringBuilder()
//    a.insert(0,6)
//    a.insert(0,1)
//    println(a)
//    println(a[0])
//}