package y2020.d16

class TicketValidator(
    private val notes: Notes
) {
    private val rangeValidator = RangeValidator()
    private val validTickets = mutableListOf<Ticket>()
    private val ruleValidators = HashMap<Int, RangeValidator>()
    internal val possibleIndexInTicketToRule = HashMap<Int, MutableList<Int>>()

    data class RuleNameToIndex(
        val name: String,
        val ticketIndex: Int
    )

    init {
        notes.rules.forEach { rule ->
            rule.ranges.forEach(rangeValidator::addRange)
        }

        for (ruleIndex in notes.rules.indices) {
            ruleValidators[ruleIndex] = RangeValidator().apply { addRanges(notes.rules[ruleIndex].ranges) }
            possibleIndexInTicketToRule[ruleIndex] = notes.rules.indices.toMutableList()
        }

        keepValidTickets()
    }

    fun translateRuleNamesToTicketIndices(): List<RuleNameToIndex> {
        computePossibleRulesForIndices()

        repeat(notes.rules.size) {
            removePossibilitiesWithOccuranceOne()
        }

        assertDistinctTicketIndicesToRules()

        return convertPossibilitiesToResults()
    }

    private fun convertPossibilitiesToResults(): List<RuleNameToIndex> {
        return possibleIndexInTicketToRule.map { ticketIndexToRule ->
            RuleNameToIndex(
                notes.rules[ticketIndexToRule.value.first()].name,
                ticketIndexToRule.key
            )
        }
    }

    internal fun computePossibleRulesForIndices() {
        for (indexInTicket in notes.rules.indices) {
            possibleIndexInTicketToRule.getValue(indexInTicket)
                .filter { ruleIndex ->
                    ruleValidInAllValidTickets(ruleIndex, indexInTicket)
                }.toMutableList()
                .also {
                    possibleIndexInTicketToRule[indexInTicket] = it
                }
        }
    }

    private fun assertDistinctTicketIndicesToRules() {
        assert(possibleIndexInTicketToRule.flatMap { it.value }
            .distinct()
            .count() == notes.rules.size)
        { "some fishy business here" }
    }

    private fun ruleValidInAllValidTickets(ruleIndex: Int, indexInTicket: Int): Boolean =
        validTickets.map { ticket ->
            ticket.values[indexInTicket]
        }.filter { value ->
            !ruleValidators.getValue(ruleIndex).existsInRanges(value)
        }.count() == 0

    private fun removePossibilitiesWithOccuranceOne() {
        possibleIndexInTicketToRule.filter { it.value.size == 1 }
            .map { it.value.first() }
            .forEach{ removeFromAll(it) }
    }

    private fun removeFromAll(it: Int) {
        for (rulePossibilitiesPair in possibleIndexInTicketToRule) {
            if (rulePossibilitiesPair.value.size > 1) {
                rulePossibilitiesPair.value.remove(it)
            }
        }
    }

    private fun keepValidTickets() {
        validTickets.addAll(
            notes.nearbyTickets.filter { existsInRanges(it) }
        )
    }

    internal fun existsInRanges(ticket: Ticket): Boolean =
        ticket.values.map { value ->
            rangeValidator.existsInRanges(value)
        }.all { it }

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

    fun addRanges(ranges: List<Range>) {
        ranges.forEach(::addRange)
    }

    fun existsInRanges(value: Int): Boolean = ranges.contains(value)
}