package d14

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

internal class CellTest {

    @Test
    fun `should mask number correctly`() {
        // given
        val mask = SetMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        val cell = Cell()

        // when
        cell.assignValueWithMask(101, mask)

        // then
        then(cell.value).isEqualTo(101)

    }

    @Test
    fun `should mask number correctly a`() {
        // given
        val mask = SetMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X")
        val cell = Cell()

        // when
        cell.assignValueWithMask(0, mask)

        // then
        then(cell.value).isEqualTo(64)

    }
}