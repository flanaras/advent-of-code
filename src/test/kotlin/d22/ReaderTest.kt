package d22

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class ReaderTest {

    private val reader = Reader()

    @Test
    fun should_read_players() {
        // given

        // when
        val players = reader.readPlayers()

        // then
        then(players[0].name).isEqualTo("Player 1")
        then(players[0].deck.deckItems()).contains(9, 2, 6, 3, 1)
        then(players[1].name).isEqualTo("Player 2")
        then(players[1].deck.deckItems()).contains(5, 8, 4, 7, 10)
    }

}