package y2020.d13

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

internal class ShuttleSearchTest {

    @Test
    fun `find earliest departure test input`() {
        // given
        val arrivalProblem = Reader().readArrivalProblem()

        // when
        val earliestDeparture = ShuttleSearch(arrivalProblem).findEarliestDeparture()

        // then
        then(earliestDeparture.multipliedNumber()).isEqualTo(295)
    }

    @Test
    fun `find earliest departure part a`() {
        // given
        val arrivalProblem = Reader().readArrivalProblem("day-13-a")

        // when
        val earliestDeparture = ShuttleSearch(arrivalProblem).findEarliestDeparture()

        // then
        then(earliestDeparture.multipliedNumber()).isEqualTo(2095)
    }

    @Test
    fun `find earliest step departure timestamp part b input`() {
        // given
        val arrivalProblem = Reader().readArrivalProblem("day-13-a")

        // when
        val earliestStepTimestamp = ShuttleSearch(arrivalProblem).findStepDepartureTimestamp()

        // then
        then(earliestStepTimestamp).isEqualTo(598411311431841)
    }

    @Test
    fun `find earliest step departure timestamp test input`() {
        // given
        val arrivalProblem = Reader().readArrivalProblem()

        // when
        val earliestStepTimestamp = ShuttleSearch(arrivalProblem).findStepDepartureTimestamp()

        // then
        then(earliestStepTimestamp).isEqualTo(1068781)
    }

    @Test
    fun `find earliest step departure timestamp test 5 input`() {
        // given
        val arrivalProblem = Reader().readArrivalProblem("day-13-test-5")

        // when
        val earliestStepTimestamp = ShuttleSearch(arrivalProblem).findStepDepartureTimestamp()

        // then
        then(earliestStepTimestamp).isEqualTo(1202161486)
    }
}