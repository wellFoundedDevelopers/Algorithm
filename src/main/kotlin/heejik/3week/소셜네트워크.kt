package heejik.`3week`

fun main() {

    val (n, m) = readln().split(' ').map { it.toInt() }
    var time = 0
    val answer = arrayListOf<Int>()
    val friend = mutableMapOf<Int, MutableSet<Int>>()
    repeat(m) {
        val (a, b) = readln().split(' ').map { it.toInt() }
        friend[a] = friend[a]?.apply { add(b) } ?: mutableSetOf(b)
        friend[b] = friend[b]?.apply { add(a) } ?: mutableSetOf(a)
    }

    while (!friend.keys.all { friend[it]?.count() == n - 1 }) {
        time += 1
        var cnt = 0
        val newFriend = mutableMapOf<Int, MutableSet<Int>>()
        for (me in friend.keys) {
            val friends = friend[me]!!
            for (other in friend.keys.filter { it != me }) {
                if (other in friends) {  // 내 친구라면
                    friend[other]?.forEach {
                        if (it != me) {
                            newFriend[me] = newFriend[me]?.apply { add(it) } ?: mutableSetOf(it)
                        }
                    }
                }
            }
        }
        friend.keys.forEach {
            val tmp = friend[it]?.count() ?: 0
            friend[it]?.addAll(newFriend[it] ?: return@forEach)
            cnt += friend[it]?.count()!! - tmp
        }
        answer.add(cnt/2)
    }

    println(time)
    answer.forEach {
        println(it)
    }
}