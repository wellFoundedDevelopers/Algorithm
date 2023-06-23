package hyunsoo.`39week`

/**
 *
 * <문제>
 * [줄세우기](https://www.acmicpc.net/problem/2631)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */



class `전현수_줄세우기` {

    fun solution() {

        val cnt = readln().toInt()
        val children = mutableListOf<Int>().apply {
            add(0)
        }

        repeat(cnt) {
            val info = readln().toInt()
            children.add(info)
        }

        val dp = IntArray(cnt + 1)

        for (i in 1..cnt) {

            val curNum = children[i]

            // 나보다 작은 수들 중 LIS가 가장 큰 친구 바로 뒤에 붙기
            val lisIndexFromSmallNumsThanCurNum =
                children
                    .filter {
                        it < curNum
                    }.map {
                        dp[children.indexOf(it)]
                    }.maxOf {
                        dp.indexOf(it)
                    }

            dp[i] = dp[lisIndexFromSmallNumsThanCurNum] + 1
        }

        println(cnt - dp.maxOf { it })
    }
}

fun main() {
    전현수_줄세우기().solution()
}