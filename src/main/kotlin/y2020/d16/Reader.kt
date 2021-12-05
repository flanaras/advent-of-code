package y2020.d16

import y2020.d07.base_2020
import java.io.File

class Reader {

    fun readNotes(filename: String = "day-16-test"): Notes {
        val groups = File(base_2020 + filename).readText()
            .split("\n\\s*\n".toRegex())

        val rules = toRules(groups[0].lines())
        val myTicket = toTicket(dropHeaderLine(groups[1].lines()).first())
        val nearbyTickets = toTickets(dropHeaderLine(groups[2].lines()))

        return Notes(rules, myTicket, nearbyTickets)
    }

    private fun toRules(lines: List<String>): List<Rule> = lines.map(::toRule)

    private fun toRule(line: String): Rule {
        val splitLine = line.split(":")

        val name = splitLine[0]
        val ranges = removeSpaces(splitLine[1]).split("or")
            .map { toRange(it) }

        return Rule(name, ranges)
    }

    private fun toRange(fromTo: String) =
        fromTo.split("-")
            .map { it.toInt() }
            .let { parts ->
                Range(parts[0], parts[1])
            }

    private fun dropHeaderLine(lines: List<String>): List<String> = lines.drop(1)
    private fun removeSpaces(string: String): String = string.replace(" ", "")

    private fun toTickets(lines: List<String>): List<Ticket> = lines.map(::toTicket)

    private fun toTicket(line: String): Ticket =
        line.split(",")
            .map { it.toInt() }
            .let { Ticket(it) }

}

data class Notes(
    val rules: List<Rule>,
    val myTicket: Ticket,
    val nearbyTickets: List<Ticket>
)

data class Rule(
    val name: String,
    val ranges: List<Range>
)

data class Range(
    val from: Int,
    val to: Int
)

data class Ticket(
    val values: List<Int>
)

