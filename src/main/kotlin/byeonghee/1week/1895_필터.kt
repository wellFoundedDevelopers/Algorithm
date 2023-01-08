package byeonghee.`1week`

/** 백준 제출 코드 START*/
fun main() {
    val R : Int
    val C : Int
    val T : Int
    var count = 0

    readln().trim().split(" ").apply {
        R = first().toInt()
        C = last().toInt()
    }

    val image = arrayListOf<ArrayList<Int>>()

    repeat(R) {
        val r = readln().trim().split(" ").map { it.toInt() }
        image.add(r as ArrayList<Int>)
    }

    T = readln().trim().toInt()

    for(r in 1 until (R - 1)) {
        for( c in 1 until (C - 1)) {
            val filter = arrayListOf<Int>()
            image.subList(r-1, r+2).map {
                filter.addAll(it.subList(c-1, c+2))
            }
            filter.sort()
            if (filter[4] >= T) {
                count++
            }
        }
    }

    println(count)
}
/** 백준 제출 코드 END*/

/*
test case
6 5
49 36 73 62 21
27 88 14 11 12
99 18 36 91 21
45 96 72 12 10
12 48 49 75 56
12 15 48 86 78
40
 */