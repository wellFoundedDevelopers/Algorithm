package jimin.`3week`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val friends = mutableMapOf<Int, MutableSet<Int>>()
    val cloneFriends = mutableMapOf<Int, MutableSet<Int>>()
    for (i in 1..n) {
        friends[i] = mutableSetOf()
        cloneFriends[i] = mutableSetOf()
    }
    for (i in 0 until m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        friends[a]?.add(b)
        friends[b]?.add(a)
        cloneFriends[a]?.add(b)
        cloneFriends[b]?.add(a)
    }

    var day = 0
    var newRelationship = arrayListOf<Int>()
    while (friends.filterValues { it.size == n - 1 }.size < m) {
        friends.forEach { (key, value) ->
            if (value.size == n - 1) return@forEach
            value.forEach { num ->
                cloneFriends[key]?.addAll(ArrayList(friends[num]))
                cloneFriends[key]?.remove(key)
            }
        }
        //println(friends)
        //println(cloneFriends)
        newRelationship.add(0)
        cloneFriends.forEach { (key, value) ->
            newRelationship[newRelationship.size - 1] += value.filter { friends[key]?.contains(it) == false }.size
            //println("차이 ${value.filter { friends[key]?.contains(it) == false }}")
            friends[key]?.addAll(value)
        }
        day += 1
        newRelationship[newRelationship.size - 1] = newRelationship[newRelationship.size - 1] / 2
    }
    println(day)
    newRelationship.forEach {
        println(it)
    }
}