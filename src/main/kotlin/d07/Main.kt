@file:JvmName("Main")
package d07

import kotlin.IllegalArgumentException

fun main(args: Array<String>) {

    val rules = Parser().parseFile("day-07-a")

    val bagSecurityCount = BagSecurityCountB(rules)

    println("Bag count: " + bagSecurityCount.count("shiny gold"))
}

class BagSecurityCount(rules: List<Rule>) {

    private val encountered = HashSet<String>()
    private var searchingFor = ArrayList<String>()
    private var searchingForNext = ArrayList<String>()
    private val invertedRules = HashMap<String, ArrayList<String>>()

    init {
        parseRules(rules)
    }

    private fun parseRules(rules: List<Rule>) {
        rules.forEach { rule ->
            parseRule(rule)
        }
    }

    private fun parseRule(rule: Rule) {
        rule.containsBags.forEach { containsBag ->
            invertedRules.getOrPut(containsBag.name) { arrayListOf() }
                .add(rule.owningBag)
        }
    }

    fun count(startingBag: String): Int {
        var sum = 0
        encounterBag(startingBag)

        while (searchingForNext.isNotEmpty()) {
            searchingFor = searchingForNext
            searchingForNext = ArrayList()

            searchingFor.forEach { bag ->
                if (!encountered.contains(bag)) {
                    sum += 1
                    encounterBag(bag)
                }
            }
        }

        return sum
    }

    private fun encounterBag(startingBag: String) {
        encountered.add(startingBag)
        invertedRules[startingBag]?.also {
            searchingForNext.addAll(it)
        }
    }
}

class BagSecurityCountB(rules: List<Rule>) {

    data class WithModifier(
        val name: String,
        val accumulatedModifier: Int
    )

    private var searchingFor = ArrayList<WithModifier>()
    private var searchingForNext = ArrayList<WithModifier>()
    private val index = HashMap<String, List<Bag>>()
    private var sum = 0

    init {
        rules.forEach { rule ->
            index[rule.owningBag] = rule.containsBags
        }
    }

    fun count(startingBagName: String): Int {
        sum += encounterBag(createStartingBag(startingBagName))

        while (searchingForNext.isNotEmpty()) {
            searchingFor = searchingForNext
            searchingForNext = ArrayList()

            searchingFor.forEach { bagWithModifier ->
                sum += encounterBag(bagWithModifier)
            }
        }

        return sum
    }

    private fun createStartingBag(startingBagName: String): WithModifier {
        val initialModifier = 1
        return WithModifier(startingBagName, initialModifier)
    }

    private fun encounterBag(bagWithModifier: WithModifier): Int =
        index[bagWithModifier.name]?.let { bags ->
            prepareNextSearchFront(bags, bagWithModifier.accumulatedModifier)
            countBags(bags, bagWithModifier.accumulatedModifier)
        } ?: throw IllegalArgumentException("Could not find bag $bagWithModifier.name")

    private fun prepareNextSearchFront(bags: List<Bag>, accumulatedModifier: Int) {
        searchingForNext.addAll(toWithModifiers(bags, accumulatedModifier))
    }

    private fun countBags(bags: List<Bag>, accumulatedModifier: Int): Int =
        bags.map { bag ->
            bag.count * accumulatedModifier
        }.sumOf { it }

    private fun toWithModifiers(bags: List<Bag>, modifier: Int) =
        bags.map { bag ->
            WithModifier(bag.name, bag.count * modifier)
        }

}