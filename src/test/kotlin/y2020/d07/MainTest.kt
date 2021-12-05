package y2020.d07

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class MainTest {

    @Test
    fun should_count_part_a_properly() {
        // given
        val rules = Parser().parseFile()
        val bagSecurityCount = BagSecurityCount(rules)

        // when
        val countOfBags = bagSecurityCount.count("shiny gold")

        // then
        then(countOfBags).isEqualTo(4)
    }

    @Test
    fun should_count_part_b_properly() {
        // given
        val rules = Parser().parseFile()
        val bagSecurity = BagSecurityCountB(rules)

        // when
        val countOfBags = bagSecurity.count("shiny gold")

        // then
        then(countOfBags).isEqualTo(32)
    }

    @Test
    fun should_count_part_b_properly_second_input() {
        // given
        val rules = Parser().parseFile("day-07-test2")
        val bagSecurity = BagSecurityCountB(rules)

        // when
        val countOfBags = bagSecurity.count("shiny gold")

        // then
        then(countOfBags).isEqualTo(126)
    }

    @Test
    fun should_count_part_b_properly_question() {
        // given
        val rules = Parser().parseFile("day-07-a")
        val bagSecurity = BagSecurityCountB(rules)

        // when
        val countOfBags = bagSecurity.count("shiny gold")

        // then
        then(countOfBags).isGreaterThan(2672)
    }

}