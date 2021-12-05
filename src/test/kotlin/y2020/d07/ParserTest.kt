package y2020.d07

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class ParserTest {

    private val parser = Parser()

    @Test
    fun should_parse_one_bag_rule() {
        // given
        val line = "dark red bags contain 2 dark orange bags."

        // when
        val bag = parser.parseLine(line)

        // then
        then(bag.owningBag).isEqualTo("dark red")
        val darkBag = Bag("dark orange", 2)
        then(bag.containsBags).hasSize(1)
        then(bag.containsBags).contains(darkBag)
    }

    @Test
    fun should_parse_two_bags_rule() {
        // given
        val line = "dark orange bags contain 3 bright white bags, 4 muted yellow bags."

        // when
        val bag = parser.parseLine(line)

        // then
        then(bag.owningBag).isEqualTo("dark orange")
        val whiteBag = Bag("bright white", 3)
        val yellowBag = Bag("muted yellow", 4)
        then(bag.containsBags).containsAll(arrayListOf(whiteBag, yellowBag))
    }

    @Test
    fun should_parse_four_bags_rule() {
        // given
        val line = "wavy teal bags contain 5 faded indigo bags, 4 dotted gray bags, 3 pale chartreuse bags, 3 vibrant coral bags."

        // when
        val bag = parser.parseLine(line)

        // then
        then(bag.owningBag).isEqualTo("wavy teal")
        then(bag.containsBags).hasSize(4)

        val bags = ArrayList<Bag>().apply {
            add(Bag("faded indigo", 5))
            add(Bag("dotted gray", 4))
            add(Bag("pale chartreuse", 3))
            add(Bag("vibrant coral", 3))
        }

        then(bag.containsBags).containsAll(bags)
    }
}