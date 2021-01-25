package d22

import java.lang.IllegalArgumentException
import kotlin.collections.HashSet

class RecursiveCombatGame (
    players: List<Player>,
    private val decksPlayed: MutableSet<Int> = HashSet()
) : AbstractCombatGame(players) {

    override fun playRound() {
        val playerOne = players[0].deck.popTop()
        val playerTwo = players[1].deck.popTop()

        if (isDeckPlayed()) {
            addToDeckRecursive(players[0], playerOne, playerTwo)
        } else if (isNumberLowerThanPlayerDeck(playerOne, players[0]) && isNumberLowerThanPlayerDeck(playerTwo, players[1])) {
            addToDeckRecursive(playRecursiveRound(playerOne, playerTwo), playerOne, playerTwo)
        } else if (playerOne > playerTwo) {
            addToDeck(players[0], playerOne, playerTwo)
        } else {
            addToDeck(players[1], playerOne, playerTwo)
        }
        decksPlayed.add(players[0].deck.hashCode())
    }

    private fun isDeckPlayed(): Boolean = decksPlayed.contains(players[0].deck.hashCode())

    private fun addToDeckRecursive(winner: Player, playerOneCard: Int, playerTwoCard: Int) {
        val (top, bottom) = extractCorrectOrder(winner, playerOneCard, playerTwoCard)

        with(winner.deck) {
            addBottom(top)
            addBottom(bottom)
        }
    }

    private fun extractCorrectOrder(
        winner: Player,
        playerOneCard: Int,
        playerTwoCard: Int
    ): Pair<Int, Int> =
        when (winner.name) {
            players[0].name -> Pair(playerOneCard, playerTwoCard)
            players[1].name -> Pair(playerTwoCard, playerOneCard)
            else -> {
                throw IllegalArgumentException("Cannot find winner name")
            }
        }

    private fun playRecursiveRound(numOfDeckOne: Int, numOfDeckTwo: Int): Player =
        RecursiveCombatGame(arrayListOf(
            Player(players[0].name, players[0].deck.take(numOfDeckOne)),
            Player(players[1].name, players[1].deck.take(numOfDeckTwo))
        )).let {
            it.playGame()
            val winner = it.findWinner()
            players.find { player -> player.name == winner.name } ?: throw IllegalArgumentException("Didn't find player")
        }

    private fun isNumberLowerThanPlayerDeck(playerCard: Int, player: Player) =
        playerCard <= player.deck.size()
}