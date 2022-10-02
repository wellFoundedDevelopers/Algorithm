package heejik.`3week`

fun main() {

    val word = readln()     // mobitel
    val len = word.length   // 7

    val answer = arrayListOf<String>()

    for (i in 1 until len-1) {    // 1 ~ 5
        for (j in 1 until len-1) {
            for (k in 1 until len-1) {
                if (i + j + k == len) {
                    answer += word.run {
                        substring(0,i).reversed() +
                        substring(i,i+j).reversed() +
                        substring(i+j,i+j+k).reversed()
                    }
                }
            }
        }
    }
    println(answer.minOf { it })    // sorted().first()
}