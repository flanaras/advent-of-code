package y2021.d02

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

internal class NavigationTrackerTest {

    @Test
    fun `should navigate test input a`() {
        // given
        val commands = Reader().readCommands()

        // when
        val checkValue = NavigationTracker().navigate(NavigationVisitorSimple(), commands)

        // then
        then(checkValue).isEqualTo(150)
    }

    @Test
    fun `should navigate input a`() {
        // given
        val commands = Reader().readCommands("day-02-a")

        // when
        val checkValue = NavigationTracker().navigate(NavigationVisitorSimple(), commands)

        // then
        then(checkValue).isEqualTo(1561344)
    }

    @Test
    fun `should navigate with aim test input a`() {
        // given
        val commands = Reader().readCommands()

        // when
        val checkValue = NavigationTracker().navigate(NavigationVisitorWithAim(), commands)

        // then
        then(checkValue).isEqualTo(900)
    }

    @Test
    fun `should navigate with aim input a`() {
        // given
        val commands = Reader().readCommands("day-02-a")

        // when
        val checkValue = NavigationTracker().navigate(NavigationVisitorWithAim(), commands)

        // then
        then(checkValue).isEqualTo(1848454425)
    }
}