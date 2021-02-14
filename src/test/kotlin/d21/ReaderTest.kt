package d21

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class ReaderTest {

    private val reader = Reader()

    @Test
    fun `should read test input menu`() {
        // given

        // when
        val menu = reader.readMenu()

        // then
        then(menu[0].ingredients).containsExactlyInAnyOrder("mxmxvkd", "kfcds", "sqjhc", "nhms")
        then(menu[0].allergens).containsExactlyInAnyOrder("dairy", "fish")
        then(menu[1].ingredients).containsExactlyInAnyOrder("trh", "fvjkl", "sbzzf", "mxmxvkd")
        then(menu[1].allergens).containsExactlyInAnyOrder("dairy")
        then(menu[2].ingredients).containsExactlyInAnyOrder("sqjhc", "fvjkl")
        then(menu[2].allergens).containsExactlyInAnyOrder("soy")
        then(menu[3].ingredients).containsExactlyInAnyOrder("sqjhc", "mxmxvkd", "sbzzf")
        then(menu[3].allergens).containsExactlyInAnyOrder("fish")
    }

}