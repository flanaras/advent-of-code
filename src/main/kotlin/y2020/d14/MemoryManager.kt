package y2020.d14

class MemoryManager(private val operations: List<Operation>) {

    fun accept(visitor: MemoryVisitor) {
        operations.forEach { it.visit(visitor) }
    }
}

abstract class MemoryVisitor {
    private val memoryCells = HashMap<Long, Cell>()
    protected lateinit var mask: SetMask

    fun sumMemoryValues() = memoryCells.map { it.value.value }.reduce { acc, value -> acc + value }

    abstract fun visitMemCopy(memCopy: MemCopy)
    fun visitSetMask(setMask: SetMask) { mask = setMask }
    protected fun getOrCreateCell(address: Long) = memoryCells.getOrPut(address) { Cell() }
}

class MemoryVisitorA: MemoryVisitor() {
    override fun visitMemCopy(memCopy: MemCopy) {
        getOrCreateCell(memCopy.index).assignValueWithMask(memCopy.value, mask)
    }
}

class MemoryAddressDecoderVisitor(): MemoryVisitor() {
    override fun visitMemCopy(memCopy: MemCopy) {
        computePermutations(memCopy.index)
            .forEach { address -> getOrCreateCell(address).assignValue(memCopy.value) }
    }

    private fun computePermutations(address: Long): List<Long> {
        val addressCharArray = address.toString(2).padStart(36, '0').toCharArray()
        return computePermutations(addressCharArray)
            .map { it.concatToString() }
            .map { it.toLong(2) }
    }

    private fun computePermutations(addressCharArray: CharArray) = mutableListOf(addressCharArray).also { binaryAddresses ->
        mask.value.forEachIndexed { index, char ->
            when (char) {
                'X' -> {
                    binaryAddresses.forEach { chars -> chars[index] = '1' }
                    binaryAddresses.addAll(
                        binaryAddresses.map { chars -> chars.copyOf().also { it[index] = '0' } }
                    )
                }
                '1' -> binaryAddresses.forEach { it[index] = '1' }
            }
        }
    }
}

abstract class Operation {
    abstract fun visit(visitor: MemoryVisitor)
}

data class MemCopy(
    val index: Long,
    val value: Long
) : Operation() {
    override fun visit(visitor: MemoryVisitor) {
        visitor.visitMemCopy(this)
    }
}

data class SetMask(
    val value: String
) : Operation() {
    override fun visit(visitor: MemoryVisitor) {
        visitor.visitSetMask(this)
    }
}

class Cell {
    var value: Long = 0
    private set

    fun assignValue(newValue: Long) { value = newValue }
    fun assignValueWithMask(intValue: Long, mask: SetMask) {
        val paddedValue = intValue.toString(2).padStart(36, '0')

        value = mask.value.mapIndexed { index, char ->
            when (char) {
                'X' -> paddedValue[index]
                '1' -> '1'
                '0' -> '0'
                else -> throw IllegalStateException("Wrong input")
            }
        }.toCharArray()
            .concatToString()
            .toLong(2)
    }
}