package jimin.`60week`

//https://st-lab.tistory.com/277

class `공유기 설치` {
    var result = 0

    fun solve() {
        val (n, c) = readln().split(" ").map{ it.toInt() }
        val houses = mutableListOf<Int>()
        repeat(n) {
            houses.add(readln().toInt())
        }
        houses.sort()


        println(binarySearch(n, c, houses))
    }

    fun binarySearch(n: Int, c: Int, houses: MutableList<Int>):Int {
        var start = 1
        var end = houses.last() - houses.first() + 1

        while (start < end) {
            val mid = (start + end) / 2
            val num = check(n, mid, houses)
            
            if (num < c) {
                end = mid
            } else {
                start = mid + 1
            }
        }

        return start - 1
    }

    fun check(n: Int, distance:Int, houses: MutableList<Int>): Int {
        var before = 0
        var num = 1
        for(i in 1 until n) {
            if(houses[i] - houses[before] >= distance) {
                num += 1
                before = i
            }
        }

        return num
    }
}

fun main() {
    `공유기 설치`().solve()
}