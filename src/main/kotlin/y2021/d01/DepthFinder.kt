package y2021.d01

class DepthFinder {

    fun calculateDepthIncreases(depths: List<Int>): Int {
        var previous = Int.MAX_VALUE
        var counter = 0

        for (current in depths) {
            if (current > previous) {
                counter++
            }
            previous = current
        }
        return counter
    }

    fun calculateDepthIncreasesWithSlidingWindow(depths: List<Int>, windowSize: Int = 3): Int {
        return (0..depths.size - windowSize)
            .map { index -> depths.subList(index, index + windowSize) }
            .map { list -> list.reduce { acc, current -> acc + current } }
            .let { depthIncreasesOfSlidingWindow -> calculateDepthIncreases(depthIncreasesOfSlidingWindow) }
    }

}
