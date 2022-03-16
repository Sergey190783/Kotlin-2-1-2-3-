package ru.netology

const val MAX_SUM_PER_MOUNTH = 60_000_000L
const val MAX_SUM_PER_DAY = 15_000_000L
const val M_MC_MAX_MONEY = 7_500_000L

const val STATUS_CODE_MAX_MONEY_PER_DAY_EXCEEDED = -1L
const val STATUS_CODE_MAX_MONEY_PER_MOUNTH_EXCEEDED = -2L
const val STATUS_CODE_CARD_NOT_SUPPROTED = -3L
const val STATUS_CODE_NEGATIVE_MONEY = -4L

const val M_MC_COMMISION = 0.006
const val VISA_COMMISION = 0.0075

fun calculateCommission(prevSum: Long, curSum: Long, cardType: String = "VKPay", action: Boolean = false): Long {

    if (curSum < 0)
        return STATUS_CODE_NEGATIVE_MONEY

    if (curSum > MAX_SUM_PER_DAY)
        return STATUS_CODE_MAX_MONEY_PER_DAY_EXCEEDED

    if (curSum + prevSum > MAX_SUM_PER_MOUNTH)
        return STATUS_CODE_MAX_MONEY_PER_MOUNTH_EXCEEDED

    return when (cardType) {
        "VKPay" -> 0L
        "MasterCard", "Maestro" -> {
            if (action && curSum + prevSum < M_MC_MAX_MONEY)
                0L
            else (curSum * M_MC_COMMISION + 2_000).toLong()
        }
        "Visa", "Мир" -> if (curSum * VISA_COMMISION > 3500)
            (curSum * VISA_COMMISION).toLong()
        else
            3500L
        else -> STATUS_CODE_CARD_NOT_SUPPROTED
    }
}

fun main() {

    println(calculateCommission(100_000, 500_000, "MasterCard"))
    
    println(calculateCommission(100_000, 500_000, "MasterCard", true))
}
