package jimin.`18week`

/*
<문제>
[거짓말](https://www.acmicpc.net/problem/1043)

<구현 방법>
우선 파티 정보를 입력받으면서 n명의 파티원들이 누구와 함께 파티를 하는지 map에 담아주었다.
그런 다음 setTree() 함수를 통해 truePeople들의 map에 방문하면서,
map의 value를 truePeople에 넣어주는 식으로 bfs로 완탐을 하게 하였다.
완탐하면서 truePeople을 구하고 partyInfo를 돌면서 truePeople이 없는 파티만 세도록 result를 구했다.

<트러블 슈팅>
bfs 까묵어서 구글링함
*/

val partyPeople = mutableMapOf<Int, MutableSet<Int>>()
lateinit var truePeople: MutableSet<Int>
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    truePeople = readln().split(" ").map { it.toInt() }.drop(1).toMutableSet()
    val partyInfo = mutableListOf<List<Int>>()
    repeat(n) {
        partyPeople[it + 1] = mutableSetOf()
    }
    repeat(m) {
        val people = readln().split(" ").map { it.toInt() }.drop(1)
        people.forEach { person ->
            people.forEach {
                if (person != it) {
                    partyPeople[person]?.add(it)
                }
            }
        }
        partyInfo.add(people)
    }

    setTrue(MutableList(n){false})
    var result = 0
    partyInfo.forEach{ party ->
        if(party.all{it !in truePeople}){
            result += 1
        }
    }
    println(result)
}

fun setTrue(visited: MutableList<Boolean>) {
    val queue = truePeople.toMutableList()
    while (queue.isNotEmpty()) {
        val person = queue.removeAt(0)

        visited[person - 1] = true
        partyPeople[person]?.forEach{
            if (visited[it - 1].not()) {
                queue.add(it)
                truePeople.add(it)
            }
        }
    }
}