package jimin.`27week`

/*
<문제>
[친구 네트워크](https://www.acmicpc.net/problem/4195)

<구현 방법>
서로소 집합을 이용해서 friends라는 그래프를 그렸다.
friends는 자식 -> 부모 이기 때문에, 부모 -> 자식을 알기 위해 numbers를 만들었다.
numbers는 2개의 노드의 부모를 다시 설정할 때 부모가 될 것에 자식이 원래 가진 자식의 개수를 더해서 만들었다.
<트러블 슈팅>
시간 초과 - 처음에 자식을 mutableSet으로 해서 모두 알게하고 size를 반환하게 했더니 시간초과 났다..
틀렸습니다 - setParent한 다음에 getParent를 한번씩 또 해줘야만 해결되었다.
*/


class `친구 네트워크` {
    val friends = mutableMapOf<String, String>()
    val children = mutableMapOf<String, Int>()
    fun solve() {
        val n = readln().toInt()
        repeat(n) {
            val f = readln().toInt()
            repeat(f) {
                val (a, b) = readln().split(" ")
                if (friends[a] == null) {
                    friends[a] = a
                    children[a] = 1
                }
                if (friends[b] == null) {
                    friends[b] = b
                    children[b] = 1
                }
                setParent(a, b)
                getParent(a)
                getParent(b)
                println(children[friends[a]])
            }
            friends.clear()
            children.clear()
        }
    }

    fun getParent(name: String): String {
        if (name != friends[name]) {
            friends[name] = getParent(friends[name]!!)
        }
        return friends[name]!!
    }

    fun setParent(first:String, second: String) {
        val firstParent = getParent(first)
        val secondParent = getParent(second)

        if (firstParent < secondParent) {
            friends[secondParent] = firstParent
            children[firstParent] = children[firstParent]!! + children[secondParent]!!
        } else if (firstParent > secondParent){
            friends[firstParent] = secondParent
            children[secondParent] = children[secondParent]!! + children[firstParent]!!
        }
    }
}

fun main() {
    `친구 네트워크`().solve()
}