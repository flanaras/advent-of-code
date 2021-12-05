package y2020.d09

import y2020.d07.base_2020
import java.io.File
import java.math.BigInteger

class Reader {

    fun readInts(filename: String = "day-09-test"): List<BigInteger> =
        File(base_2020 + filename).readLines()
            .map { it.toBigInteger() }

}
