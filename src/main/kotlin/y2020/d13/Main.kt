package y2020.d13

fun main() {
    val timestamp = ShuttleSearch(Reader().readArrivalProblem("day-13-a")).findStepDepartureTimestamp()

    println(timestamp)
}

