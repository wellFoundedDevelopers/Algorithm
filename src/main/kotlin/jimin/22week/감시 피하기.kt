package jimin.`22week`

/*
<문제>
[감시 피하기](https://www.acmicpc.net/problem/18428)

<구현 방법>
복도를 돌면서 선생님을 발견하면, checkStudent 함수를 통해
학생을 만날때까지의 좌표를 담은 리스트(장애물 위치가 될 수 있는 리스트)를 obstacles에 담는다.
이때 해당 리스트가 empty라면 선생님과 학생이 딱 붙어있는 것이므로 학생들은 선생님을 피할 수 없다.
그러므로 해당 empty 리스트가 있다면 바로 No을 출력하게 했고,
그렇지 않으면서 리스트가 3개 이하라면 바로 Yes를 출력하게 했다.
두 경우도 아니라면, 리스트가 중복되는게 있을 경우 한 리스트를 empty로 만들어주어,
empty가 아닌 리스트의 개수가 3개 이하라면 Yes를 출력하게하였다.

<트러블 슈팅>
풀지 못하였다. 반례를 다 확인했는데도 안된다,,,
*/


class `감시 피하기` {
    var n = 0
    val hallway = mutableListOf<MutableList<String>>()
    val obstacles = mutableListOf<MutableList<Pair<Int, Int>>>()
    var result = false

    fun solve() {
        n = readln().toInt()
        repeat(n) {
            hallway.add(readln().split(" ") as MutableList)
        }

        checkTeacher()
        if (result) {
            println("YES")
        } else {
            println("NO")
        }
    }

    private fun checkTeacher() {
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (hallway[i][j] == "T") {
                    checkStudent(i, j)
                }
            }
        }

        if (obstacles.size <= 3) {
            result = true
            return
        }
        if (obstacles.count { it.isEmpty() } > 0) {
            return
        }
        for (i in 0 until obstacles.size - 1) {
            for (j in i + 1 until obstacles.size) {
                val newObstacles = obstacles[i] + obstacles[j]
                if (newObstacles.distinct().size != newObstacles.size) {
                    obstacles[j].clear()
                }
            }
        }

        if (obstacles.count { it.isNotEmpty() } <= 3) {
            result = true
        }
        print(obstacles)
    }

    private fun checkStudent(x: Int, y: Int) {
        val possibleObstacles = mutableListOf<Pair<Int, Int>>()
        for (i in x + 1 until n) {
            if (hallway[i][y] == "T") {
                break
            }
            if (hallway[i][y] == "S") {
                obstacles.add(possibleObstacles.toMutableList())
                break
            }
            possibleObstacles.add(Pair(i, y))
        }
        possibleObstacles.clear()
        for (i in x - 1 downTo 0) {
            if (hallway[i][y] == "T") {
                break
            }
            if (hallway[i][y] == "S") {
                obstacles.add(possibleObstacles.toMutableList())
                break
            }
            possibleObstacles.add(Pair(i, y))
        }
        possibleObstacles.clear()
        for (i in y + 1 until n) {
            if (hallway[x][i] == "T") {
                break
            }
            if (hallway[x][i] == "S") {
                obstacles.add(possibleObstacles.toMutableList())
                break
            }
            possibleObstacles.add(Pair(x, i))
        }
        possibleObstacles.clear()
        for (i in y - 1 downTo 0) {
            if (hallway[x][i] == "T") {
                break
            }
            if (hallway[x][i] == "S") {
                obstacles.add(possibleObstacles.toMutableList())
                break
            }
            possibleObstacles.add(Pair(x, i))
        }
    }

}

fun main() {
    `감시 피하기`().solve()
}