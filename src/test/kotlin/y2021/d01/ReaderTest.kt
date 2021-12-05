package y2021.d01

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class ReaderTest {

    private val reader = Reader()

    @Test
    fun `should read test input a depth`() {
        // given

        // when
        val depths = reader.readDepths()

        // then
        then(depths.size).isEqualTo(10)
        then(depths[0]).isEqualTo(199)
        then(depths[9]).isEqualTo(263)
    }

}