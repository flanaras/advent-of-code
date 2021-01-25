package d22

class CombatGame (
    players: List<Player>
) : AbstractCombatGame(players) {

    override fun playRound() {
        val playerOne = players[0].deck.popTop()
        val playerTwo = players[1].deck.popTop()

        if (playerOne > playerTwo) {
            addToDeck(players[0], playerOne, playerTwo)
        } else {
            addToDeck(players[1], playerOne, playerTwo)
        }
    }
}