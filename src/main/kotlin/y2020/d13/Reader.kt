package y2020.d13

import y2020.d07.base_2020
import java.io.File

class Reader {

    fun readArrivalProblem(filename: String = "day-13-test"): ArrivalProblem {
        val fileLines = File(base_2020 + filename).readLines()

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
