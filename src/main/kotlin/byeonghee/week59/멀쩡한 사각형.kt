package byeonghee.week59

class 소병희_멀쩡한사각형 {
    fun solution(w: Int, h: Int): Long {
        val gcd = gcd(w, h)
        val sw = w / gcd
        val sh = h / gcd
        val adj = 1 + (sw - 1) + (sh - 1)

        return (0L + w) * h - gcd * adj
    }
}

fun gcd(_a: Int, _b: Int): Int {
    var a = _a
    var b = _b

    while(b > 0) {
        a = b.also { b = a % b }
    }

    return a
}