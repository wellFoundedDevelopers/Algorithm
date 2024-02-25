package heejik.`51week`

class `거리두기 확인하기2` {

    fun solution(places: Array<Array<String>>): IntArray {
        val answer = mutableListOf<Int>()

        places.forEach { place ->
            repeat(5) { x ->
                repeat(5) { y ->
                    if (place[x][y] == 'P') {
                        if (check(place, 0, x, y, 0).not()) {
                            answer.add(0)
                            return@forEach
                        }
                    }
                }
            }
            answer.add(1)
        }

        return answer.toIntArray()
    }


    fun check(place: Array<String>, depth: Int, x: Int, y: Int, skip: Int): Boolean {
        var result = true

        if (x < place.lastIndex) {
            when (place[x + 1][y]) {
                'P' -> return false
                'O' -> if (depth == 0) result = check(place, 1, x + 1, y, 0)
            }
        }

        if (result && y - 1 >= 0 && skip != -1) {
            when (place[x][y - 1]) {
                'P' -> return false
                'O' -> if (depth == 0) result = check(place, 1, x, y - 1, 1)
            }
        }


        if (result && y < place.lastIndex && skip != 1) {
            when (place[x][y + 1]) {
                'P' -> return false
                'O' -> if (depth == 0) result = check(place, 1, x, y + 1, -1)
            }
        }


        return result
    }
}