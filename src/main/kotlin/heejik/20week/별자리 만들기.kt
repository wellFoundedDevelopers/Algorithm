package heejik.`20week`

import kotlin.math.*
import kotlin.properties.Delegates

class `별자리 만들기` {

    var n by Delegates.notNull<Int>()
    private lateinit var stars: MutableList<Star>
    private lateinit var parents: MutableList<Int>
    private val lines = mutableListOf<Line>()
    private var cost = 0F

    data class Line(
        val star1: Star,
        val star2: Star,
    ) {
        var distance by Delegates.notNull<Float>()
        fun getDistance() {
            val distanceOfX = abs(star1.x - star2.x).pow(2)
            val distanceOfY = abs(star1.y - star2.y).pow(2)
            distance = sqrt(distanceOfX + distanceOfY)
        }
    }

    data class Star(
        val name: Int,
        val x: Float,
        val y: Float,
    ) {
        var root by Delegates.notNull<Int>()

        @JvmName("_setRoot")
        fun setRoot(root: Int) {
            this.root = root
        }
    }

    fun solve() {
        getInput()
        getLines()
        makeConstellation()
        println(String.format("%.2f", cost))
    }

    private fun getInput() {
        n = readln().toInt()
        stars = MutableList(n) { Star(name = -1, x = -1F, y = -1F) }
        parents = MutableList(n) { it }
        repeat(n) { i ->
            val (x, y) = readln().split(' ').map { it.toFloat() }
            stars[i] = Star(i, x, y).apply {
                setRoot(i)
            }
        }
    }

    private fun getLines() {
        for (i in 0 until n - 1) {
            for (j in i + 1 until n) {
                lines.add(Line(star1 = stars[i], star2 = stars[j]).apply { getDistance() })
            }
        }
        lines.sortBy { it.distance }
    }

    private fun makeConstellation() {
        lines.forEach { line ->
            if (line.star1.root == line.star2.root) return@forEach
            cost += line.distance

            val bigRoot = max(line.star1.root, line.star2.root)
            val smallRoot = min(line.star1.root, line.star2.root)
            stars.forEach {
                if (it.root == bigRoot) it.root = smallRoot
            }
        }
    }
}

fun main() {
    `별자리 만들기`().solve()
}