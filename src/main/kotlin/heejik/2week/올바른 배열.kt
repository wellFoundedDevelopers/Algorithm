package heejik.`2week`

import java.lang.Integer.max

fun main() {

    val li = arrayListOf<Int>()

    repeat(readln().toInt()) {
        li.add(readln().toInt())
    }

    var answer = 0

    li.forEach {
        val tmp = it until it+5
        val cnt = tmp.count { num ->
            li.contains(num)
        }
        answer = max(cnt,answer)
    }
    println(5-answer)
}