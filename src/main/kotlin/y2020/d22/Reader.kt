package y2020.d22

import y2020.d07.base_2020
import java.io.File
import java.util.*

class Reader {

    fun readPlayers(filename: String = "day-22-test"): List<Player> {
        val playersInput = File(base_2020 + filename).readLines()
        val playerOne = playersInput.subList(0, playersInput.size / 2)
        val playerTwo = playersInput.subList(playersInput.size / 2 + 1, playersInput.size)

        return listOf(toPlayer(playerOne), toPlayer(playerTwo))
    }

    private fun toPlayer(playerInput: List<String>): Player {
        val deck = Deck(playerInput.drop(1).map { str -> str.toInt() }.asIterable())
        return Player(removeColon(playerInput), deck)
    }

    private fun removeColon(playerInput: List<String>) =
        playerInput[0].replace(":", "")

}

data class Player(
    val name: String,
    val deck: Deck
)

class Deck(deque: Deque<Int>) {
    private val deck = deque
    constructor(it: Iterable<Int>) : this(ArrayDeque<Int>().apply { addAll(it) })

    fun isEmpty() = deck.isEmpty()
    fun addBottom(item: Int) = deck.addLast(item)
    fun popTop() = deck.pollFirst() ?: throw IllegalArgumentException("Popping an empty deck")
    fun take(number: Int) = Deck(deck.take(number))
    fun size() = deck.size

    fun deckItems(): List<Int> = deck.asIterable().toList()

    override fun hashCode(): Int {
        deck.indices
        return deck.reduceOrNull { first, second -> Objects.hash(first, second) }
            ?: 0
    }
}

