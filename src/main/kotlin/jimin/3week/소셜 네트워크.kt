package jimin.`3week`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() : Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val friends = mutableMapOf<Int, MutableSet<Int>>()
    val cloneFriends = mutableMapOf<Int, MutableSet<Int>>()
    for (i in 1 .. n){
        friends[i] = mutableSetOf()
        cloneFriends[i] = mutableSetOf()
    }
    for (i in 0 until m){
        val (a, b) = readLine().split(" ").map { it.toInt() }
        friends[a]?.add(b)
        friends[b]?.add(a)
        cloneFriends[a]?.add(b)
        cloneFriends[b]?.add(a)
    }

    var day = 0
    var new = 0
    var newRelationship = arrayListOf<Int>()
    while (friends.filterValues { it.size == n - 1 }.size < m) {
        friends.forEach { (key, value) ->
            if (value.size == n) return@forEach
            value.forEach { num ->
                cloneFriends[key]?.addAll(ArrayList(friends[num]))
                cloneFriends[key]?.remove(key)
            }
        }
        //println(friends)
        //println(cloneFriends)
        cloneFriends.forEach { (key, value) ->
            new += value.filter { friends[key]?.contains(it) == false }.size
            //println("차이 ${value.filter { friends[key]?.contains(it) == false }}")
            friends[key]?.addAll(value)
        }
        //println(new)
        day += 1
        newRelationship.add(new / 2)
        new = 0
    }
    println(day)
    newRelationship.forEach {
        println(it)
    }
}