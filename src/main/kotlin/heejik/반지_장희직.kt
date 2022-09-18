package heejik

import java.io.BufferedReader
import java.io.InputStreamReader


fun main():Unit= with(BufferedReader(InputStreamReader(System.`in`))) {

    var answer = 0
    val find = readln()

    repeat(readln().toInt()){
        val ring = readln()
        if (check(find,ring)) answer+= 1
    }
    println(answer)

}

fun check(find:String,ring:String) = (ring+ring).contains(find)