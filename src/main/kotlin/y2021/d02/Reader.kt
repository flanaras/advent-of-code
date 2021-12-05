package y2021.d02

import y2021.d01.base_2021
import y2021.d02.Direction.DOWN
import y2021.d02.Direction.FORWARD
import y2021.d02.Direction.UP
import java.io.File

class Reader {

    fun readCommands(filename: String = "day-02-test-a"): List<Command> {
        return File(base_2021 + filename).readLines()
            .map(this::toPath)
    }

    private fun toPath(line: String): Command {
        return line.split(" ")
            .let { Command(toDirection(it[0]), it[1].toInt()) }
    }

    private fun toDirection(command: String) = when (command.lowercase()) {
        UP.name.lowercase() -> UP
        DOWN.name.lowercase() -> DOWN
        FORWARD.name.lowercase() -> FORWARD
        else -> throw IllegalStateException("Unexpected value")
    }

}
