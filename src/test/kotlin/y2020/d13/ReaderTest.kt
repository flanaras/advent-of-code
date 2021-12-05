package y2020.d13

import y2020.d13.Reader.Bus
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class ReaderTest {

    private val reader = Reader()

    @Test
    fun should_parse_one_bag_rule() {
        // given

        // when
        val arrivalProblem = reader.readArrivalProblem()

        // then
        then(arrivalProblem.earliestDeparture).isEqualTo(939)
        then(arrivalProblem.busses).hasSize(5)
        then(arrivalProblem.busses).containsAll(arrayListOf(
            Bus(7, 0),
            Bus(13, 1),
            Bus(59, 4),
            Bus(31, 6),
            Bus(19, 7)
        ))
    }

}