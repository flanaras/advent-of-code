package d22

import java.lang.IllegalArgumentException
import kotlin.math.max
import kotlin.math.min

abstract class AbstractCombatGame (
    protected val players: List<Player>
) {
    fun computeWinnersScore(): Int {
        playGame()
        val winner = findWinner()
        return computeScore(winner)
    }

    private fun computeScore(winner: Player): Int =
        winner.deck.deckItems().reversed()
            .reduceIndexed { index, acc, card ->
                acc + (index + 1 ) * card
            }

    protected fun findWinner(): Player = players.find { player -> !player.deck.isEmpty() } ?:
    throw IllegalArgumentException("No winner found")

    protected fun playGame() {
        while (!gameDone()) {
            playRound()
        }
    }

    protected abstract fun playRound()

    protected fun addToDeck(player: Player, cardOne: Int, cardTwo: Int) {
        with(player.deck) {
            addBottom(max(cardOne, cardTwo))
            addBottom(min(cardOne, cardTwo))
        }
    }

    private fun gameDone() = players.map { it.deck.isEmpty() }.any { it }
}