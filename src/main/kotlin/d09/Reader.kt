package d09

import d07.base
import java.io.File
import java.math.BigInteger

class Reader {

    fun readInts(filename: String = "day-09-test"): List<BigInteger> =
        File(base + filename).readLines()
            .map { it.toBigInteger() }

}
