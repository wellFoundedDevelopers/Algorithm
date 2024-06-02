package hyunsoo.`65week`

import java.util.*
 /**
 *
 * <문제>
 * [괄호 회전하기](https://school.programmers.co.kr/learn/courses/30/lessons/76502)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_괄호_희전하기` {

    private val opens = listOf('(', '[', '{')

    fun solution(s: String): Int {
        var answer = 0

        val target = s + s

        var start = 0
        repeat(s.length) {
            if (target.slice(start until start + s.length).isAlright()) answer++
            start++
        }

        return answer
    }

     private fun String.isAlright(): Boolean {

         val stack = Stack<Char>()

         this.forEachIndexed { index, ch ->

             if (ch in opens) {
                 stack.add(ch)
             }
             else {

                 if (stack.isEmpty()) return false

                 when (stack.peek()) {
                     '(' -> {
                         if (ch == ')') stack.pop()
                         else return false
                     }
                     '[' -> {
                         if (ch == ']') stack.pop()
                         else return false
                     }
                     '{' -> {
                         if (ch == '}') stack.pop()
                         else return false
                     }
                 }
             }
         }

         return stack.isEmpty()

     }
}

fun main() {
    전현수_괄호_희전하기().solution("[](){}")
}