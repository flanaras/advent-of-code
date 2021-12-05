package y2021.d01

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

internal class DepthFinderTest {

    @Test
    fun `should count depth increases test input a`() {
        // given
        val depths = Reader().readDepths()

        // when
        val depthIncreases = DepthFinder().calculateDepthIncreases(depths)

        // then
        then(depthIncreases).isEqualTo(7)
    }

    @Test
    fun `should count depth increases input a`() {
        // given
        val depths = Reader().readDepths("day-01-a")

        // when
        val depthIncreases = DepthFinder().calculateDepthIncreases(depths)

        // then
        then(depthIncreases).isEqualTo(1342)
    }

    @Test
    fun `should count depth increases with sliding window test input`() {
        // given
        val depths = Reader().readDepths()

        // when
        val depthIncreases = DepthFinder().calculateDepthIncreasesWithSlidingWindow(depths)

        // then
        then(depthIncreases).isEqualTo(5)
    }

    @Test
    fun `should count depth increases with sliding window input a`() {
        // given
        val depths = Reader().readDepths("day-01-a")

        // when
        val depthIncreases = DepthFinder().calculateDepthIncreasesWithSlidingWindow(depths)

        // then
        then(depthIncreases).isEqualTo(1378)
    }
}