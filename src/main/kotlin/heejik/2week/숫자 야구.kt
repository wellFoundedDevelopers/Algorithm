package heejik.`2week`

var candidates = mutableListOf<List<Int>>()


fun main() {

    initCandidates()

    repeat(readln().toInt()) {
        val (_ques, strike, ball) = readln().split(' ')
        val ques = _ques.toList().map { it.digitToInt() }

        candidates = candidates.filter {
            isStrike(strike.toInt(),it,ques) && isBall(ball.toInt(),it,ques)
        }.toMutableList()
    }

    println(candidates.size)

}

fun initCandidates() {
    (1..9).forEach {i ->
        (1..9).forEach {j ->
            (1..9).forEach {k ->
                if (i != j && j != k && i != k) {
                    candidates.add(listOf(i, j, k))
                }
            }
        }
    }
}

fun isStrike(cnt: Int, candidate: List<Int>, ques: List<Int>): Boolean {
    var answer = 0
    ques.forEachIndexed { i, q ->
        candidate.forEachIndexed { j, c ->
            if (i == j && q == c){
                answer += 1
            }
        }
    }
    return cnt == answer
}


fun isBall(cnt:Int, candidate:List<Int>, ques:List<Int>) : Boolean{
    var answer = 0
    ques.forEachIndexed { i, q ->
        candidate.forEachIndexed { j, c ->
            if (i != j && q == c){
                answer += 1
            }
        }
    }
    return cnt == answer
}

