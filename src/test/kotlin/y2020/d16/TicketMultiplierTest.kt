package y2020.d16

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

internal class TicketMultiplierTest {

    @Test
    fun `should test multiplier part b`() {
        // given
        val notes = Reader().readNotes("day-16-a")
        val rulesToTickets = TicketValidator(notes).translateRuleNamesToTicketIndices()

        // when
        val multiplied = TicketMultiplier.multiply(notes.myTicket, rulesToTickets, "departure")

        // then
        then(multiplied).isEqualTo(650080463519)
    }

    @Test
    fun `should test multiplier test input`() {
        // given
        val notes = Reader().readNotes("day-16-test-b")
        val rulesToTickets = TicketValidator(notes).translateRuleNamesToTicketIndices()

        // when
        val multiplied = TicketMultiplier.multiply(notes.myTicket, rulesToTickets, "cla")

        // then
        then(multiplied).isEqualTo(12)
    }
}