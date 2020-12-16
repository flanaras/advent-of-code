package d16

class TicketValidator(
    private val notes: Notes
) {
    private val rangeValidator = RangeValidator()

    init {
        notes.rules.forEach { rule ->
            rule.ranges.forEach(rangeValidator::addRange)
        }
    }

    fun computeTicketScanningErrorRate(): Int =
        notes.nearbyTickets.map(this::computeTicket)
            .sumOf { it }

    private fun computeTicket(ticket: Ticket): Int =
        ticket.values.filter { value ->
            !rangeValidator.existsInRanges(value)
        }.sumOf { it }
}

class RangeValidator() {

    private val ranges = HashSet<Int>()

    fun addRange(range: Range) {
        ranges.addAll(range.from..range.to)
    }

    fun existsInRanges(value: Int): Boolean = ranges.contains(value)
}