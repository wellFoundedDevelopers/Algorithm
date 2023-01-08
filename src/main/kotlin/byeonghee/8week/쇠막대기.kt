package byeonghee.`8week`

import java.io.*

class 소병희_쇠막대기 {

    val br = BufferedReader(InputStreamReader(System.`in`))
    var answer = 0
    var acc = 0

    fun solution() {
        val brackets = br.readLine().toCharArray()

        for(i in 1 until brackets.size) {
            if (brackets[i] == '(' && brackets[i-1] == '(') acc++
            else if (brackets[i] == ')') {
                if (brackets[i-1] == '(') {
                    answer += acc
                }
                else if (brackets[i-1] == ')') {
                    answer++
                    acc--
                }
            }
        }
        println(answer)
    }
}

fun main() {
    소병희_쇠막대기().solution()
}