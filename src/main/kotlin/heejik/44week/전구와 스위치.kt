package heejik.`44week`


import kotlin.math.min

class `전구와 스위치` {
    fun solve(state: MutableList<Int>, wantState: List<Int>, n: Int): Int {
        val case1 = change(state, wantState, n, 0)
        state[0] = (state[0] + 1) % 2
        state[1] = (state[1] + 1) % 2
        val case2 = change(state, wantState, n, 1)

        return min(case1, case2)
    }

    fun change(_state: MutableList<Int>, wantState: List<Int>, n: Int, _count: Int): Int {
        val state = mutableListOf<Int>()
        _state.forEach { state.add(it) }

        var count = _count
        state.forEachIndexed { index, bulb ->
            if (index == 0) return@forEachIndexed
            if (state[index - 1] == wantState[index - 1]) return@forEachIndexed
            state[index - 1] = (state[index - 1] + 1) % 2
            state[index] = (state[index] + 1) % 2
            count++
            if (index == n - 1) return@forEachIndexed
            state[index + 1] = (state[index + 1] + 1) % 2
        }
        return (if (state == wantState) count else Int.MAX_VALUE)
    }
}

fun main() {
    val n = readln().toInt()
    val state = readln().map { it.digitToInt() }
    val wantState = readln().map { it.digitToInt() }
    val answer = `전구와 스위치`().solve(state.toMutableList(), wantState, n)

    print(if (answer == Int.MAX_VALUE) -1 else answer)
}