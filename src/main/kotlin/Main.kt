package ru.netology

fun main() {
    val amount = 100_000.00
    val cardType = "Vk Pay"
    val amountDayOut = 0.0 //Переводы за день
    val amountDayIn = 0.0 //Переводы за день
    val amountMonthOut = 0.0 //Переводы за месяц
    val amountMonthIn = 0.0 //Переводы за месяц
    val isVkPay = false

    moneyTransfer(amount, cardType, amountDayOut, amountDayIn, amountMonthOut, amountMonthIn, isVkPay)
}

fun moneyTransfer(
    amount: Double,
    cardType: String,
    amountDayOut: Double,
    amountDayIn: Double,
    amountMonthOut: Double,
    amountMonthIn: Double,
    isVkPay: Boolean,

    ) {
//Значения для MasterCard и Maestro
    val masterAndMaestroMinFeeLimit = 300.0
    val masterAndMaestroMonthLimit = 75_000.0
    val masterAndMaestroFee = 0.006
    val masterAndMaestroFeeFixed = 20.0
//Для Visa и Mir
    val visaAndMirFee = 0.0075
    val visaAndMirMinnFee = 35.0
//Дневной и месячный лимиты
    val dayLimit = 150_000.0
    val monthLimit = 600_000.0
//Лимиты для переводов Vk Pay
    val vkPayOnesLimit = 15_000.0
    val vkPayMonthLimit = 40_000.0

    when ( //Проверим лимиты
        amount + amountDayOut > dayLimit ||
                amount + amountDayIn > monthLimit ||
                amount + amountMonthOut > monthLimit ||
                amount + amountMonthIn > monthLimit
    ) {
        true -> println("Превышен лимит переводов")

        else -> when (isVkPay) { //Проверяем переводы на счет Vk PAy
            true -> println("Для переводов на счета Vk Pay комиссия не взимается")

            else -> when (cardType) { //Выбираем тип карты
                "Mastercard", "Maestro" -> {
                    val monthAmountCalc = amountMonthOut + amount
                    val fee = when {
                        amount > masterAndMaestroMinFeeLimit && monthAmountCalc < masterAndMaestroMonthLimit -> 0.0
                        else -> amount * masterAndMaestroFee + masterAndMaestroFeeFixed
                    }
                    println("Переведено $amount руб. Комиссия составит ${String.format("%.2f", fee)} руб.")
                }

                "Visa", "Mir" -> {
                    val fee = amount * visaAndMirFee
                    println(
                        "Переведено $amount руб. Комиссия составит ${
                            String.format("%.2f", maxOf(visaAndMirMinnFee, fee))
                        } руб."
                    )
                }

                "Vk Pay" -> {
                    when (amount > vkPayOnesLimit) {
                        true -> println("Превышен разовый лимит переводов с Vk Pay")
                        else -> when (amount + amountMonthOut > vkPayMonthLimit){
                           true -> println("Превышен месячный лимит переводов с Vk Pay")
                            else -> println("Переведено $amount, комиссия не взимается")
                        }

                    }
                }
            }
        }
    }

}
