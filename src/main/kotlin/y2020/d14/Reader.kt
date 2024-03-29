package y2020.d14

import y2020.d07.base_2020
import java.io.File

class Reader {

    fun readMemoryOperations(filename: String = "day-14-test"): List<Operation> =
        File(base_2020 + filename).readLines()
            .map { parseLine(it) }

    private fun parseLine(line: String): Operation =
        if (line.contains("mem")) toOperation(line)
        else toMask(line)

    private fun toMask(line: String) = SetMask(line.replace("[^X01]".toRegex(), ""))

    private fun toOperation(inputLine: String): MemCopy =
        inputLine.replace("[^-0-9]+".toRegex()," ")
            .trim().split(" ")
            .also {
                if (it.equals("10100111101")) {
                    print("")
                }
            }
            .let { strList -> MemCopy(strList[0].toLong(), strList[1].toLong()) }
}

