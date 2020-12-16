package d16

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

internal class TicketValidatorTest {

    @Test
    fun `should compute ticket scanning error rate part a`() {
        // given
        val notes = Reader().readNotes("day-16-a")

        // when
        val errorRate = TicketValidator(notes).computeTicketScanningErrorRate()

        // then
        then(errorRate).isEqualTo(21996)
    }

    @Test
    fun `should compute ticket scanning error rate test input`() {
        // given
        val notes = Reader().readNotes()

        // when
        val errorRate = TicketValidator(notes).computeTicketScanningErrorRate()

        // then
        then(errorRate).isEqualTo(71)
    }
}