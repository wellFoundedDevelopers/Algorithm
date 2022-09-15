package heejik

import java.lang.Integer.max

class 카펫_장희직 {

    fun solution(brown: Int, yellow: Int): IntArray {
        val limit = max(brown,yellow)

        for (i in 1..limit){
            for (j in i..limit){
                if (((j*2)+((i-2)*2) == brown) && ((i-2)*(j-2) == yellow)){
                    return intArrayOf(j,i)
                }
            }
        }
        return intArrayOf(0,0)
    }
}

fun main() {
    println(카펫_장희직().solution(10,2)[0])
}