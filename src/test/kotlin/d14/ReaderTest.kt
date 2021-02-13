package d14

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class ReaderTest {

    private val reader = Reader()

    @Test
    fun should_parse_operations() {
        // given

        // when
        val operations = reader.readMemoryOperations()

        // then
        then(operations).containsExactlyElementsOf(listOf(
            SetMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"),
            MemCopy(8, 11),
            MemCopy(7, 101),
            MemCopy(8, 0)
        ))
    }

    @Test
    fun `should parse mixed order`() {
        // given

        // when
        val operations = reader.readMemoryOperations("day-14-test-b")

        // then
        then(operations).containsExactlyElementsOf(listOf(
            SetMask("000000000000000000000000000000X1001X"),
            MemCopy(42, 100),
            SetMask("00000000000000000000000000000000X0XX"),
            MemCopy(26, 1)
        ))
    }

}