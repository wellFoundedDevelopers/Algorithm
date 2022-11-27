package heejik.`11week`

class 꽃길 {

    val priceList = mutableListOf<Int>()
    val flowerBed = mutableListOf<MutableList<Int>>()

    val dx = listOf(-1, 0, 1, 0, 0)
    val dy = listOf(0, 1, 0, -1, 0)


    fun solve() {
        val n = readln().toInt()


        repeat(n) {
            flowerBed.add(readln().split(' ').map { it.toInt() }.toMutableList())
        }

        for (a in 1 until n - 1) {
            for (b in 1 until n - 1) {
                for (c in 1 until n - 1) {
                    for (d in 1 until n - 1) {
                        for (e in 1 until n - 1) {
                            for (f in 1 until n - 1) {
                                check(a, b, c, d, e, f)
                            }
                        }
                    }
                }
            }
        }

        println(priceList.minOrNull())
    }


    fun check(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int) {
        val posList = mutableListOf<Pair<Int, Int>>()
        var s = 0
        for (i in 0..4) {
            val na = a + dx[i]
            val nb = b + dy[i]
            posList.add(Pair(na, nb))
            s += flowerBed[na][nb]
        }
        for (i in 0..4) {
            val nc = c + dx[i]
            val nd = d + dy[i]
            if (Pair(nc, nd) in posList) return
            posList.add(Pair(nc, nd))
            s += flowerBed[nc][nd]
        }

        for (i in 0..4) {
            val ne = e + dx[i]
            val nf = f + dy[i]
            if (Pair(ne, nf) in posList) return
            posList.add(Pair(ne, nf))
            s += flowerBed[ne][nf]
        }

        priceList.add(s)
    }
}

fun main() {
    꽃길().solve()
}