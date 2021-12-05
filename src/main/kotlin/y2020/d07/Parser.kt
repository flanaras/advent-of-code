package y2020.d07

import java.io.File

const val base_2020 = "src/main/resources/2020/"

class Parser {

    fun parseFile(filename: String = "day-07-test"): List<Rule> = File(base_2020 + filename).readLines()
        .map { line -> parseLine(line) }
        .toList()

    internal fun parseLine(line: String): Rule {
        val twoPartLine = line.split(" bags contain ")
        val containsBags = parseContainsBags(twoPartLine[1])

        return Rule(twoPartLine[0], containsBags)
    }

    private fun parseContainsBags(string: String): List<Bag> {
        return if (string.contains("no other bags")) {
            emptyList()
        } else {
            removeBags(string)
                .split(",")
                .map { toBag(it) }
        }
    }

    //    val line = "dark orange bags contain 3 bright white bags, 4 muted yellow bags."
    private fun toBag(string: String): Bag {
        val numberOfBags = string.filter { it.isDigit() }.toInt()
        val name = removeDigits(string)
        return Bag(name, numberOfBags)

    }

    private fun removeDigits(string: String) = string.replace(" ?\\d+ ".toRegex(), "")
    private fun removeBags(string: String) = string.replace(" ?bags?\\.?".toRegex(), "")

}

data class Rule(
    val owningBag: String,
    val containsBags: List<Bag>
)

data class Bag(
    val name: String,
    val count: Int
)