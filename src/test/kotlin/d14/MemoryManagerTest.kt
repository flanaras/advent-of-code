package d14

import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

internal class MemoryManagerTest {

    @Test
    fun `should calculate test input a`() {
        // given
        val operations = Reader().readMemoryOperations()
        val memoryManager = MemoryManager(operations)
        val memoryVisitor = MemoryVisitorA()

        // when
        memoryManager.accept(memoryVisitor)
        val sum = memoryVisitor.sumMemoryValues()

        // then
        then(sum).isEqualTo(165)
    }

    @Test
    fun `should calculate input a`() {
        // given
        val operations = Reader().readMemoryOperations("day-14-a")
        val memoryManager = MemoryManager(operations)
        val memoryVisitor = MemoryVisitorA()

        // when
        memoryManager.accept(memoryVisitor)
        val sum = memoryVisitor.sumMemoryValues()

        // then
        then(sum).isEqualTo(13727901897109)
    }

    @Test
    fun `should calculate test input b`() {
        // given
        val operations = Reader().readMemoryOperations("day-14-test-b")
        val memoryManager = MemoryManager(operations)
        val memoryVisitor = MemoryAddressDecoderVisitor()

        // when
        memoryManager.accept(memoryVisitor)
        val sum = memoryVisitor.sumMemoryValues()

        // then
        then(sum).isEqualTo(208)
    }

    @Test
    fun `should calculate input b`() {
        // given
        val operations = Reader().readMemoryOperations("day-14-a")
        val memoryManager = MemoryManager(operations)
        val memoryVisitor = MemoryAddressDecoderVisitor()

        // when
        memoryManager.accept(memoryVisitor)
        val sum = memoryVisitor.sumMemoryValues()

        // then
        then(sum).isEqualTo(5579916171823)
    }
}