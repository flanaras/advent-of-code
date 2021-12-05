package y2020.d16

import y2020.d16.TicketValidator.RuleNameToIndex
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

    @Test
    fun `should map rule names to indices in tickets`() {
        // given
        val notes = Reader().readNotes("day-16-test-c")

        // when
        val rulesToTickets = TicketValidator(notes).translateRuleNamesToTicketIndices()

        // then
        then(rulesToTickets).containsAll(listOf(
            RuleNameToIndex("class", 1),
            RuleNameToIndex("row", 0),
            RuleNameToIndex("seat", 2)
        ))
    }

    @Test
    fun `should reject bad ticket containing zero`() {
        // given
        val notes = Reader().readNotes("day-16-a")
        val ticketValidator = TicketValidator(notes)
        val ticket = Ticket(listOf(837,836,437,872,220,502,375,643,328,490,0,662,267,648,222,344,399,555,944,946))

        // when
        then(ticketValidator.existsInRanges(ticket)).isFalse
    }

    @Test
    fun `should map rule to indices in tickets`() {
        // given
        val notes = Reader().readNotes("day-16-test-b")
        val ticketValidator = TicketValidator(notes)

        // when
        ticketValidator.computePossibleRulesForIndices()

        // then
        then(ticketValidator.possibleIndexInTicketToRule[0]).containsExactly(1)
        then(ticketValidator.possibleIndexInTicketToRule[1]).containsExactly(0, 1)
        then(ticketValidator.possibleIndexInTicketToRule[2]).containsExactly(0, 1, 2)
    }
}