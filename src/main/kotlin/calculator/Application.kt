package calculator

import camp.nextstep.edu.missionutils.Console

fun input(): String = Console.readLine()

fun splitNumber(str: String): List<Int> {
    var numberList: List<Int> = listOf()

    var num = ""

    str.forEach {
        if (it == ',' || it == ':') {
            if (num != "") {
                numberList += num.toInt()
                num = ""
            }
            return@forEach
        }
        num += it.toString()
    }
    if (num != "") {
        numberList += num.toInt()
    }

    return numberList
}

fun sum(numbers: List<Int>): Int = numbers.sum()


fun isValid(str: String): Boolean {
    val inputRegex = "^[0-9,:]*$".toRegex()
    return inputRegex.matches(str)
}

fun checkCustom(str: String): Boolean = str.contains("//") && str.contains("\\n")

fun replaceCustom(str: String): String {
    var str = str
    var newStr = ""
    var customSeparator = ""

    val beforeIndex = str.indexOf("//")
    val afterIndex = str.indexOf("\\n")
    if (beforeIndex < afterIndex) {
        customSeparator = str.substring(beforeIndex + 2, afterIndex)
        newStr = str.substring(afterIndex + 2)
        newStr = newStr.replace(customSeparator, ",")
    } else {
        throw IllegalArgumentException()
    }
    return newStr
}

fun main() {
    println("덧셈할 문자열을 입력해주세요.")

    try {
        var inputString = input()

        if (!isValid(inputString)) {
            if (checkCustom(inputString)) {
                inputString = replaceCustom(inputString)
            } else {
                throw IllegalArgumentException()
            }
        }

        val numbers = splitNumber(inputString)
        val result = sum(numbers)
        println("결과 : $result")

    } catch (e: NoSuchElementException) {
        println("결과 : 0")
    }
}
