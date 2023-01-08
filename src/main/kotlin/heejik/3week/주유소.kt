package heejik.`3week`


fun main() {

    val n = readln().toInt()
    val road = readln().split(' ').map { it.toLong() }
    val gasStation = readln().split(' ').map { it.toLong() }

    var cost= 0L
    var tmp = 1000000001L


    for(pos in 0 until n-1){
        if (gasStation[pos] < tmp){     // 현재 주유소 가격이 이전 주유소 가격보다 더 싸면
            tmp = gasStation[pos]
        }
        cost += tmp * road[pos]
    }

    println(cost)
}