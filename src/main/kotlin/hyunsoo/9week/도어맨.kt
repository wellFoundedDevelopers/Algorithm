package hyunsoo.`9week`

import java.util.Collections
import kotlin.math.absoluteValue


/**
 * <도어맨>
 * [도어맨](https://www.acmicpc.net/problem/5002)
 *
 */

const val WOMAN = 'W'
const val MAN = 'M'
var enteredManCnt = 0
var enteredWomanCnt = 0
val maxOfDiff = readln().toInt()
val lineData = readln().toMutableList()

fun main() {

    lineData.forEachIndexed { index, gender ->

        if (diffOfGender() < maxOfDiff) {
            enterTheClub(gender)

            // 남 / 여 차이가 최대 기억할 수 있을 때와 동일하다면
        } else {

            // 남자를 1 증가시킨다면(입장)
            val tempEnteredMan = enteredManCnt + 1
            // 여자를 1 증가시킨다면(입장)
            val tempEnteredWoman = enteredWomanCnt + 1

            when (gender) {
                MAN -> {
                    // 현재 성별이 남자이고 현재 순서로 입장할 때 최대 차이보다 작다면 그대로 입장
                    if (tempEnteredMan.diffAbs(enteredWomanCnt) < maxOfDiff) {
                        enterTheClub(gender)
                        // 다음 성별이 다르다면(여자)
                        // 동일한 로직을 체크한 후 입장
                    } else if (
                        index + 1 < lineData.lastIndex &&
                        gender != lineData[index + 1]
                    ) {

                        if (tempEnteredWoman.diffAbs(enteredManCnt) < maxOfDiff) {
                            Collections.swap(lineData, index, index + 1)
                            val changedGender = lineData[index]
                            enterTheClub(changedGender)
                        }

                    } else {
                        println(enteredCnt())
                        return
                    }
                }

                WOMAN -> {
                    // 현재 성별이 여자이고 현재 순서로 입장할 때 최대 차이보다 작다면 그대로 입장
                    if (tempEnteredWoman.diffAbs(enteredManCnt) < maxOfDiff) {
                        enterTheClub(gender)
                        // 다음 성별이 다르다면(남자)
                        // 동일한 로직을 체크한 후 입장
                    } else if (
                        index + 1 < lineData.lastIndex &&
                        gender != lineData[index + 1]
                    ) {
                        if (tempEnteredMan.diffAbs(enteredWomanCnt) < maxOfDiff) {
                            Collections.swap(lineData, index, index + 1)
                            val changedGender = lineData[index]
                            enterTheClub(changedGender)
                        }

                    } else {
                        println(enteredCnt())
                        return
                    }
                }
            }
        }
    }
    println(enteredCnt())
}

fun enteredCnt() = enteredManCnt + enteredWomanCnt

fun Int.diffAbs(other: Int) = (this - other).absoluteValue

fun diffOfGender() = (enteredManCnt - enteredWomanCnt).absoluteValue

fun enterTheClub(gender: Char) {
    when (gender) {
        MAN -> enteredManCnt++
        WOMAN -> enteredWomanCnt++
    }
}