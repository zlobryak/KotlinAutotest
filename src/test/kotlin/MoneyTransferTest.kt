import org.junit.Test
import ru.netology.moneyTransfer
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class MoneyTransferTest {

    private val outputStream = ByteArrayOutputStream()
    private val printStream = PrintStream(outputStream)
    private val originalOut = System.out

//Этот тест был сломан по улвиям задачи, а сейча мы его починили
    @Test
    fun moneyTransfer_Above_Day_Limits() {
        System.setOut(printStream)
        val amount = 150_001.0
        val cardType = "Visa"
        val amountDayOut = 0.0 //Переводы за день
        val amountDayIn = 0.0 //Переводы за день
        val amountMonthOut = 0.0 //Переводы за месяц
        val amountMonthIn = 0.0 //Переводы за месяц
        val isVkPay = false

        moneyTransfer(amount, cardType, amountDayOut, amountDayIn, amountMonthOut, amountMonthIn, isVkPay)

        val actualOutput = outputStream.toString().trim()
        assertEquals("Превышен лимит переводов", actualOutput)
        System.setOut(originalOut)
    }

    @Test
    fun moneyTransfer_Above_Mont_Limits() {
        System.setOut(printStream)
        val amount = 1.0
        val cardType = "Visa"
        val amountDayOut = 0.0 //Переводы за день
        val amountDayIn = 0.0 //Переводы за день
        val amountMonthOut = 0.0 //Переводы за месяц
        val amountMonthIn = 600_000.0 //Переводы за месяц
        val isVkPay = false

        moneyTransfer(amount, cardType, amountDayOut, amountDayIn, amountMonthOut, amountMonthIn, isVkPay)

        val actualOutput = outputStream.toString().trim()
        assertEquals("Превышен лимит переводов", actualOutput)
        System.setOut(originalOut)
    }

    @Test
    fun moneyTransfer_Vk_Pay_is_true() {
        System.setOut(printStream)
        val amount = 1.0
        val cardType = "Visa"
        val amountDayOut = 0.0 //Переводы за день
        val amountDayIn = 0.0 //Переводы за день
        val amountMonthOut = 0.0 //Переводы за месяц
        val amountMonthIn = 0.0 //Переводы за месяц
        val isVkPay = true

        moneyTransfer(amount, cardType, amountDayOut, amountDayIn, amountMonthOut, amountMonthIn, isVkPay)

        val actualOutput = outputStream.toString().trim()
        assertEquals("Для переводов на счета Vk Pay комиссия не взимается", actualOutput)
        System.setOut(originalOut)
    }

    @Test
    fun moneyTransfer_Mastercard_No_fee() {
        System.setOut(printStream)
        val amount = 301.0
        val cardType = "Mastercard"
        val amountDayOut = 0.0 //Переводы за день
        val amountDayIn = 0.0 //Переводы за день
        val amountMonthOut = 0.0 //Переводы за месяц
        val amountMonthIn = 0.0 //Переводы за месяц
        val isVkPay = false

        moneyTransfer(amount, cardType, amountDayOut, amountDayIn, amountMonthOut, amountMonthIn, isVkPay)

        val actualOutput = outputStream.toString().trim()
        assertEquals("Переведено 301.0 руб. Комиссия составит 0.00 руб.", actualOutput)
        System.setOut(originalOut)
    }

    @Test
    fun moneyTransfer_Mir_Min_fee() {
        System.setOut(printStream)
        val amount = 1000.0
        val cardType = "Mir"
        val amountDayOut = 0.0 //Переводы за день
        val amountDayIn = 0.0 //Переводы за день
        val amountMonthOut = 0.0 //Переводы за месяц
        val amountMonthIn = 0.0 //Переводы за месяц
        val isVkPay = false

        moneyTransfer(amount, cardType, amountDayOut, amountDayIn, amountMonthOut, amountMonthIn, isVkPay)

        val actualOutput = outputStream.toString().trim()
        assertEquals("Переведено 1000.0 руб. Комиссия составит 35.00 руб.", actualOutput)
        System.setOut(originalOut)
    }

    @Test
    fun moneyTransfer_Mir_fee() {
        System.setOut(printStream)
        val amount = 10_000.0
        val cardType = "Mir"
        val amountDayOut = 0.0 //Переводы за день
        val amountDayIn = 0.0 //Переводы за день
        val amountMonthOut = 0.0 //Переводы за месяц
        val amountMonthIn = 0.0 //Переводы за месяц
        val isVkPay = false

        moneyTransfer(amount, cardType, amountDayOut, amountDayIn, amountMonthOut, amountMonthIn, isVkPay)

        val actualOutput = outputStream.toString().trim()
        assertEquals("Переведено 10000.0 руб. Комиссия составит 75.00 руб.", actualOutput)
        System.setOut(originalOut)
    }

    @Test
    fun moneyTransfer_Vk_Pay_Above_Ones_Limit() {
        System.setOut(printStream)
        val amount = 15_000.01
        val cardType = "Vk Pay"
        val amountDayOut = 0.0 //Переводы за день
        val amountDayIn = 0.0 //Переводы за день
        val amountMonthOut = 0.0 //Переводы за месяц
        val amountMonthIn = 0.0 //Переводы за месяц
        val isVkPay = false

        moneyTransfer(amount, cardType, amountDayOut, amountDayIn, amountMonthOut, amountMonthIn, isVkPay)

        val actualOutput = outputStream.toString().trim()
        assertEquals("Превышен разовый лимит переводов с Vk Pay", actualOutput)
        System.setOut(originalOut)
    }

    @Test
    fun moneyTransfer_Vk_Pay_Above_month_Limit() {
        System.setOut(printStream)
        val amount = 1_000.01
        val cardType = "Vk Pay"
        val amountDayOut = 0.0 //Переводы за день
        val amountDayIn = 0.0 //Переводы за день
        val amountMonthOut = 39_900.0 //Переводы за месяц
        val amountMonthIn = 0.0 //Переводы за месяц
        val isVkPay = false

        moneyTransfer(amount, cardType, amountDayOut, amountDayIn, amountMonthOut, amountMonthIn, isVkPay)

        val actualOutput = outputStream.toString().trim()
        assertEquals("Превышен месячный лимит переводов с Vk Pay", actualOutput)
        System.setOut(originalOut)
    }

    @Test
    fun moneyTransfer_Vk_Pay_No_Fee() {
        System.setOut(printStream)
        val amount = 1_000.0
        val cardType = "Vk Pay"
        val amountDayOut = 0.0 //Переводы за день
        val amountDayIn = 0.0 //Переводы за день
        val amountMonthOut = 0.0//Переводы за месяц
        val amountMonthIn = 0.0 //Переводы за месяц
        val isVkPay = false

        moneyTransfer(amount, cardType, amountDayOut, amountDayIn, amountMonthOut, amountMonthIn, isVkPay)

        val actualOutput = outputStream.toString().trim()
        assertEquals("Переведено $amount, комиссия не взимается", actualOutput)
        System.setOut(originalOut)
    }

}