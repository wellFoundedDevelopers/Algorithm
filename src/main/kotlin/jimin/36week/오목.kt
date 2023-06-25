package jimin.`36week`

import kotlin.system.exitProcess

class 오목 {
    private val ground = mutableListOf<MutableList<Int>>()

    fun solve() {
        repeat(19) {
            ground.add(readln().split(" ").map { it.toInt() } as MutableList)
        }

        for (type in BLACK..WHITE) {
            goDown(type)
            goRight(type)
            goRightDown(type)
            goRightUp(type)
        }

        println("0")
    }

    private fun goDown(color: Int) {
        for (j in 0 until 19) {
            for (i in 0 until 15) {
                var five = true
                for (k in 0 until 5) {
                    if (ground[i + k][j] != color) {
                        five = false
                    }
                }
                if (five) {
                    if (i == 0 && ground[i + 5][j] == color) {
                        five = false
                    } else if ((i > 0 && i + 5 < 19) && (ground[i - 1][j] == color || ground[i + 5][j] == color)) {
                        five = false
                    } else if (i + 5 == 19 && ground[i - 1][j] == color) {
                        five = false
                    }
                }

                if (five) {
                    println(color)
                    println("${i + 1} ${j + 1}")
                    exitProcess(0)
                }
            }
        }
    }


    private fun goRight(color: Int) {
        for (i in 0 until 19) {
            for (j in 0 until 15) {
                var five = true
                for (k in 0 until 5) {
                    if (ground[i][j + k] != color) {
                        five = false
                    }
                }
                if (five) {
                    if (j == 0 && ground[i][j + 5] == color) {
                        five = false
                    } else if ((j > 0 && j + 5 < 19) && (ground[i][j - 1] == color || ground[i][j + 5] == color)) {
                        five = false
                    } else if (j + 5 == 19 && ground[i][j - 1] == color) {
                        five = false
                    }
                }

                if (five) {
                    println(color)
                    println("${i + 1} ${j + 1}")
                    exitProcess(0)
                }
            }
        }
    }

    private fun goRightDown(color: Int) {
        for (i in 0 until 15) {
            for (j in 0 until 15) {
                var five = true
                for (k in 0 until 5) {
                    if (ground[i + k][j + k] != color) {
                        five = false
                    }
                }
                if (five) {
                    if (i == 0 && j == 0 && ground[i + 5][j + 5] == color) {
                        five = false
                    } else if ((i > 0 && i + 5 < 19 && j > 0 && j + 5 < 19) && (ground[i - 1][j - 1] == color || ground[i + 5][j + 5] == color)) {
                        five = false
                    } else if (i + 5 == 19 && j + 5 == 19 && ground[i - 1][j - 1] == color) {
                        five = false
                    }
                }

                if (five) {
                    println(color)
                    println("${i + 1} ${j + 1}")
                    exitProcess(0)
                }
            }
        }
    }


    private fun goRightUp(color: Int) {
        for (i in 4 until 19) {
            for (j in 0 until 15) {
                var five = true
                for (k in 0 until 5) {
                    if (ground[i - k][j + k] != color) {
                        five = false
                    }
                }
                if (five) {
                    if (i == 18 && j == 0 && ground[13][j + 5] == color) {
                        five = false
                    } else if ((i > 5 && i < 18 && j > 0 && j + 5 < 19) && (ground[i + 1][j - 1] == color || ground[i - 5][j + 5] == color)) {
                        five = false
                    } else if (i == 4 && j + 5 == 19 && ground[i + 1][j - 1] == color) {
                        five = false
                    }
                }

                if (five) {
                    println(color)
                    println("${i + 1} ${j + 1}")
                    exitProcess(0)
                }
            }
        }
    }


    companion object {
        const val EMPTY = 0
        const val BLACK = 1
        const val WHITE = 2
    }
}

fun main() {
    오목().solve()
}

/*

0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 1
0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0
0 0 1 0 0 0 0 0 0 0 0 0 0 1 0 0 1 0 0
0 1 0 0 0 0 0 0 0 0 0 0 1 0 0 1 0 0 0
0 0 1 0 0 0 0 0 0 0 0 1 0 0 1 0 0 0 0
0 0 0 1 0 0 0 0 0 0 1 0 0 1 0 0 0 0 0
0 0 0 0 1 0 0 0 0 1 0 0 0 0 0 0 0 0 0
0 0 0 0 1 1 1 1 1 1 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 1
0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0
0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0
0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1

 */