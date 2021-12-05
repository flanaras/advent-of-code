package y2021.d02

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import y2021.d02.Direction.DOWN
import y2021.d02.Direction.FORWARD
import y2021.d02.Direction.UP

class ReaderTest {

    private val reader = Reader()

    @Test
    fun `should read test input a depth`() {
        // given

        // when
        val commands = reader.readCommands()

        // then
        then(commands.size).isEqualTo(6)
        then(commands[0]).isEqualTo(Command(FORWARD, 5))
        then(commands[1]).isEqualTo(Command(DOWN, 5))
        then(commands[2]).isEqualTo(Command(FORWARD, 8))
        then(commands[3]).isEqualTo(Command(UP, 3))
        then(commands[4]).isEqualTo(Command(DOWN, 8))
        then(commands[5]).isEqualTo(Command(FORWARD, 2))
    }

}