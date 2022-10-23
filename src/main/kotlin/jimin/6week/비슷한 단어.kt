package jimin.`6week`

import java.io.BufferedReader
import java.io.InputStreamReader


fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val masterWord = readLine()
    var num = 0

    repeat(n - 1) {
        val word = readLine()
        if (checkSameFormat(makeWordCharMap(masterWord).toSortedMap(), makeWordCharMap(word).toSortedMap())) {
            println("true")
            num += 1
        } else {
            println("false")
        }
    }
    println(num)

}

fun makeWordCharMap(word: String): MutableMap<Char, Int> {
    val wordCharMap = mutableMapOf<Char, Int>()
    word.forEach {
        if (wordCharMap[it] == null) {
            wordCharMap[it] = 1
        } else {
            wordCharMap[it] = (wordCharMap[it] ?: 0) + 1
        }
    }
    return wordCharMap
}

fun checkSameFormat(masterWordMap: MutableMap<Char, Int>, newWordMap: MutableMap<Char, Int>): Boolean {
    return if (masterWordMap == newWordMap) {
        true
    } else {
        if (masterWordMap.keys == newWordMap.keys) { // 같은 종류 but 다른 개수
            var diff = 0
            masterWordMap.forEach { key, value ->
                diff += if (value > (newWordMap[key] ?: 0)) value - (newWordMap[key] ?: 0)
                else (newWordMap[key] ?: 0) - value
            }
            (diff == 1)
        } else { // 다른 종류
            val onlyInNew = newWordMap.keys.filter { it !in masterWordMap.keys }
            val onlyInMaster = masterWordMap.keys.filter { it !in newWordMap.keys }
            if (masterWordMap.values.sum() == newWordMap.values.sum()) { // 다른 종류 and 같은 길이 -> 하나 바꾸기
                if (onlyInMaster.size == 1) { // ex. GOD, GGD : onlyInMaster = O / GOD, GOF : onlyInMaster = D
                    val tempNewWordMap = newWordMap.toMutableMap()
                    if (onlyInNew.size == 1) { // GOF
                        tempNewWordMap.remove(onlyInNew.first())
                        tempNewWordMap[onlyInMaster.first()] = 1
                        (tempNewWordMap == masterWordMap)
                    } else if (onlyInNew.isEmpty()) { //GGD
                        newWordMap.forEach { key, value ->
                            if (value - masterWordMap[key]!! != 0) {
                                tempNewWordMap[key] = tempNewWordMap[key]!! - 1
                                tempNewWordMap[onlyInMaster.first()] = 1
                                (tempNewWordMap == masterWordMap)
                            }
                        }
                    } else {
                        false
                    }
                }
            } else { // 다른 종류 and 다른 길이 -> 하나 추가 or 빼기


                    if (tempNewWordMap[onlyInNew.first()] == 1) {
                        tempNewWordMap.remove(onlyInNew.first())
                    } else {
                        tempNewWordMap[onlyInNew.first()] = (tempNewWordMap[onlyInNew.first()] ?: 1) - 1
                    }
                    tempNewWordMap[onlyInMaster.first()] = 1
                    println("2 temp $tempNewWordMap, master $masterWordMap")
                    (tempNewWordMap == masterWordMap)

            }
        }

        if (onlyInNew.size == 1 && onlyInMaster.size == 1 && newWordMap[onlyInNew.first()] == 1) {
            val tempNewWordMap = newWordMap.toMutableMap()
            if (tempNewWordMap[onlyInNew.first()] == 1) {
                tempNewWordMap.remove(onlyInNew.first())
            } else {
                tempNewWordMap[onlyInNew.first()] = (tempNewWordMap[onlyInNew.first()] ?: 1) - 1
            }
            tempNewWordMap[onlyInMaster.first()] = 1
            (tempNewWordMap == masterWordMap)
        } else if (onlyInNew.size == 1 && onlyInMaster.isEmpty()) {
            val tempMasterWordMap = masterWordMap.toMutableMap()
            tempMasterWordMap[onlyInNew.first()] = 1
            (tempMasterWordMap == newWordMap)
        } else if (onlyInMaster.size == 1 && onlyInNew.isEmpty()) {
            val tempNewWordMap = newWordMap.toMutableMap()
            if (newWordMap.size != masterWordMap.size) {
                tempNewWordMap[onlyInMaster.first()] = 1
                println("1 temp $tempNewWordMap, master $masterWordMap")
                (tempNewWordMap == masterWordMap)
            } else {
                if (tempNewWordMap[onlyInNew.first()] == 1) {
                    tempNewWordMap.remove(onlyInNew.first())
                } else {
                    tempNewWordMap[onlyInNew.first()] = (tempNewWordMap[onlyInNew.first()] ?: 1) - 1
                }
                tempNewWordMap[onlyInMaster.first()] = 1
                println("2 temp $tempNewWordMap, master $masterWordMap")
                (tempNewWordMap == masterWordMap)
            }

        } else {
            false
        }
    }
}
}
