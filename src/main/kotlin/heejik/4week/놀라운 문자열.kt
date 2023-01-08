package heejik.`4week`

import kotlin.system.exitProcess

fun main() {

    while (true) {
        val s = readln().also { if (it == "*") exitProcess(0) }
        println(s.plusSuffix(isSurprising(s)))
    }
}

fun isSurprising(s: String) : Boolean {
    for (i in 1 until s.length) {
        val li = mutableListOf<String>()
        s.forEachIndexed { index, c ->
            if (index + i >= s.length) return@forEachIndexed
            li.add(c.toString() + s[index + i])
        }
        if (li.toSet().size != li.size) return false
    }
    return true
}

fun String.plusSuffix(isSurprising: Boolean): String {
    return if (isSurprising){
        "$this is surprising."
    } else {
        "$this is NOT surprising."
    }
}