package hyunsoo.`4week`

/**
 *  <문제>
 *  [NBA 농구](https://www.acmicpc.net/problem/2852)
 * 동혁이는 NBA 농구 경기를 즐겨봄
 * 골이 들어갈 때 마다 골이 들어간 시간과 팀을 적는 취미가 있음
 * 농구는 48분동안 진행되고, 각 팀이 몇 분동안 이기고 있었는지 출력
 *
 * 입/출력
 * - 첫째 줄에 골이 들어간 횟수 n이 주어짐
 * - 둘째 줄부터 n개의 줄에 득점 정보가 주어짐
 *      - 득점한 팀의 번호와 득점한 시간
 * - MM:SS 형식으로 득점한 시간이 주어짐.
 * - 분과 초과 한자리 일 경우 첫째 자리가 0으로 주어짐
 *
 * 아이디어
 * - 구현문제인듯?
 *
 * */

data class Time(val hour: Int, val minute: Int) {
    override fun toString() = "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}"
}

data class GoalData(val team: String, val time: Time)

fun main() {

    val goalCnt = readln().toInt()
    val goalDataList = mutableListOf<GoalData>()
    var lastGoalTime = Time(0, 0)
    var firstTeamWonTime = Time(0, 0)
    var secondTeamWonTime = Time(0, 0)
    var firstGoalCount = 0
    var secondGoalCount = 0

    repeat(goalCnt) {
        val (team, time) = readln().split(" ")
        val (hour, minute) = time.split(":").map { it.toInt() }
        goalDataList.add(
            GoalData(
                team,
                Time(hour, minute)
            )
        )
    }

    goalDataList.forEachIndexed { index, goalData ->

        val timeAfterGoal = timeMinusCalculate(goalData.time, lastGoalTime)
        lastGoalTime = goalData.time

        if (firstGoalCount > secondGoalCount) {
            firstTeamWonTime = timePlusCalculate(firstTeamWonTime, timeAfterGoal)
        } else if (secondGoalCount > firstGoalCount) {
            secondTeamWonTime = timePlusCalculate(secondTeamWonTime, timeAfterGoal)
        }

        // 1번 팀이 골을 넣었을 경우
        if (goalData.team == "1") {
            firstGoalCount++
            // 2번 팀이 골을 넣었을 경우
        } else {
            secondGoalCount++
        }

        if (index == goalDataList.lastIndex) {

            val leftTime = timeMinusCalculate(Time(48, 0), lastGoalTime)

            if (firstGoalCount > secondGoalCount) {
                firstTeamWonTime = timePlusCalculate(firstTeamWonTime, leftTime)
            } else if (secondGoalCount > firstGoalCount) {
                secondTeamWonTime = timePlusCalculate(secondTeamWonTime, leftTime)
            }
        }

    }

    println(firstTeamWonTime)
    println(secondTeamWonTime)
}

fun timeMinusCalculate(goalTime: Time, lastGoalTime: Time): Time {

    var isExceed = false
    var hourAfterLastGoal = 0
    var minuteAfterLastGoal = 0

    if (goalTime.minute >= lastGoalTime.minute) {
        minuteAfterLastGoal = goalTime.minute - lastGoalTime.minute
    } else {
        isExceed = true
        minuteAfterLastGoal = goalTime.minute - lastGoalTime.minute + 60
    }

    hourAfterLastGoal = if (goalTime.hour >= lastGoalTime.hour) {
        goalTime.hour - lastGoalTime.hour
    } else {
        lastGoalTime.hour - goalTime.hour
    }

    if (isExceed) hourAfterLastGoal--

    return Time(hourAfterLastGoal, minuteAfterLastGoal)
}

fun timePlusCalculate(previousTime: Time, plusTime: Time): Time {

    var isExceed = false
    var hourAfterPlus = 0
    var minuteAfterPlus = 0

    minuteAfterPlus = (previousTime.minute + plusTime.minute)
    if (minuteAfterPlus >= 60) {
        isExceed = true
        minuteAfterPlus -= 60
    }


    hourAfterPlus = previousTime.hour + plusTime.hour

    if (isExceed) hourAfterPlus++

    return Time(hourAfterPlus, minuteAfterPlus)
}