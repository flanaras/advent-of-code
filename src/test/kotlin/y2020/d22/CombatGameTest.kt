package y2020.d22

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

internal class CombatGameTest {

    @Test
    fun should_compute_test_game() {
        // given
        val players = Reader().readPlayers()

        // when
        val score = CombatGame(players).computeWinnersScore()

        // then
        then(score).isEqualTo(306)
    }

    @Test
    fun should_compute_part_a_game() {
        // given
        val players = Reader().readPlayers("day-22-a")

        // when
        val score = CombatGame(players).computeWinnersScore()

        // then
        then(score).isEqualTo(35370)
    }
}
