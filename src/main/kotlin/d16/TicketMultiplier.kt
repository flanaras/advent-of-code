package d16

import d16.TicketValidator.RuleNameToIndex

class TicketMultiplier {
    companion object {
        fun multiply(ticket: Ticket, ruleNameToIndex: List<RuleNameToIndex>, containing: String) =
            ruleNameToIndex.filter { it.name.contains(containing) }
                .map { ticket.values[it.ticketIndex].toLong() }
                .reduce { first, second -> first * second }
    }
}