package y2020.d21

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

internal class AllergenFinderTest {

    @Test
    fun `should count allergens test input a`() {
        // given
        val dishes = Reader().readMenu()

        // when
        val sum = AllergenFinder(dishes).countNonAllergenOccurrences()

        // then
        then(sum).isEqualTo(5)
    }

    @Test
    fun `should count allergens input a`() {
        // given
        val dishes = Reader().readMenu("day-21-a")

        // when
        val occurrences = AllergenFinder(dishes).countNonAllergenOccurrences()

        // then
        then(occurrences).isEqualTo(2659)
    }

    @Test
    fun `should canonical dangerous ingredient list test input`() {
        // given
        val dishes = Reader().readMenu()

        // when
        val canonicalList = AllergenFinder(dishes).computeCanonicalDangerousIngredientList()

        // then
        then(canonicalList).isEqualTo("mxmxvkd,sqjhc,fvjkl")
    }

    @Test
    fun `should canonical dangerous ingredient list input b`() {
        // given
        val dishes = Reader().readMenu("day-21-a")

        // when
        val canonicalList = AllergenFinder(dishes).computeCanonicalDangerousIngredientList()

        // then
        then(canonicalList).isEqualTo("rcqb,cltx,nrl,qjvvcvz,tsqpn,xhnk,tfqsb,zqzmzl")
    }
}