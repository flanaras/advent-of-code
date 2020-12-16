package d16

import d13.Reader.Bus
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class ReaderTest {

    private val reader = Reader()

    @Test
    fun should_parse_one_bag_rule() {
        // given

        // when
        val notes = reader.readNotes()

        // then
        then(notes.myTicket.values).containsAll(listOf(1, 7, 14))
        then(notes.rules).containsAll(listOf(
            Rule("class", listOf(Range(1, 3), Range(5, 7))),
            Rule("row", listOf(Range(6, 11), Range(33, 44))),
            Rule("seat", listOf(Range(13, 40), Range(45, 50)))
        ))
        then(notes.nearbyTickets).containsAll(listOf(
            Ticket(listOf(7, 3, 47)),
            Ticket(listOf(40, 4, 50)),
            Ticket(listOf(55, 2, 20)),
            Ticket(listOf(38, 6, 12))
        ))
    }

}