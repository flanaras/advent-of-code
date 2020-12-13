package d09

import java.math.BigInteger

fun main(args: Array<String>) {
    val lines = Reader().readInts("day-09-a")

    val checker = VulnerabilityChecker(lines)

    println("First invalid number: ${checker.extractIncorrectPreamble()}")
}

class VulnerabilityChecker(
    private val numbers: List<BigInteger>,
    private val preambleLength: Int = 25
) {

    private lateinit var slidingWindow: List<BigInteger>

    fun extractIncorrectPreamble(): BigInteger {
        return findFirstIncorrectIndex()
    }

    private fun findFirstIncorrectIndex():BigInteger {
        val res: BigInteger = BigInteger.ONE

        (preambleLength..numbers.size-preambleLength).forEach { i ->
            updateSlidingWindow(i)
            if (!possibleNumber(numbers[i])) {
                return numbers[i]
            }
        }
        return res
    }

    private fun updateSlidingWindow(offset: Int) {
        slidingWindow = numbers.subList(offset - preambleLength, offset)
    }

    private fun possibleNumber(number: BigInteger): Boolean {
        slidingWindow.forEach { first ->
            val second = number - first
            if (first != second && slidingWindow.contains(second)) {
                return true
            }
        }
        return false
    }

}
