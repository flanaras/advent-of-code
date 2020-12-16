package d13

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import java.math.BigInteger

internal class MathTest {

    @Test
    fun `compute correct lcm of two numbers`() {
        // given
        val first = BigInteger.valueOf(121)
        val second = BigInteger.valueOf(67)

        // when
        val lcm = Math.lcm(first, second)

        // then
        then(lcm).isEqualTo(8107)
    }

    @Test
    fun `compute correct lcm of multiple numbers`() {
        // given
        val numbers = listOf(12, 15, 10, 75, 121).map { it.toBigInteger() }

        // when
        val lcm = Math.lcm(*numbers.toTypedArray())

        // then
        then(lcm).isEqualTo(36300)
    }
}