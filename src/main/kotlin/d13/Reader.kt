package d13

import d07.base
import java.io.File
import java.math.BigInteger

class Reader {

    fun readArrivalProblem(filename: String = "day-13-test"): ArrivalProblem {
        val fileLines = File(base + filename).readLines()

        val busInput = fileLines[1].split(",")
        val busses = parseBusses(busInput)

        return ArrivalProblem(fileLines[0].toInt(), busses)
    }

    private fun parseBusses(busInput: List<String>) =
        (busInput.indices).filter { i ->
            busInput[i] != "x"
        }.map { i ->
            Bus(busInput[i].toInt(), i)
        }

    data class ArrivalProblem(
        val earliestDeparture: Int,
        val busses: List<Bus>
    )

    data class Bus(
        val number: Int,
        val index: Int
    )
}
