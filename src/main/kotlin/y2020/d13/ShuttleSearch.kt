package y2020.d13

import y2020.d13.Math.Companion.lcm
import y2020.d13.Reader.ArrivalProblem
import y2020.d13.Reader.Bus
import java.math.BigInteger
import kotlin.math.ceil

class ShuttleSearch(
    arrivalProblem: ArrivalProblem
) {

    private var busses: List<Bus> = arrivalProblem.busses
    private val earliestDeparture: Int = arrivalProblem.earliestDeparture

    private val departures = HashMap<Int, Bus>()

    fun findEarliestDeparture(): Departure {
        prepareDepartureBoard()

        val firstDeparture = departures.toSortedMap().firstKey()
        val bus = departures[firstDeparture]!!

        return Departure(bus, firstDeparture)
    }

    private fun prepareDepartureBoard() {
        busses.forEach { bus ->
            val waitingTime = computeWaitingTime(bus)
            departures[waitingTime] = bus
        }
    }

    private fun computeWaitingTime(bus: Bus): Int {
        val busLoopNumber = ceil(earliestDeparture.toDouble() / bus.number.toDouble()).toInt()
        val nextBusAt = busLoopNumber * bus.number

        return nextBusAt - earliestDeparture
    }

    fun findStepDepartureTimestamp(): BigInteger {

        var startingPoint: BigInteger = busses[0].number.toBigInteger()
        var period: BigInteger = busses[0].number.toBigInteger()

        for (index in 1 until busses.size) {
            val currentBussesView: List<Bus> = busses.subList(0, index + 1)

            for (timestamp in generateSequence(startingPoint) { it.plus(period) }) {

                if (areAlignedStepDepartures(currentBussesView, timestamp)) {
                    startingPoint = timestamp
                    period = lcm(*(currentBussesView.map { it.number.toBigInteger() }.toTypedArray()))
                    break
                }
            }
        }
        return startingPoint
    }

    private fun areAlignedStepDepartures(currentBussesView: List<Bus>, timestamp: BigInteger ): Boolean {
        return currentBussesView.map { bus ->
            (timestamp + bus.index.toBigInteger()) % bus.number.toBigInteger() == BigInteger.ZERO
        }.firstOrNull { !it } ?: return true
    }

    data class Departure(
        val bus: Bus,
        val waitingTime: Int
    )
}

fun ShuttleSearch.Departure.multipliedNumber() = bus.number * waitingTime