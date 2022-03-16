package ru.netology

const val MINUTE = 60
const val HOUR = 3600

fun agoToTExt(networkTime: Int): String {
    return when (networkTime) {
        in 0..60 -> "был(а) только что"
        in 61..3600 -> "был(а) ${secToMin(networkTime)} назад"
        in 3601..86400 -> "был(а) ${secToHours(networkTime)} назад"
        else -> "был(а) ${secToDays(networkTime)}"

    }
}

fun secToMin(seconds: Int): String {
    val minutes = seconds / MINUTE
    return when {
        minutes == 1 || (minutes % 10 == 1 && minutes != 11) -> "$minutes минуту"
        minutes % 10 in 2..4 && minutes !in 12..14 -> "$minutes минуты"
        else -> "$minutes минут"
    }
}

fun secToHours(seconds: Int): String {
    val hours = seconds / HOUR
    return when {
        hours == 1 || (hours % 10 == 1 && hours != 11) -> "$hours час"
        hours % 10 in 2..4 && hours !in 12..14 -> "$hours часа"
        else -> "$hours часов"
    }
}

fun secToDays(seconds: Int): String {
    return when (seconds) {
        in 86401..172800 -> "сегодня"
        in 172801..259200 -> "вчера"
        else -> "давно"
    }
}

fun main() {
    println(agoToTExt(61))
    println(agoToTExt(400))
    println(agoToTExt(9000))
    println(agoToTExt(90000))
    println(agoToTExt(200000))
    println(agoToTExt(300000))

}


