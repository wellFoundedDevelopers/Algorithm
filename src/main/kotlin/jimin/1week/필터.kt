package jimin.`1week`
/*
<문제>
https://www.acmicpc.net/problem/1895

<구현 방법>
전체 픽셀을 2중 for문으로 살펴볼 때 필터의 영역인 3x3 만큼을 정렬하고 중앙값을 구해
해당 값이 T보다 큰 경우를 계산하였다.

<트러블 슈팅>
NumberFormat 에러남.
NumberFormatException : 문자를 숫자로 변경시도하다가 에러가 발생하는 경우.
=> 예제에 불필요한 공백이 있어 오류가 나는 듯함.
   trim()을 사용해서 공백을 제거해주니 맞았음.

 */

fun main() {
    val x = readLine()!!.trim().split(" ").first().toInt()
    val pixel = arrayListOf<ArrayList<Int>>()
    for (i in 0 until x) {
        pixel.add(readLine()!!.trim().split(" ").map { it.toInt() }
                as ArrayList<Int>)
    }

    val T = readLine()!!.trim().toInt()

    println(checkPixelOverT(pixel, T))
}

private fun checkPixelOverT(pixel: ArrayList<ArrayList<Int>>, T: Int): Int {
    val ninePixels = arrayListOf<Int>()
    var num = 0
    for (i in 0 until pixel.size - 2) {
        for (j in 0 until pixel[0].size - 2) {
            for (x in 0 until 3) {
                for (y in 0 until 3) {
                    ninePixels.add(pixel[i + x][j + y])
                }
            }
            if (ninePixels.sorted()[4] >= T) num += 1
            ninePixels.clear()
        }
    }
    return num
}
