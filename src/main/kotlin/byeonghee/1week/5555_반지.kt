package byeonghee.`1week`

/** 백준 제출 코드 START*/
fun mainForSubmit_5555() {
    val keyword = readln().toRegex()
    val ringN = readln().toInt()
    var count = 0

    (1..ringN).forEach {
        val ringStrTwice = readln().repeat(2)
        if (keyword.containsMatchIn(ringStrTwice)) {
            count++
        }
    }

    println(count)
}
/** 백준 제출 코드 END*/

fun solution(args: Array<String>) : Int {
    var readIdx = 0

    val keyword = args[readIdx++].toRegex()
    val ringN = args[readIdx++].toInt()
    var count = 0

    (1..ringN).forEach {
        val ringStrTwice = args[readIdx++].repeat(2)
        if (keyword.containsMatchIn(ringStrTwice)) {
            count++
        }
    }

    return count
}

fun main() { //for Test
    val tcs = arrayListOf<Array<String>>()
    tcs.add(arrayOf("ABCD", "3", "ABCDXXXXXX", "YYYYABCDXX", "DCBAZZZZZZ"))
    tcs.add(arrayOf("XYZ", "1", "ZAAAAAAAXY"))
    tcs.add(arrayOf("PQR", "3", "PQRAAAAPQR", "BBPQRBBBBB", "CCCCCCCCCC"))

    val ans = arrayOf(2, 1, 2)

    tcs.forEachIndexed { i, tc ->
        println("정답: ${ans[i]}\t내답: ${solution(tc)}")
    }
}