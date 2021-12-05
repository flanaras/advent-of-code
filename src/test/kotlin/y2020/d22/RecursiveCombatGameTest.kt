package y2020.d22

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

internal class RecursiveCombatGameTest {

    @Test
    fun should_compute_part_b_test_game() {
        // given
        val players = Reader().readPlayers("day-22-test")

        // when
        val score = RecursiveCombatGame(players).computeWinnersScore()

        // then
        then(score).isEqualTo(291)
    }

    @Test
    fun should_compute_part_b_game() {
        // given
        val players = Reader().readPlayers("day-22-a")

        // when
        val score = RecursiveCombatGame(players).computeWinnersScore()
        println(score)

        // then
        then(score).isEqualTo(36246)
    }

}