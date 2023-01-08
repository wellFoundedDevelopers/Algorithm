package heejik.`4week`

fun main() {

    val endTime = "48:00".toSecond()
    var win_team = -1
    var time_1 = 0
    var time_2 = 0
    var tmp_1 = -1
    var tmp_2 = -1
    var score_1 = 0
    var score_2 = 0

    repeat(readln().toInt()) {
        val (_team, _score_time) = readln().split(' ')
        val team = _team.toInt()
        val score_time = _score_time.toSecond()
        if (team == 1) {
            score_1 += 1
        } else {
            score_2 += 1
        }

        if (score_1 > score_2) {
            win_team = 1
            if (tmp_1 == -1) {
                tmp_1 = score_time
            }
        } else if (score_2 > score_1) {
            win_team = 2
            if (tmp_2 == -1) {
                tmp_2 = score_time
            }
        } else {
            if (win_team == 1) {
                time_1 += score_time - tmp_1
            }
            if (win_team == 2) {
                time_2 += score_time - tmp_2
            }
            win_team = -1
            tmp_1 = -1
            tmp_2 = -1
        }
    }
    if (win_team == 1) {
        time_1 += endTime - tmp_1
    } else if (win_team == 2) {
        time_2 += endTime - tmp_2
    }

    println(time_1.toTime())
    println(time_2.toTime())
}

fun String.toSecond(): Int {
    val li = this.split(':').map { it.toInt() }
    return (li[0] * 60 + li[1])
}

fun Int.toTime(): String {
    val minute = (this / 60).toString().padStart(2, '0')
    val second = (this % 60).toString().padStart(2, '0')
    return "$minute:$second"
}